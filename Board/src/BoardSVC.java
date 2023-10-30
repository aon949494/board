import java.sql.*;
import java.util.Scanner;
import static db.JdbcUtil.*;//static인 클래스들을 메소드없이 가능하게해줌

public class BoardSVC {
	Connection con;
	
	//게시물등록, 목록조회, 게시글리스트, 게시글수정, 게시글삭제,회원가입로그인(유저테이블생성)
	
	public BoardVO getBoardVO(Scanner sc) {
		Scanner sc2 = new Scanner(System.in);
		System.out.println("===게시물 등록===");
		System.out.print("작성자 : ");
		String writer = sc.next();
		System.out.print("비밀번호 : ");
		String passwd = sc.next();
		System.out.print("제목 : ");
		String subject = sc2.nextLine();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("글내용 : ");
		String contents = sc2.nextLine();
		
		
		BoardVO boardVO = new BoardVO(0, writer, passwd, subject, email, contents);
		return boardVO;
	}
	public BoardVO getLBoardVO(Scanner sc) {
		Scanner sc2 = new Scanner(System.in);
		LoginSVC loginSVC = new LoginSVC();
		System.out.println("===게시물 등록===");
		System.out.println("작성자 : "+ loginSVC.loginId);
		System.out.print("제목 : ");
		String subject = sc2.nextLine();
		System.out.print("글내용 : ");
		String contents = sc2.nextLine();
		
		BoardVO boardVO = new BoardVO(0, loginSVC.loginId, loginSVC.loginPasswd, subject, loginSVC.loginEmail, contents);
		return boardVO;
	}
	public void loginwriteArticle(Scanner sc) {
		BoardVO boardVO = getLBoardVO(sc);
		con = getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board VALUES(board_seq.nextval, ?,?,?,?,?)";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardVO.getWriter());
			pstmt.setString(2, boardVO.getPasswd());
			pstmt.setString(3, boardVO.getSubject());
			pstmt.setString(4, boardVO.getEmail());
			pstmt.setString(5, boardVO.getContents());
			
			int count = pstmt.executeUpdate();
			if(count>0) {
				commit(con);
			}else {
				rollback(con);
			}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(con);
			}
	}
	public void writeArticle(Scanner sc) {
		BoardVO boardVO = getBoardVO(sc);
		con = getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board VALUES(board_seq.nextval, ?,?,?,?,?)";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardVO.getWriter());
			pstmt.setString(2, boardVO.getPasswd());
			pstmt.setString(3, boardVO.getSubject());
			pstmt.setString(4, boardVO.getEmail());
			pstmt.setString(5, boardVO.getContents());
			
			int count = pstmt.executeUpdate();
			if(count>0) {
				commit(con);
			}else {
				rollback(con);
			}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(con);
			}
	}

	public void showArticle(String id) {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//String sql = "SELECT * from board";
			String sql = "SELECT * from board where id = ?";
			Scanner sc = new Scanner(System.in);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("---글내용--------------------------------");
			if(rs.next()) {
				do {
					System.out.println("|글번호 	"+rs.getString("id")+ "\n"+
							"|작성자 	"+rs.getString("writer")+ "\n"+
							"|제목 	"+rs.getString("subject")+"\n"+
							"|이메일 	"+rs.getString("email")+"\n"+
							"|글내용 	"+rs.getString("contents"));
					
				}while(rs.next());
			}
			else {
				System.out.println("올바른번호를 입력해주세요!");
			}
			System.out.println("---------------------------------------");
			System.out.println();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			close(con);
		}
	}
	public void listArticleid(Scanner sc,String id) {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from board where id like '%"+id+"%'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
				if(rs.next()) {
					do {
						System.out.println("글번호 = "+rs.getString("id")+ "\n"+
								"작성자 = "+rs.getString("writer")+ "\n"+
								"제목 = "+rs.getString("subject")+"\n"+
								"이메일 = "+rs.getString("email")+"\n"+
								"글내용 = "+rs.getString("contents"));
						System.out.println();
					}while(rs.next());
					System.out.println();
				}
				else {
					System.out.println("검색결과없음");
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			close(con);
		}
	}
	public void listArticlewriter(Scanner sc,String writer) {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from board where writer like '%"+writer+"%'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
				if(rs.next()) {
					do {
						System.out.println("글번호 = "+rs.getString("id")+ "\n"+
								"작성자 = "+rs.getString("writer")+ "\n"+
								"제목 = "+rs.getString("subject")+"\n"+
								"이메일 = "+rs.getString("email")+"\n"+
								"글내용 = "+rs.getString("contents"));
						System.out.println();
					}while(rs.next());
					System.out.println();
				}
				else {
					System.out.println("검색결과없음");
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			close(con);
		}
	}
	public void listArticlesubject(Scanner sc,String subject) {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from board where subject like '%"+subject+"%'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
				if(rs.next()) {
					do {
						System.out.println("글번호 = "+rs.getString("id")+ "\n"+
								"작성자 = "+rs.getString("writer")+ "\n"+
								"제목 = "+rs.getString("subject")+"\n"+
								"이메일 = "+rs.getString("email")+"\n"+
								"글내용 = "+rs.getString("contents"));
						System.out.println();
					}while(rs.next());
					System.out.println();
				}
				else {
					System.out.println("검색결과없음");
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			close(con);
		}
	}
	public void listArticlecontents(Scanner sc,String contents) {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from board where contents like '%"+contents+"%'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
				if(rs.next()) {
					do {
						System.out.println("글번호 = "+rs.getString("id")+ "\n"+
								"작성자 = "+rs.getString("writer")+ "\n"+
								"제목 = "+rs.getString("subject")+"\n"+
								"이메일 = "+rs.getString("email")+"\n"+
								"글내용 = "+rs.getString("contents"));
						System.out.println();
					}while(rs.next());
					System.out.println();
				}
				else {
					System.out.println("검색결과없음");
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			close(con);
		}
	}
	public void listArticle() {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * from board order by id";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("---글목록-------------------------------------------");
			if(rs.next()) {
				do {
					System.out.println("글번호  "+rs.getString("id")+
										" 작성자  "+rs.getString("writer")+
										" 제목  "+rs.getString("subject"));
				}while(rs.next());
			}
			else {
				System.out.println("작성된글이 없습니다.");
			}
			System.out.println("--------------------------------------------------");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			close(con);
		}
	}
	
	public void loginlistArticle() {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from board where writer = '"+LoginSVC.loginId+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					System.out.println("글번호 = "+rs.getString("id")+"작성자 = "+rs.getString("writer")+"제목 = "+rs.getString("subject"));
				}while(rs.next());
			}
			else {
				System.out.println("작성된글이 없습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			close(con);
		}
	}
	public void updateArticle(String id, String passwd) {
		Scanner sc = new Scanner(System.in);
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			if(id.equals(rs.getString("id"))) {
				if(passwd.equals(rs.getString("passwd"))) {
					System.out.println("작성자 = "+rs.getString("writer"));
					System.out.print("제목 : ");
					String subject = sc.nextLine();
					System.out.print("글내용 : ");
					String contents = sc.nextLine();
					sql = "UPDATE board set subject = '"+subject+"', contents = '"
														+contents+"' where id = '"+id+"'";
					int count = pstmt.executeUpdate(sql);
					if(count > 0){
						System.out.println("수정 성공!");
						break;
					}
					else{
						System.out.println("수정 실패!");
						break;
					}
				}else {
					System.out.println("비밀번호가 일치하지 않습니다!");
				}
			}
			}
				
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
		close(rs);
		close(con);
	}
	}
	public void searchArticle(Scanner sc, String menu) {
		Scanner sc2 = new Scanner(System.in);
		con = getConnection();
			switch(menu) {
			case "1" :
				System.out.println("id : ");
				String id = sc.next();
				listArticleid(sc,id);
				break;
			case "2" :
				System.out.print("작성자:");
				String writer = sc.next();
				listArticlewriter(sc,writer);
				break;
			case "3" :
				System.out.print("글제목 : ");
				String subject = sc2.nextLine();
				listArticlesubject(sc,subject);
				break;
			case "4" :
				System.out.print("글내용 : ");
				String contents = sc2.nextLine();
				listArticlecontents(sc,contents);
				break;
			case "5" :
				break;
			default :
				System.out.println("올바른 메뉴를 입력하세요!");
				break;
			}
	}
	public void deleteArticle(String id, String passwd) {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
					if(id.equals(rs.getString("id"))) {
						if(passwd.equals(rs.getString("passwd"))) {
							sql = "DELETE from board where id = '"+id+"'";
							int count = pstmt.executeUpdate(sql);
							if(count > 0){
								System.out.println("삭제 성공!");
								break;
							}
							else{
								System.out.println("삭제 실패!");
								break;
							}
					}
						else {
							System.out.println("비밀번호가 일치하지않습니다!");
							break;
						}
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			close(con);
		}
	}
	public void deleteUserArticle(String userid, String passwd) {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
					if(userid.equals(rs.getString("writer"))) {
						if(passwd.equals(rs.getString("passwd"))) {
							sql = "DELETE from board where writer = '"+userid+"'";
							int count = pstmt.executeUpdate(sql);
							if(count > 0){
								System.out.println("삭제 성공!");
								break;
							}
							else{
								System.out.println("삭제 실패!");
								break;
							}
					}
						else {
							System.out.println("비밀번호가 일치하지않습니다!");
							break;
						}
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			close(con);
		}
	}
	
}
