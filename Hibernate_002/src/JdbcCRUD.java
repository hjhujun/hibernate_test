import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcCRUD {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		// create();
		// update();
		delete();
		read();

	}

	static void create() throws SQLException {

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

	static void read() throws SQLException {

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

	static void update() throws SQLException {

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

	static void delete() throws SQLException {

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
}
