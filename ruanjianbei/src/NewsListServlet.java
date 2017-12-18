import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NewsListServlet")
public class NewsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsServiceBean newsService = new NewsServiceBean();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<News> newes = newsService.getLastNews();
		StringBuilder sbJson = new StringBuilder();
		sbJson.append('[');
		for (News news : newes) {
			sbJson.append('{');
			sbJson.append("id:").append(news.getId()).append(",");
			sbJson.append("title:\"").append(news.getTitle()).append("\",");
			sbJson.append("publishDate:").append(news.getPublishDate().getTime()).append(",");
			sbJson.append("tupian:\"").append(news.getTupian()).append("\",");
			sbJson.append("neirong:\"").append(news.getNeirong()).append("\"");
			sbJson.append("},");
		}
		sbJson.deleteCharAt(sbJson.length() - 1);
		sbJson.append(']');
		request.setAttribute("json", sbJson.toString());
		request.getRequestDispatcher("WEB-INF/xinwen.jsp").forward(request, response);
	}
}