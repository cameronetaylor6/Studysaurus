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
	        Set s = new Set("dong");
	        Pair dp = new Pair("doggo", "pupper", s.getName());
	        Pair pd = new Pair("pupper", "doggo", s.getName());
	        s.addPair(dp);
	        s.addPair(pd);
	        
	        sets.add(s);
	        
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
	        }
	         
	        session.getTransaction().commit();
	        session.close();
	        
	        session = sessionFactory.openSession();
	        session.beginTransaction();
	         
	        for (Pair pair : s.getPairs()){
	            session.save(pair);
	            System.out.println(pair);
	        }
	         
	        session.getTransaction().commit();
	        session.close();
	        sessionFactory.close();
	        
	        GameClient gc = GameClient.getInstance();
	    }
}
