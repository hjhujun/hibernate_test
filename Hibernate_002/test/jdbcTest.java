import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class jdbcTest {

	@Test
	public void testjdbc() throws SQLException, ClassNotFoundException {
		// 1.注册驱动 用 : 分割多个驱动
		// DriverManager.deregisterDriver(new com.mysql.jdbc.Driver()); //
		// System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver"); //
		Class.forName("com.mysql.jdbc.Driver"); // 类装载到虚拟机 推荐使用

		// 2.建立连接
		String url = "jdbc:mysql://192.168.56.102:3306/mydatabase1";
		String user = "mysqluser1";
		String password = "1234";
		Connection conn = DriverManager.getConnection(url, user, password);
		// 3.创建语句
		Statement st = conn.createStatement();

		// 4.执行语句
		ResultSet rs = st.executeQuery("select * from students");

		// 5.处理结果
		while (rs.next()) {
			System.out.println(rs.getObject(1) + "\t" + rs.getObject(2));
		}

		// 6.释放资源
		rs.close();
		st.close();
		conn.close();

	}

	@Test
	public void template() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 注册驱动
			// Class.forName("com.mysql.jdbc.Driver");

			// 建立连接
			conn = JdbcUtils.getConnection();

			// 3.创建语句
			st = conn.createStatement();

			// 4.执行语句
			rs = st.executeQuery("select * from students");

			// 5.处理结果
			while (rs.next()) {
				System.out.println(rs.getObject(1) + "\t" + rs.getObject(2));
			}

		} finally {
			// 6.释放资源
			JdbcUtils.free(rs, st, conn);

		}
	}

	@Test
	public void create() throws SQLException {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 注册驱动
			// Class.forName("com.mysql.jdbc.Driver");

			// 建立连接
			conn = JdbcUtils.getConnection();

			// 3.创建语句
			st = conn.createStatement();

			String sql = "insert into students(stid,sname,gender,birthday,address) values('88','77','44','2000-01-01','addr')";
			// 4.执行语句
			int i = st.executeUpdate(sql);
			System.out.println("i= " + i);

		} finally {
			// 6.释放资源
			JdbcUtils.free(rs, st, conn);

		}
	}

	@Test
	public void read() throws SQLException {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 注册驱动
			// Class.forName("com.mysql.jdbc.Driver");

			// 建立连接
			conn = JdbcUtils.getConnection();

			// 3.创建语句
			st = conn.createStatement();

			// 4.执行语句
			rs = st.executeQuery("select stid,sname,gender,birthday,address,picture  from students");

			// 5.处理结果
			while (rs.next()) {
				System.out.println(rs.getObject("stid") + "\t" + rs.getObject("sname") + "\t" + rs.getObject("gender")
						+ "\t" + rs.getObject("birthday") + "\t" + rs.getObject("address") + "\t"
						+ rs.getObject("picture"));
			}

		} finally {
			// 6.释放资源
			JdbcUtils.free(rs, st, conn);

		}
	}

	@Test
	public void update() throws SQLException {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 注册驱动
			// Class.forName("com.mysql.jdbc.Driver");

			// 建立连接
			conn = JdbcUtils.getConnection();

			// 3.创建语句
			st = conn.createStatement();
			String sql = "update students set sname = '99999'";

			// 4.执行语句
			int i = st.executeUpdate(sql);
			System.out.println("i= " + i);

		} finally {
			// 6.释放资源
			JdbcUtils.free(rs, st, conn);

		}
	}

	@Test
	public void delete() throws SQLException {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 注册驱动
			// Class.forName("com.mysql.jdbc.Driver");

			// 建立连接
			conn = JdbcUtils.getConnection();

			// 3.创建语句
			st = conn.createStatement();

			String sql = "delete from students where gender= 'man'";
			// 4.执行语句
			int i = st.executeUpdate(sql);
			System.out.println("i= " + i);

		} finally {
			// 6.释放资源
			JdbcUtils.free(rs, st, conn);

		}
	}

	@Test
	public void testInject() throws SQLException {

		Connection conn = null;
		// Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String stid = "88";

		try {
			// 注册驱动
			// Class.forName("com.mysql.jdbc.Driver");

			// 建立连接
			conn = JdbcUtils.getConnection();

			// 3.创建语句

			String sql = "select stid,sname,gender,birthday,address,picture  from students where stid = ?";
			// st = conn.createStatement();
			ps = conn.prepareStatement(sql);
			ps.setString(1, stid);

			// 4.执行语句

			// rs = st.executeQuery(sql);
			rs = ps.executeQuery();
			// System.out.println(sql);
			// 5.处理结果
			while (rs.next()) {
				System.out.println(rs.getObject("stid") + "\t" + rs.getObject("sname") + "\t" + rs.getObject("gender")
						+ "\t" + rs.getObject("birthday") + "\t" + rs.getObject("address") + "\t"
						+ rs.getObject("picture"));
			}

		} finally {
			// 6.释放资源
			JdbcUtils.free(rs, ps, conn);

		}

	}
}
