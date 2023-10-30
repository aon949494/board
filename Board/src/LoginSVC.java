import java.util.Scanner;
import java.sql.*;
import static db.JdbcUtil.*;
public class LoginSVC {
	Connection con;
	boolean ic;
	static boolean loginCheck;
	static String loginId;
	static String loginPasswd;
	static String loginEmail;
	static String loginContents;
	//�α���(üũ), ȸ������(�ߺ�üũ)
	public void idcheak(String id) {
		Scanner sc = new Scanner(System.in);
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from tmember where userid = '"+id+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("�ߺ��� ���̵��Դϴ�!");
				getjoinUser(sc);
				break;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
			close(con);
		}
	}
	public User getjoinUser(Scanner sc) {
		
		System.out.print("ID : ");
		String id = sc.next();
		idcheak(id);
		System.out.print("Name : ");
		String name = sc.next();
		System.out.print("PW : ");
		String passwd = sc.next();
		System.out.print("Email : ");
		String email = sc.next();
		
		User user = new User(id,name,passwd,email);
		return user;
	}
	public void joinUser(Scanner sc) {
		User user = getjoinUser(sc);
		con = getConnection();
		PreparedStatement pstmt = null;
		
		try{
			String sql = "INSERT INTO tmember VALUES(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPasswd());
			pstmt.setString(4, user.getEmail());
			
			int count = pstmt.executeUpdate();
			if(count>0) {
				System.out.println("ȸ�����Լ���!");
				commit(con);
			}else {
				System.out.println("insert fail!!");
				rollback(con);
			}
			}catch(Exception e) {
				System.out.println("insert fail");
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(con);
			}
	}
	public void loginUser(Scanner sc) {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		loginCheck = false;
		String sql = "select * from tmember";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("====�α���====");
			System.out.print("ID : ");
			String id = sc.next();
			System.out.print("PW : ");
			String passwd = sc.next();
			while(rs.next()) {
				//while() {
					//System.out.println(id.equals(rs.getString("userid")));
					//System.out.println(id.equals(rs.getString("passwd")));
					if(id.equals(rs.getString("userid"))){
						if(passwd.equals(rs.getString("passwd"))) {
							System.out.println(id+"���� �α����ϼ̽��ϴ�.");
							loginId = id;
							loginPasswd = passwd;
							loginEmail = rs.getString("email");
							loginCheck = true;
							break;
						}
						else {
							System.out.println("���̵� ��й�ȣ�� ��ġ�����ʽ��ϴ�!");
							break;
						}
					}
					
				}
				
			//}

		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
			close(con);
		}
		
	}
	public void userInfo() {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from tmember where userid = '"+loginId+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("ID : "+rs.getString("userid")+"\n"+
									"Name : "+rs.getString("name")+"\n"+
									"Email : "+rs.getString("email"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			close(con);
		}
	}

	public void deleteUser(Scanner sc) {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardSVC boardSVC = new BoardSVC();
		try {
			System.out.println("===ȸ��Ż��===");
			System.out.println("ID : "+loginId);
			System.out.print("PW : ");
			String passwd = sc.next();

			String sql = "select * from tmember where userid = '"+loginId+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(passwd.equals(rs.getString("passwd"))) {
					System.out.println("ȸ��Ż�� �ϸ� �ۼ��۵��� ��� �����˴ϴ�. �׷��� ȸ��Ż���Ͻðڽ��ϱ�?(y/n)");
					String yorn = sc.next();
					if(yorn.equals("y")) {
						sql = "DELETE from tmember where userid = '"+loginId+"'";
						pstmt = con.prepareStatement(sql);
						boardSVC.deleteUserArticle(loginId, loginPasswd);
						int count = pstmt.executeUpdate(sql);
						
						if(count > 0){
							System.out.println("ȸ��Ż�� ����!");
						}
						else{
							System.out.println("ȸ��Ż�� ����!");
						}
						loginCheck = false;
						break;
					}
					else if(yorn.equals("n")) {
						LoginMain loginMain = new LoginMain();
						loginMain.login(sc);
						break;
					}
					else {
						System.out.println("�ùٸ� ��ȣ�� �Է��ϼ���!");
						break;
					}
			}
				else {
					System.out.println("��й�ȣ�� ��ġ�����ʽ��ϴ�!");
					break;
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
