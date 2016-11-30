package studysaurus;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class DatabaseConnector {
	public ArrayList<String> getSets(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
		String hql = "SELECT S.name FROM Set S";
		//this normally returns List, could be issues casting to ArrayList
		Query query = session.createQuery(hql);
		ArrayList<String> results = (ArrayList<String>) query.list();
		session.close();
        sessionFactory.close();
        return results;
	}
	
	 public static void main(String[] args){
		 DatabaseConnector dc = new DatabaseConnector();
		 ArrayList<String> ls = dc.getSets();
		 System.out.println(ls);
	 }
}
