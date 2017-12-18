import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class zhuce1 {
	public static HashMap<String, List> hashmap = new HashMap<>();
	public static List<Object> list = new ArrayList<>();
	public static List<Object> list1 = new ArrayList<>();
	public static List<Object> list2 = new ArrayList<>();
	public static int num = 4;
	public static int port = 6;
	public static int port1 = 1;
	public static String string[] = null;
	static byte[] b2;
	static byte[] b3;
	static InetAddress ip;
	static int duankou;

	public static void main(String[] args) {

		new Thread() {

			public void run() {

				try {
					Server.openServer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};

		}.start();
		new Thread() {
			public void run() {
				DatagramSocket a = null;
				try {
					a = new DatagramSocket(11111);
				} catch (SocketException e) {
					e.printStackTrace();
				}
				while (true) {
					byte[] bb = new byte[1024];
					DatagramPacket b = new DatagramPacket(bb, bb.length);
					try {
						a.receive(b);

					} catch (IOException e) {
						e.printStackTrace();
					}
					String n = new String(b.getData());
					System.out.println(n.trim());
					String[] str = n.trim().split(",");
					String fasongzhe = str[0];
					list1.add(fasongzhe);
					String jieshouzhe = str[1];
					String xiaoxi = str[2];
					list.add(b.getAddress());
					list.add(b.getPort());
					list.add(fasongzhe);
					list.add(jieshouzhe);
					list.add(xiaoxi);
					hashmap.put(fasongzhe, list);
					if (hashmap.size() >= 2) {

						if (hashmap.get(list1.get(0)).get(2).equals(hashmap.get(list1.get(1)).get(8))
								&& hashmap.get(list1.get(0)).get(3).equals(hashmap.get(list1.get(1)).get(7))) {
							System.out.println("配对成功");
							System.out.println(hashmap);
							while (true) {
								byte[] bb1 = new byte[1024];
								DatagramPacket b1 = new DatagramPacket(bb1, bb1.length);
								try {
									a.receive(b1);

								} catch (IOException e) {
									e.printStackTrace();
								}
								String n1 = new String(b1.getData());
								System.out.println(n1.trim());
								String[] str1 = n1.trim().split(",");
								String fasongzhe1 = str1[0];
								list1.add(fasongzhe1);
								String jieshouzhe1 = str1[1];
								String xiaoxi1 = str1[2];
								InetAddress dizhia = b1.getAddress();
								int duan = b1.getPort();
								list.add(b1.getAddress());
								list.add(b1.getPort());
								list.add(fasongzhe1);
								list.add(jieshouzhe1);
								list.add(xiaoxi1);
								hashmap.put(fasongzhe1, list);
								try {
									b2 = hashmap.get(list1.get(0)).get(num).toString().getBytes("utf-8");
								} catch (UnsupportedEncodingException e2) {
									e2.printStackTrace();
								}
								System.out.println(hashmap.get(list1.get(0)).get(num).toString());
								DatagramPacket b11;
								System.out.println("可以" + hashmap.get(list1.get(0)).get(0));
								if (b1.getAddress().equals(hashmap.get(list1.get(0)).get(0))) {
									try {
										b11 = new DatagramPacket(xiaoxi1.getBytes("utf-8"),
												xiaoxi1.getBytes("utf-8").length,
												(InetAddress) hashmap.get(list1.get(1)).get(5),
												(int) hashmap.get(list1.get(1)).get(6));
										try {
											a.send(b11);
										} catch (IOException e) {
											e.printStackTrace();
										}
									} catch (UnsupportedEncodingException e1) {
										e1.printStackTrace();
									}
									port = port + 10;
									System.out.println("已发送");
								} else {
									try {
										b11 = new DatagramPacket(xiaoxi1.getBytes("utf-8"),
												xiaoxi1.getBytes("utf-8").length,
												(InetAddress) hashmap.get(list1.get(0)).get(0),
												(int) hashmap.get(list1.get(0)).get(1));
										try {
											a.send(b11);
										} catch (IOException e) {
											e.printStackTrace();
										}
									} catch (UnsupportedEncodingException e1) {
										e1.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		}.start();
	};
}
