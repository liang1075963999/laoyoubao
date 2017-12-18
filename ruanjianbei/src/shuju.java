import java.util.HashMap;

public class shuju {
	static HashMap p = new HashMap();

	public static void putshuju(String a, String b) {
		p.put(a, b);
	}

	public static HashMap getshuju() {
		return p;
	}
}
