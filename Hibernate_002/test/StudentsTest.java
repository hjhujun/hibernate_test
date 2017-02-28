

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
			

			//创建配置对象
			Configuration config = new Configuration().configure();
			
			//创建服务注册对象
			//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			
			//创建会话工厂对象
			//sessionFactory = config.buildSessionFactory(serviceRegistry);
			sessionFactory = config.buildSessionFactory();
			
			//会话对象
			session = sessionFactory.openSession();
			
			//开启事务
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
			
			//提交事务
			transaction.commit();
			
			//关闭会话
			session.close();
			
			//关闭会话工厂
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

			//生成学生对象
			Students s = new Students(3,"qqq","qw", new Date(), "ccc");
			//保存对象 写数据库
			session.save(s);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}
}
