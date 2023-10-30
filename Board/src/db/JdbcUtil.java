package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","java","java");
			con.setAutoCommit(false);//자동으로 커밋하는것.지금은 자동으로 안하겠다는 뜻
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void close(Connection con) {
		try {
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void close(Statement con) {
		try {
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void close(ResultSet con) {
		try {
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void commit(Connection con) {
		try {
			con.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void rollback(Connection con) {
		try {
			con.rollback();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
