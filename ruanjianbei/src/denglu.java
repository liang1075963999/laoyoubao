import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class denglu
 */
@WebServlet("/denglu")
public class denglu extends HttpServlet {
	String a;
	String b;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public denglu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap aa = shuju.getshuju();
		a = request.getParameter("user");
		b = request.getParameter("mima");
		if (aa.get(a).equals(b))
			response.getOutputStream().write("chenggong".getBytes("gbk"));
		else
			response.getOutputStream().write("shibai".getBytes("gbk"));
		System.out.println("用户为" + a);
		System.out.println("密码为" + b);
		new Thread() {
			public void run() {
				xieru pa = new xieru();
				pa.xie(a, b);
			}
		}.start();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
