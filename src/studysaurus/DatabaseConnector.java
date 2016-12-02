package studysaurus;

import java.util.ArrayList;

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
		//this normally returns List, could be issues casting to ArrayList
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
	public void saveSet(Set aSet){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(aSet);
        session.getTransaction().commit();
        session.close();
	}
	public void saveDefaults(){
		ArrayList<Score> scoreList = new ArrayList<Score>();
        scoreList.add(new Score("CET", "doggos", 69));
        scoreList.add(new Score("JSC", "puppers", 96));
        scoreList.add(new Score("JAM", "doggos", 100));
        scoreList.add(new Score("CET", "pitbull", 0));
        	        
        ArrayList<Set> sets = new ArrayList<Set>();
        Set s1 = new Set("BasicTimesTable", false);
        for(int i = 1; i < 13; i++){
        	for(int j = 1; j < 13; j++){
        		Pair p1 = new Pair((Integer.toString(i) + "x" + Integer.toString(j)), Integer.toString(i*j), s1.getName());
        		s1.addPair(p1);
        	}
        }	        
        sets.add(s1);
        
        Set s2 = new Set("noodle", true);
        Pair n1 = new Pair("spaghetti", "marinara", s2.getName());
        Pair n2 = new Pair("jack", "sparrow", s2.getName());
        s2.addPair(n1);
        s2.addPair(n2);
        
        sets.add(s2);
        
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
         
        for (Score score : scoreList){
            session.save(score);
            System.out.println(score);
        }
         
        session.getTransaction().commit();
        session.close();
        
        session = sessionFactory.openSession();
        session.beginTransaction();
         
        for (Set set : sets){
            session.save(set);
            System.out.println(set);
            for (Pair pair : set.getPairs()){
	            session.save(pair);
	            System.out.println(pair);
	        }
        }
         
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
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
        session.save(aScore);
        session.getTransaction().commit();
        session.close();
	}
	
	public void checkForDefaults(){
		ArrayList<String> setList = getSets(false);
		if(setList.isEmpty()){
			saveDefaults();
		}
	}
	public void checkForDatabase(){
		//implement query to check if database exists, if not, create it
		//I don't think we can implement this
	}
	public static void main(String args[]){
		
	}

}
