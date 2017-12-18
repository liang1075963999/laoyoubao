import java.sql.*;

public class JDBCTest {
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
			Statement statement = conn.createStatement();
			String sql = "select * from diqige";
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("-----------------");
			String name = null;
			while (rs.next()) {
				name = rs.getString("xingming");
				name = new String(name.getBytes("ISO-8859-1"), "GB2312");
				System.out.println(rs.getString("xuehao") + "\t" + name);
			}
			rs.close();
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