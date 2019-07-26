package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// query all students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastname=Doe
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			System.out.println("\n\nStudents with last name of Doe");
			displayStudents(theStudents);

			// query students: lastname=Doe
			theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
			
			System.out.println("\n\nStudents with last name of Doe or first name of Daffy");
			displayStudents(theStudents);
			
			// query students: email ends with luv2code.com
			theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();
			
			System.out.println("\n\nStudents with email ends with luv2code.com");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student t : theStudents)
			System.out.println(t);
	}

}
