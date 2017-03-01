

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sun.launcher.resources.launcher;

public class StudentsTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void Init()
	{
		try {
			System.out.println("init");
			
//			//������ö���
//			Configuration config = new Configuration().configure();
//			//��÷���ע�����
//			//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
//			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
//			//���sessionFactory����
//			SessionFactory sessionFactory = config.buildSessionFactory();
//			//���session����
//			Session session = sessionFactory.openSession();
//			
//			
			
			
			
			

			//�������ö���
			Configuration config = new Configuration().configure();
			//��������ע�����
			//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			//�����Ự��������
			//sessionFactory = config.buildSessionFactory(serviceRegistry);
			sessionFactory = config.buildSessionFactory();
			//�Ự����
			session = sessionFactory.openSession();
			//��������
			transaction = session.beginTransaction();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	@After
	public void destory()
	{
		try {
			System.out.println("destory");
			
			//�ύ����
			transaction.commit();
			
			//�رջỰ
			session.close();
			
			//�رջỰ����
			sessionFactory.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
		
	}
	
	@Test
	public void teststudent()
	{
		try {
			System.out.println("teststudent");

			//����ѧ������
			//Students s = new Students(3,"qqq","qw", new Date(), "ccc");
			Students s = new Students();
			s.setAddress("wudangshan");
			s.setBirthday(new Date());
			s.setGender("man");
			s.setSname("myname");
			s.setStid(2);
			//s.setPicture(null);
			//������� д���ݿ�
			session.save(s);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}
	
	@Test
	public void  testWriteBlob() throws IOException  {
		
		System.out.println("testWriteBlob");
			
		Students s = new Students(1, "name", "gender", new Date(), "address");
		
		File f = new File("C:\\reskill\\ipcontrol.jpg");
		
		InputStream input = new FileInputStream(f);
		//byte[] bFile = new byte[f];
		  System.out.println(input.available());
System.out.println(input);
		Blob image = Hibernate.getLobCreator(session).createBlob(input,input.available() ); 
				//Hibernate.getLobCreator(session).createBlob(input, input.available());
		  
		  System.out.println(input.available());

		s.setPicture(image); //(image);
		

		session.save(s);
	}
	
	@Test
	public void testReadBlob() throws Exception{
		Students s = (Students) session.get(Students.class, 1);
		
		Blob image = s.getPicture();
		
		InputStream inputStream = image.getBinaryStream();
		
		File f = new File("C:\\reskill\\getpic.jpg");
		
		OutputStream output = new FileOutputStream(f);
		
		byte[] buff = new byte[inputStream.available()];
		inputStream.read(buff);
		output.write(buff);

		inputStream.close();
		output.close();
		
		
	}
}
