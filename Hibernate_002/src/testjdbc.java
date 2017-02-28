	import java.sql.DriverManager;
	import java.sql.Connection;

public class testjdbc {



	    public static void main(String[] args) {
	        String url = "jdbc:mysql://192.168.56.102:3306/mydatabase1";
	        String userName = "mysqluser1";
	        String password = "1234";

	        try{
	            System.out.println("Begin to connect to the demo database ...");
	            Connection conn = DriverManager.getConnection(url, userName,password);
	            System.out.println("Successfully get connection by url " + url);
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
	    
	}
}
