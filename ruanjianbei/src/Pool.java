import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Pool extends Thread {

	public void run() {
		System.out.println("�����ļ�����ɹ�");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
	}
}