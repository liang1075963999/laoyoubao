import java.util.Date;

public class News {
	private Integer id;
	private String title;
	private Date publishDate;
	private String tupian;
	private String neirong;

	public News(Integer id, String title, Date publishDate, String tupian, String neirong) {
		this.id = id;
		this.title = title;
		this.publishDate = publishDate;
		this.tupian = tupian;
		this.neirong = neirong;
	}

	public String getNeirong() {
		return neirong;
	}

	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}

	public String getTupian() {
		return tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
}