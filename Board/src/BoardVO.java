
public class BoardVO {
	private int id;
	private String writer;
	private String passwd;
	private String subject;
	private String email;
	private String contents;
	
	public BoardVO(int id, String writer, String passwd, String subject, String email, String contents) {
		super();
		this.id = id;
		this.writer = writer;
		this.passwd = passwd;
		this.subject = subject;
		this.email = email;
		this.contents = contents;
	}
	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "BoardVO [게시글 번호=" + id + ", 작성자=" + writer +  ", 제목=" + subject + ", 이메일="+ email + ", 글내용="+contents+"]";
	}
	

}
