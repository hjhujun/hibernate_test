

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentsTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void Init()
	{
		try {
			System.out.println("init");
			

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
			System.out.println("commit");
			
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
			System.out.println("student");

			//����ѧ������
			Students s = new Students(3,"qqq","qw", new Date(), "ccc");
			//������� д���ݿ�
			session.save(s);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}
}
