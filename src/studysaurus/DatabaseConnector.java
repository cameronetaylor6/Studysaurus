package studysaurus;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public final class DatabaseConnector {
	private static final DatabaseConnector instance = new DatabaseConnector();
	private DatabaseConnector(){
		
	}
	public static DatabaseConnector getInstance(){
		return instance;
	}
	
	public ArrayList<String> getSets(boolean custom){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
		String hql = "SELECT S.name FROM Set S WHERE S.custom = " + custom + "";
		Query query = session.createQuery(hql);
		ArrayList<String> results = (ArrayList<String>) query.list();
		session.close();
        sessionFactory.close();
        return results;
	}
	public Set selectSet(String setName){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "Select S FROM Set S WHERE S.name = '" + setName + "'";
		Query query = session.createQuery(hql);
		ArrayList<Set> selectedSetList = (ArrayList<Set>) query.list();
		if(!selectedSetList.get(0).getName().equals(setName)){
			session.close();
			sessionFactory.close();
			//TODO: is throwing Null here acceptable?
			return null;
		}
		//TODO: look into session.load
		Set selectedSet = new Set(setName, selectedSetList.get(0).getCustom());
		hql = "Select P FROM Pair P WHERE P.ownerSet = '" + setName + "'";
		query = session.createQuery(hql);
		ArrayList<Pair> selectedPairList = (ArrayList<Pair>) query.list();
		for(Pair p: selectedPairList){
			selectedSet.addPair(p);
		}
		session.close();
		sessionFactory.close();
		return selectedSet;
	}
	public boolean checkSetName(String setName) {
		ArrayList<String> setNames = getSets(true);
		setNames.addAll(getSets(false));
		if(setNames.contains(setName)){
			return true;
		}
		return false;
	}
	public void saveSet(Set aSet){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		if(checkSetName(aSet.getName())){
			String hql = "SELECT S.id FROM Set S WHERE S.name = '" + aSet.getName() + "'";
			Query query = session.createQuery(hql);
			ArrayList<Integer> results = (ArrayList<Integer>) query.list();
			Set inst = session.load(Set.class, results.get(0));
			session.update(inst);
			hql = "SELECT P.id FROM Pair P WHERE P.ownerSet = '" + aSet.getName() + "'";
			query = session.createQuery(hql);
			results = (ArrayList<Integer>) query.list();
			for(int pId : results){
				Object inst2 = session.load(Pair.class, pId);
				if(inst2 != null){
					session.delete(inst2);
				}
			}
			for(Pair pair : aSet.getPairs()){
	        	session.save(pair);
	        }
			session.getTransaction().commit();
			session.close();
			return;
		}
        session.save(aSet);
        for(Pair pair : aSet.getPairs()){
        	session.save(pair);
        }
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
	}
	public void saveDefaults(){
		ArrayList<Score> scoreList = new ArrayList<Score>();
        scoreList.add(new Score("CET", "BasicTimesTable", 600));
        scoreList.add(new Score("JSC", "BasicTimesTable", 900));
        scoreList.add(new Score("JAM", "BasicTimesTable", 100));
        scoreList.add(new Score("CET", "BasicTimesTable", 0));
        	        
        ArrayList<Set> sets = new ArrayList<Set>();
        Set s1 = new Set("BasicTimesTable", false);
        for(int i = 1; i < 13; i++){
        	for(int j = 1; j < 13; j++){
        		Pair p1 = new Pair((Integer.toString(i) + "x" + Integer.toString(j)), Integer.toString(i*j), s1.getName());
        		s1.addPair(p1);
        	}
        }	        
        sets.add(s1);
        
        Set s2 = new Set("noodle", false);
        Pair n1 = new Pair("spaghetti", "marinara", s2.getName());
        Pair n2 = new Pair("jack", "sparrow", s2.getName());
        s2.addPair(n1);
        s2.addPair(n2);
        
        sets.add(s2);
        
        Set s3 = new Set("poodle", false);
        Pair n3 = new Pair("bark", "woof", s3.getName());
        Pair n4 = new Pair("dingus", "dodongo", s3.getName());
        s2.addPair(n3);
        s2.addPair(n4);
        
        sets.add(s3);
         
        for (Score score : scoreList){
            saveScore(score);
        }
        for (Set set : sets){
            saveSet(set);
        }
	}
	
	public ArrayList<Score> getScores(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
		String hql = "SELECT S FROM Score S ORDER BY _score DESC";
		//this normally returns List, could be issues casting to ArrayList
		Query query = session.createQuery(hql);
		query.setMaxResults(10);
		ArrayList<Score> results = (ArrayList<Score>) query.list();
		session.close();
        sessionFactory.close();
        return results;
	}
	public void saveScore(Score aScore){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //maybe throw duplicate checking in?
        session.saveOrUpdate(aScore);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
	}
	
	public void checkForDefaults(){
		ArrayList<String> setList = getSets(false);
		if(setList.isEmpty()){
			saveDefaults();
		}
	}
	public void checkForDatabase(){
		//TODO: implement query to check if database exists, if not, create it: don't think this will work
	}
	public static void main(String args[]){
		
	}

}
