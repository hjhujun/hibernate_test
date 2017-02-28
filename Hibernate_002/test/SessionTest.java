import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class SessionTest {

	@Test
	public void testOpensession(){
		//获得配置对象
		Configuration config = new Configuration().configure();
		
		//获得服务注册对象
		//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		
		//获得sessionFactory对象
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		//获得session对象
		Session session = sessionFactory.openSession();
		
		if(session != null){
			System.out.println("session 创建成功！");
		}
		else {
			System.out.println("创建失败");
		}
		
	}
	
	
	@Test
	public void testGetCurrentSession(){
		//获得配置对象
		Configuration config = new Configuration().configure();
		
		//获得服务注册对象
		//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		
		//获得sessionFactory对象
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		//获得session对象
		Session session = sessionFactory.getCurrentSession();
		
		if(session != null){
			System.out.println("session 创建成功！");
		}
		else {
			System.out.println("创建失败");
		}
		
	}
}
