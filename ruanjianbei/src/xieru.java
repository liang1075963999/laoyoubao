import java.sql.*;

public class xieru {
	static String name;
	static String mima;

	public static void xie(String a, String b) {
		name = a;
		mima = b;
	}

	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/yonghuxinxi";
		String user = "root";
		String password = "123456";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed())
				System.out.println("连接数据库成功");
			System.out.println(name + mima);
			Statement statement = conn.createStatement();
			String sql = "insert into xinxi(yonghuming,mima) values('" + name + "','" + mima + "');";
			statement.execute(sql);
			System.out.println("-----------------");
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}