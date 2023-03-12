package com.edubridge.com.edubridge.domain;
import java.util.Scanner;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App{ 

	/* we creating exception for connecting database and java we are accessing username and password for mysql driver*/
	/*configuration is used for read default constructor hibernste.cfg.xml*/
	//session factory is used to create session object//
	private static SwiggyRestaurant hotelid = new SwiggyRestaurant();
	private static Scanner scan = new Scanner(System.in);
	public static void toCreateConnection() {
		try {
			Configuration config = new Configuration().configure().addAnnotatedClass(SwiggyRestaurant.class);
			SessionFactory sessionFact = config.buildSessionFactory(); 
			Session session = sessionFact.openSession();
			toManipulateData(sessionFact,session);

		}catch(HibernateException obj) {
			System.out.println(obj);
		}
		catch(Exception obj) {
			System.out.println(obj);
		}

	}

	public static void toManipulateData(SessionFactory sessionfact,Session session) {
		System.out.println("Please choose the option 1.Add the Restaurant , 2. Display the Restaurant, 3. Update the Restaurant, 4. Search the Restaurant, 5.Delete the restaurant, 6.Log out");
		int userChoice;
		do {
			userChoice=scan.nextInt();	
			switch(userChoice) {
			case 1:
				addRestaurant(session);
				break;
			case 2:
				display(hotelid);
				break;
			case 3:
				updateById(session);
				break;
			case 4:
				search(session);
				break;
			case 5: deleteById(session);
			break;
			case 6: SwiggyRestaurant.toCallLogOut();
			sessionfact.close();
			break;
			case 7:sessionfact.close();
			break;
			}
		}while(userChoice!=7);
	}


	private static void addRestaurant(Session session) {
		System.out.println("Enter the restaurant detail to add");
		session.beginTransaction();
		session.save(getRestaurant());
		Integer id = hotelid.getId();
		System.out.println("RestaurantDetails is created with id :"+id);
		session.getTransaction().commit();

	}

	private static void deleteById(Session session) {

		System.out.println("Enter the position of the id to delete");
		int id = scan.nextInt();
		SwiggyRestaurant hotelid = session.get(SwiggyRestaurant.class, id);

		if(hotelid != null) {
			session.beginTransaction();
			session.remove(hotelid);
			System.out.println("Deleted Successfully");
			session.getTransaction().commit();
		}
		else {
			System.out.println("employee id doesnt exists..");

		}	
	}
	private static void updateById(Session session) {
		System.out.println("Enter the position of id to update");
		int id = scan.nextInt();
		SwiggyRestaurant hotelid = session.get(SwiggyRestaurant.class, id);
		if(hotelid != null) {
			//scanner obj 
			System.out.println("Enter the restaurant name");
			hotelid.setRes_Name(scan.next());
			System.out.println("Enter the owner name");
			hotelid.setOwn_Name(scan.next());
			System.out.println("Enter the food type");
			hotelid.setFood_Type(scan.next());
			System.out.println("Enter the location");
			hotelid.setLocation(scan.next());
			/*System.out.println("Enter the price");
			hotelid.setPrice(scan.nextInt());*/
			System.out.println("Enter the rating");
			hotelid.setRatings(scan.nextDouble());
			session.beginTransaction();
			session.persist(hotelid);
			session.getTransaction().commit();
		}
		else {
			System.out.println("employee id doesnt exists..");

		}	
	}
	private static void search(Session session){
		System.out.println("Enter the position of the id to search");
		int id = scan.nextInt();
		SwiggyRestaurant hotelid = session.get(SwiggyRestaurant.class, id);
		if(hotelid != null) {
			System.out.println(hotelid);
		}
		else {
			System.out.println("Restaurant id doesnt exists..");

		}
	}
	private static void display(SwiggyRestaurant hotelid){
		System.out.println("Restaurant list");

		System.out.println(hotelid);
	}
	private static SwiggyRestaurant getRestaurant() {

		System.out.println("Enter the restaurant name");
		hotelid.setRes_Name(scan.next());
		System.out.println("Enter the owner name");
		hotelid.setOwn_Name(scan.next());
		System.out.println("Enter the food type");
		hotelid.setFood_Type(scan.next());
		System.out.println("Enter the location");
		hotelid.setLocation(scan.next());
		/*System.out.println("Enter the price");
		hotelid.setPrice(scan.nextInt());*/
		System.out.println("Enter the rating");
		hotelid.setRatings(scan.nextDouble());
		return hotelid;
	}
	public static void main( String[] args )
	{
		System.out.println("Enter the user name");
		String username = scan.next();
		System.out.println("Enter the password");
		String password = scan.next();
		SwiggyRestaurant.toCheckLogin(username, password);

	}
}

