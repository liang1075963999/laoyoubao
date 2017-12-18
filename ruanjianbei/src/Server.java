import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.*;

public class Server extends Thread {
	private Socket socket = null;
	public static String yuyinming;

	public Server(Socket socket) {
		this.socket = socket;
	}

	static HashMap pools = new HashMap();
	public HashMap<String, String> yuyin = new HashMap<>();
	public static Set<String> set = new HashSet();

	public void run() {
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			byte[] b = new byte[100];
			in.read(b);
			String cmd = new String(b).trim();
			if (cmd.startsWith("zhuce")) {
				String[] str = cmd.split(",");
				String username = str[1];
				String password = str[2];
				String ip = socket.getInetAddress().toString();
				int duankou = socket.getPort();
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://127.0.0.1:3306/yonghuxinxi";
				String user = "root";
				String password1 = "123456";
				try {
					Class.forName(driver);
					Connection conn = DriverManager.getConnection(url, user, password1);
					if (!conn.isClosed())
						System.out.println("连接数据库成功");
					Statement statement = conn.createStatement();
					String sql = "insert into xinxi(yonghuming,mima,ip,duankou) values('" + username + "','" + password
							+ "','" + ip + "'," + duankou + ");";
					boolean rs = statement.execute(sql);
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
			} else if (cmd.startsWith("login")) {
				String[] str = cmd.split(",");
				String username = str[1];
				String password = str[2];
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://127.0.0.1:3306/yonghuxinxi";
				String user = "root";
				String password1 = "123456";
				try {
					Class.forName(driver);
					Connection conn = DriverManager.getConnection(url, user, password1);
					if (!conn.isClosed())
						System.out.println("连接数据库成功");
					Statement statement = conn.createStatement();
					String sql = "select *from xinxi";
					ResultSet rs = statement.executeQuery(sql);
					System.out.println("-----------------");
					String mima = null;
					while (rs.next()) {
						if (username.equals(rs.getString("yonghuming"))) {
							if (password.equals(rs.getString("mima"))) {
								System.out.println("登陆成功");
								out.write("ok".getBytes());
								out.flush();
							}
						}
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
			} else if (cmd.startsWith("tianjia")) {
				String[] str = cmd.split(",");
				String username = str[1];
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://127.0.0.1:3306/yonghuxinxi";
				String user = "root";
				String password1 = "123456";
				try {
					Class.forName(driver);
					Connection conn = DriverManager.getConnection(url, user, password1);
					if (!conn.isClosed())
						System.out.println("连接数据库成功");
					Statement statement = conn.createStatement();
					String sql = "select *from xinxi where yonghuming='" + username + "';";
					ResultSet rs = statement.executeQuery(sql);
					System.out.println("-----------------");
					if (rs.next() != false) {
						System.out.println("用户存在");
						System.out.println(socket.getPort());
						out.write("ok".getBytes());
						out.flush();
					} else {
						System.out.println("用户不存在");
						out.write("notok".getBytes());
						out.flush();
					}
					conn.close();

				} catch (ClassNotFoundException e) {
					System.out.println("找不到驱动");
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (cmd.startsWith("upload")) {
				String s = cmd.split(",")[1];
				String username = cmd.split(",")[2];
				long size = Long.parseLong(s);
				out.write("ok".getBytes());
				out.flush();
				File ff = new File("D:\\amr", username +

				"R" + ((int) (Math.random() * 100000)) +

				".amr");
				FileOutputStream fout = new FileOutputStream(ff);
				byte[] b1 = new byte[1024];
				int len = 0;
				long length = 0;
				while ((len = in.read(b1)) != -1) {
					length += len;
					fout.write(b1, 0, len);
					if (length >= size) {
						break;
					}
				}
				fout.close();
				Pool p = new Pool();
				p.start();

			} else if (cmd.startsWith("shangchuan")) {
				String s = cmd.split(",")[1];
				String username = cmd.split(",")[2];
				long size = Long.parseLong(s);
				out.write("ok".getBytes());
				out.flush();
				File ff = new File("D:\\jpeg", username +

				"R" + ((int) (Math.random() * 100000)) +

				".jpeg");
				FileOutputStream fout = new FileOutputStream(ff);
				byte[] b1 = new byte[10000];
				int len = 0;
				long length = 0;
				while ((len = in.read(b1)) != -1) {
					length += len;
					fout.write(b1, 0, len);
					if (length >= size) {
						break;
					}
				}
				fout.close();
				System.out.println("图片文件保存成功");
			} else if (cmd.startsWith("download")) {

				String username = cmd.split(",")[1];
				File tupian = new File("D:\\jpeg");
				int i = 0;
				File[] files = tupian.listFiles();
				i = files.length;
				System.out.println("所有文件为:" + files.length);
				for (File file : files) {
					if (file.isFile() && file.getName().contains(username)) {
						System.out.println(file.getName() + username + "的文件");
						System.out.println("路径为" + file.getPath());
						out.write(("shoudao," + new File(file.getPath()).length() + "," + i).getBytes());
						out.flush();
						byte[] bb = new byte[10];
						in.read(bb);
						System.out.println(new String(bb).trim());
						FileInputStream fin = new FileInputStream(file.getPath());
						int len = 0;
						byte[] b1 = new byte[10000];
						while ((len = fin.read(b1)) != -1) {
							out.write(b1, 0, len);
							out.flush();
						}
						fin.close();
						File f = new File(file.getPath());
						f.delete();
					}
				}
			} else if (cmd.startsWith("yuyinxiazai")) {
				String username = cmd.split(",")[1];
				File tupian = new File("D:\\amr");
				int i = 0;
				File[] files = tupian.listFiles();
				i = files.length;
				System.out.println("所有文件为:" + files.length);
				for (File file : files) {
					if (file.isFile() && file.getName().contains(username)) {
						System.out.println(file.getName() + username + "的文件");
						System.out.println("路径为" + file.getPath());
						out.write(("shoudao," + new File(file.getPath()).length() + "," + i).getBytes());
						out.flush();
						byte[] bb = new byte[10];
						in.read(bb);
						System.out.println(new String(bb).trim());
						FileInputStream fin = new FileInputStream(file.getPath());
						int len = 0;
						byte[] b1 = new byte[10000];
						while ((len = fin.read(b1)) != -1) {
							out.write(b1, 0, len);
							out.flush();
						}
						fin.close();
						File f = new File(file.getPath());
						f.delete();
					}
				}
			}

		} catch (Exception e) {

		} finally {
			try {
				socket.close();
			} catch (Exception e1) {
			}
		}
	}

	public static void openServer() throws Exception {
		ServerSocket server = new ServerSocket(5555);
		while (true) {
			new Server(server.accept()).start();
			System.out.println("有访客");
		}

	}

}