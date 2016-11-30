package studysaurus;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class DatabaseConnector {
	public ArrayList<String> getSets(String type){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
		String hql = "SELECT S.name FROM Set S WHERE S.type = '" + type + "'";
		//this normally returns List, could be issues casting to ArrayList
		Query query = session.createQuery(hql);
		ArrayList<String> results = (ArrayList<String>) query.list();
		session.close();
        sessionFactory.close();
        return results;
	}
	/* Cam! Here's what I have so far
	public Set selectSet(String setName){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "Select S FROM Set S WHERE S.name = '" + setName + "'";
		Query query = session.createQuery(hql);
		ArrayList<String> selectedSetList = (ArrayList<String>) query.list();
		Set selectedSet = new Set(setName)
		session.close();
		sessionFactory.close();
		return selectedSet;
	}*/
	 public static void main(String[] args){
		 DatabaseConnector dc = new DatabaseConnector();
		 ArrayList<String> ls = dc.getSets("default");
		 System.out.println(ls);
		 //Set ss = dc.selectSet("dong");
		 //System.out.print(ss);
	 }
}
