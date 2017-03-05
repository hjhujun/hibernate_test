import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcUtils {
	private static String url = "jdbc:mysql://192.168.56.102:3306/mydatabase1";
	private static String user = "mysqluser1";
	private static String password = "1234";
	
	private JdbcUtils(){
		
	}
	//静态代码块 只会在装载到虚拟机的时候执行一次
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
		
	}
	
	
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,user,password);

	}
	
	
	public static void free(ResultSet rs, Statement st, Connection conn ){
		try{
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				if(st != null)
					st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
					if(conn != null)
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
			
		}
	}
}

