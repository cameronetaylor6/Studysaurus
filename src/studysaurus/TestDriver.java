package studysaurus;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestDriver {
	 public static void main(String[] args)
	    {
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
	        
	        GameClient gc = GameClient.getInstance();
	    }
}
