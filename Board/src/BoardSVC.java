import java.sql.*;
import java.util.Scanner;
import static db.JdbcUtil.*;//static�� Ŭ�������� �޼ҵ���� �����ϰ�����

public class BoardSVC {
	Connection con;
	
	//�Խù����, �����ȸ, �Խñ۸���Ʈ, �Խñۼ���, �Խñۻ���,ȸ�����Էα���(�������̺����)
	
	public BoardVO getBoardVO(Scanner sc) {
		Scanner sc2 = new Scanner(System.in);
		System.out.println("===�Խù� ���===");
		System.out.print("�ۼ��� : ");
		String writer = sc.next();
		System.out.print("��й�ȣ : ");
		String passwd = sc.next();
		System.out.print("���� : ");
		String subject = sc2.nextLine();
		System.out.print("�̸��� : ");
		String email = sc.next();
		System.out.print("�۳��� : ");
		String contents = sc2.nextLine();
		
		
		BoardVO boardVO = new BoardVO(0, writer, passwd, subject, email, contents);
		return boardVO;
	}
	public BoardVO getLBoardVO(Scanner sc) {
		Scanner sc2 = new Scanner(System.in);
		LoginSVC loginSVC = new LoginSVC();
		System.out.println("===�Խù� ���===");
		System.out.println("�ۼ��� : "+ loginSVC.loginId);
		System.out.print("���� : ");
		String subject = sc2.nextLine();
		System.out.print("�۳��� : ");
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
			System.out.println("---�۳���--------------------------------");
			if(rs.next()) {
				do {
					System.out.println("|�۹�ȣ 	"+rs.getString("id")+ "\n"+
							"|�ۼ��� 	"+rs.getString("writer")+ "\n"+
							"|���� 	"+rs.getString("subject")+"\n"+
							"|�̸��� 	"+rs.getString("email")+"\n"+
							"|�۳��� 	"+rs.getString("contents"));
					
				}while(rs.next());
			}
			else {
				System.out.println("�ùٸ���ȣ�� �Է����ּ���!");
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
						System.out.println("�۹�ȣ = "+rs.getString("id")+ "\n"+
								"�ۼ��� = "+rs.getString("writer")+ "\n"+
								"���� = "+rs.getString("subject")+"\n"+
								"�̸��� = "+rs.getString("email")+"\n"+
								"�۳��� = "+rs.getString("contents"));
						System.out.println();
					}while(rs.next());
					System.out.println();
				}
				else {
					System.out.println("�˻��������");
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
						System.out.println("�۹�ȣ = "+rs.getString("id")+ "\n"+
								"�ۼ��� = "+rs.getString("writer")+ "\n"+
								"���� = "+rs.getString("subject")+"\n"+
								"�̸��� = "+rs.getString("email")+"\n"+
								"�۳��� = "+rs.getString("contents"));
						System.out.println();
					}while(rs.next());
					System.out.println();
				}
				else {
					System.out.println("�˻��������");
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
						System.out.println("�۹�ȣ = "+rs.getString("id")+ "\n"+
								"�ۼ��� = "+rs.getString("writer")+ "\n"+
								"���� = "+rs.getString("subject")+"\n"+
								"�̸��� = "+rs.getString("email")+"\n"+
								"�۳��� = "+rs.getString("contents"));
						System.out.println();
					}while(rs.next());
					System.out.println();
				}
				else {
					System.out.println("�˻��������");
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
						System.out.println("�۹�ȣ = "+rs.getString("id")+ "\n"+
								"�ۼ��� = "+rs.getString("writer")+ "\n"+
								"���� = "+rs.getString("subject")+"\n"+
								"�̸��� = "+rs.getString("email")+"\n"+
								"�۳��� = "+rs.getString("contents"));
						System.out.println();
					}while(rs.next());
					System.out.println();
				}
				else {
					System.out.println("�˻��������");
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
			System.out.println("---�۸��-------------------------------------------");
			if(rs.next()) {
				do {
					System.out.println("�۹�ȣ  "+rs.getString("id")+
										" �ۼ���  "+rs.getString("writer")+
										" ����  "+rs.getString("subject"));
				}while(rs.next());
			}
			else {
				System.out.println("�ۼ��ȱ��� �����ϴ�.");
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
					System.out.println("�۹�ȣ = "+rs.getString("id")+"�ۼ��� = "+rs.getString("writer")+"���� = "+rs.getString("subject"));
				}while(rs.next());
			}
			else {
				System.out.println("�ۼ��ȱ��� �����ϴ�.");
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
					System.out.println("�ۼ��� = "+rs.getString("writer"));
					System.out.print("���� : ");
					String subject = sc.nextLine();
					System.out.print("�۳��� : ");
					String contents = sc.nextLine();
					sql = "UPDATE board set subject = '"+subject+"', contents = '"
														+contents+"' where id = '"+id+"'";
					int count = pstmt.executeUpdate(sql);
					if(count > 0){
						System.out.println("���� ����!");
						break;
					}
					else{
						System.out.println("���� ����!");
						break;
					}
				}else {
					System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�!");
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
				System.out.print("�ۼ���:");
				String writer = sc.next();
				listArticlewriter(sc,writer);
				break;
			case "3" :
				System.out.print("������ : ");
				String subject = sc2.nextLine();
				listArticlesubject(sc,subject);
				break;
			case "4" :
				System.out.print("�۳��� : ");
				String contents = sc2.nextLine();
				listArticlecontents(sc,contents);
				break;
			case "5" :
				break;
			default :
				System.out.println("�ùٸ� �޴��� �Է��ϼ���!");
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
								System.out.println("���� ����!");
								break;
							}
							else{
								System.out.println("���� ����!");
								break;
							}
					}
						else {
							System.out.println("��й�ȣ�� ��ġ�����ʽ��ϴ�!");
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
								System.out.println("���� ����!");
								break;
							}
							else{
								System.out.println("���� ����!");
								break;
							}
					}
						else {
							System.out.println("��й�ȣ�� ��ġ�����ʽ��ϴ�!");
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
