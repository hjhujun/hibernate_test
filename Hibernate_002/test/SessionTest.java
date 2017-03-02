import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class SessionTest {

	@Test
	public void testOpensession() {
		// ������ö���
		Configuration config = new Configuration().configure();
		// ��÷���ע�����
		// ServiceRegistry serviceRegistry = new
		// StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
				.build();
		// ���sessionFactory����
		SessionFactory sessionFactory = config.buildSessionFactory();
		// ���session����
		Session session = sessionFactory.openSession();
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		System.out.println(session1 == session2);
		if (session != null) {
			System.out.println("open session �����ɹ���");
		} else {
			System.out.println("open session ����ʧ��");
		}

	}

	@Test
	public void testGetCurrentSession() {
		// ������ö���
		Configuration config = new Configuration().configure();

		// ��÷���ע�����
		// ServiceRegistry serviceRegistry = new
		// StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
				.build();

		// ���sessionFactory����
		SessionFactory sessionFactory = config.buildSessionFactory();

		// ���session����
		Session session = sessionFactory.getCurrentSession();
		Session session1 = sessionFactory.getCurrentSession();
		Session session2 = sessionFactory.getCurrentSession();
		System.out.println(session1 == session2);

		if (session != null) {
			System.out.println("get current session �����ɹ���");
		} else {
			System.out.println("get current session ����ʧ��");
		}

	}

	@Test
	public void testSaveStudentsWithOpenSeeion() {
		// ������ö���
		Configuration config = new Configuration().configure();
		// ��÷���ע�����
		// ServiceRegistry serviceRegistry = new
		// StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
				.build();
		// ���sessionFactory����
		SessionFactory sessionFactory = config.buildSessionFactory();
		// ���session����
		Session session1 = sessionFactory.openSession();
		// ��������
		Transaction transcation = session1.beginTransaction();
		// ����һ��ѧ������
		Students s1 = new Students(8, "ali88", "man", new Date(), "beijing");

		session1.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("session1 has code is " + connection.hashCode());

			}
		});

		session1.save(s1);
		// session1.close();
		transcation.commit();

		Session session2 = sessionFactory.openSession();
		transcation = session2.beginTransaction();

		session2.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("session2 has code is " + connection.hashCode());

			}
		});

		Students s2 = new Students(7, "ali77", "man", new Date(), "beijing");
		session2.save(s2);
		// session2.close();
		transcation.commit();
	}

	@Test
	public void testSaveStudentsWithGetCurrentSession() {
		try {

			// ������ö���
			Configuration config = new Configuration().configure();

			// ��÷���ע�����
			// ServiceRegistry serviceRegistry = new
			// StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
					.build();

			// ���sessionFactory����
			SessionFactory sessionFactory = config.buildSessionFactory();

			// ���session����
			// Session session = sessionFactory.getCurrentSession();
			Session session1 = sessionFactory.getCurrentSession();

			// ��������
			Transaction transcation = session1.beginTransaction();

			// System.out.println(session1==session2);

			// if(session != null){
			// System.out.println("get current session �����ɹ���");
			// }
			// else {
			// System.out.println("get current session ����ʧ��");
			// }

			Students s1 = new Students(8, "ali88", "man", new Date(), "beijing");

			session1.doWork(new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {
					// TODO Auto-generated method stub
					System.out.println("session1 has code is " + connection.hashCode());

				}
			});

			session1.save(s1);
			transcation.commit();
			// session1.close();

			// Session session2 = sessionFactory.openSession();
			Session session2 = sessionFactory.getCurrentSession();

			transcation = session2.beginTransaction();

			session2.doWork(new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {
					// TODO Auto-generated method stub
					System.out.println("session2 has code is " + connection.hashCode());

				}
			});

			Students s2 = new Students(7, "ali77", "man", new Date(), "beijing");
			session2.save(s2);
			// session2.close();
			transcation.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	@Test
	public void testWriteBlob() {
		try {

			// ������ö���
			Configuration config = new Configuration().configure();

			// ��÷���ע�����
			// ServiceRegistry serviceRegistry = new
			// StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
					.build();

			// ���sessionFactory����
			SessionFactory sessionFactory = config.buildSessionFactory();

			// ���session����
			// Session session = sessionFactory.getCurrentSession();
			Session session = sessionFactory.openSession();
			Transaction transcation = session.beginTransaction();

			System.out.println("testWriteBlob");

			Students s = new Students(999, "name", "gender", new Date(), "address");

			File f = new File("C:\\reskill\\ipcontrol.jpg");

			InputStream input = new FileInputStream(f);
			// byte[] bFile = new byte[f];
			System.out.println(input.available());
			System.out.println(input);
			Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());
			// Hibernate.getLobCreator(session).createBlob(input,
			// input.available());

			System.out.println(input.available());

			s.setPicture(image); // (image);

			session.save(s);
			// �ύ����
			transcation.commit();

			// �رջỰ
			session.close();

			// �رջỰ����
			sessionFactory.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
