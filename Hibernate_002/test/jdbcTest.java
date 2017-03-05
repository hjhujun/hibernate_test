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
		// 1.ע������ �� : �ָ�������
		// DriverManager.deregisterDriver(new com.mysql.jdbc.Driver()); //
		// System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver"); //
		Class.forName("com.mysql.jdbc.Driver"); // ��װ�ص������ �Ƽ�ʹ��

		// 2.��������
		String url = "jdbc:mysql://192.168.56.102:3306/mydatabase1";
		String user = "mysqluser1";
		String password = "1234";
		Connection conn = DriverManager.getConnection(url, user, password);
		// 3.�������
		Statement st = conn.createStatement();

		// 4.ִ�����
		ResultSet rs = st.executeQuery("select * from students");

		// 5.������
		while (rs.next()) {
			System.out.println(rs.getObject(1) + "\t" + rs.getObject(2));
		}

		// 6.�ͷ���Դ
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
			// ע������
			// Class.forName("com.mysql.jdbc.Driver");

			// ��������
			conn = JdbcUtils.getConnection();

			// 3.�������
			st = conn.createStatement();

			// 4.ִ�����
			rs = st.executeQuery("select * from students");

			// 5.������
			while (rs.next()) {
				System.out.println(rs.getObject(1) + "\t" + rs.getObject(2));
			}

		} finally {
			// 6.�ͷ���Դ
			JdbcUtils.free(rs, st, conn);

		}
	}

	@Test
	public void create() throws SQLException {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// ע������
			// Class.forName("com.mysql.jdbc.Driver");

			// ��������
			conn = JdbcUtils.getConnection();

			// 3.�������
			st = conn.createStatement();

			String sql = "insert into students(stid,sname,gender,birthday,address) values('88','77','44','2000-01-01','addr')";
			// 4.ִ�����
			int i = st.executeUpdate(sql);
			System.out.println("i= " + i);

		} finally {
			// 6.�ͷ���Դ
			JdbcUtils.free(rs, st, conn);

		}
	}

	@Test
	public void read() throws SQLException {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// ע������
			// Class.forName("com.mysql.jdbc.Driver");

			// ��������
			conn = JdbcUtils.getConnection();

			// 3.�������
			st = conn.createStatement();

			// 4.ִ�����
			rs = st.executeQuery("select stid,sname,gender,birthday,address,picture  from students");

			// 5.������
			while (rs.next()) {
				System.out.println(rs.getObject("stid") + "\t" + rs.getObject("sname") + "\t" + rs.getObject("gender")
						+ "\t" + rs.getObject("birthday") + "\t" + rs.getObject("address") + "\t"
						+ rs.getObject("picture"));
			}

		} finally {
			// 6.�ͷ���Դ
			JdbcUtils.free(rs, st, conn);

		}
	}

	@Test
	public void update() throws SQLException {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// ע������
			// Class.forName("com.mysql.jdbc.Driver");

			// ��������
			conn = JdbcUtils.getConnection();

			// 3.�������
			st = conn.createStatement();
			String sql = "update students set sname = '99999'";

			// 4.ִ�����
			int i = st.executeUpdate(sql);
			System.out.println("i= " + i);

		} finally {
			// 6.�ͷ���Դ
			JdbcUtils.free(rs, st, conn);

		}
	}

	@Test
	public void delete() throws SQLException {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// ע������
			// Class.forName("com.mysql.jdbc.Driver");

			// ��������
			conn = JdbcUtils.getConnection();

			// 3.�������
			st = conn.createStatement();

			String sql = "delete from students where gender= 'man'";
			// 4.ִ�����
			int i = st.executeUpdate(sql);
			System.out.println("i= " + i);

		} finally {
			// 6.�ͷ���Դ
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
			// ע������
			// Class.forName("com.mysql.jdbc.Driver");

			// ��������
			conn = JdbcUtils.getConnection();

			// 3.�������

			String sql = "select stid,sname,gender,birthday,address,picture  from students where stid = ?";
			// st = conn.createStatement();
			ps = conn.prepareStatement(sql);
			ps.setString(1, stid);

			// 4.ִ�����

			// rs = st.executeQuery(sql);
			rs = ps.executeQuery();
			// System.out.println(sql);
			// 5.������
			while (rs.next()) {
				System.out.println(rs.getObject("stid") + "\t" + rs.getObject("sname") + "\t" + rs.getObject("gender")
						+ "\t" + rs.getObject("birthday") + "\t" + rs.getObject("address") + "\t"
						+ rs.getObject("picture"));
			}

		} finally {
			// 6.�ͷ���Դ
			JdbcUtils.free(rs, ps, conn);

		}

	}
}
