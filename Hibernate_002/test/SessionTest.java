import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class SessionTest {

	@Test
	public void testOpensession(){
		//������ö���
		Configuration config = new Configuration().configure();
		
		//��÷���ע�����
		//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		
		//���sessionFactory����
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		//���session����
		Session session = sessionFactory.openSession();
		
		if(session != null){
			System.out.println("session �����ɹ���");
		}
		else {
			System.out.println("����ʧ��");
		}
		
	}
	
	
	@Test
	public void testGetCurrentSession(){
		//������ö���
		Configuration config = new Configuration().configure();
		
		//��÷���ע�����
		//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		
		//���sessionFactory����
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		//���session����
		Session session = sessionFactory.getCurrentSession();
		
		if(session != null){
			System.out.println("session �����ɹ���");
		}
		else {
			System.out.println("����ʧ��");
		}
		
	}
}
