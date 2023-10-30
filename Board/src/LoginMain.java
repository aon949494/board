import java.util.Scanner;
import java.sql.*;
import static db.JdbcUtil.*;

public class LoginMain {
	LoginSVC loginSVC = new LoginSVC();
	BoardSVC boardSVC = new BoardSVC();
	//BoardMain boardMain = new BoardMain();
	public void list(Scanner sc) {
		boolean returns = false;
		do {
			boardSVC.listArticle();
			System.out.println("1. 글 등록");
			System.out.println("2. 글 수정");
			System.out.println("3. 글 삭제");
			System.out.println("4. 글 검색");
			System.out.println("5. 작성글 보기");
			System.out.println("6. 돌아가기");
			System.out.print("메뉴를 선택하세요>> ");
			String menu = sc.next();
			switch(menu) {
			case "1":
				boardSVC.loginwriteArticle(sc);
				break;
			case "2":
				System.out.print("수정할 글 번호를 입력하세요>> ");
				String id = sc.next();
				System.out.print("작성자의 비밀번호를 입력하세요>> ");
				String passwd = sc.next();
				boardSVC.updateArticle(id,passwd);
				break;
			case "3":
				System.out.print("삭제할 글번호를 입력하세요>> ");
				id = sc.next();
				System.out.print("작성자의 비밀번호를 입력하세요>> ");
				passwd = sc.next();
				boardSVC.deleteArticle(id,passwd);
				break;
			case "4":
				System.out.println("1. 글번호로 찾기");
				System.out.println("2. 작성자로 찾기");
				System.out.println("3. 글제목로 찾기");
				System.out.println("4. 글내용로 찾기");
				System.out.println("5. 돌아가기");
				System.out.print("메뉴를 선택하세요>> ");
				menu = sc.next();
				boardSVC.searchArticle(sc, menu);
				break;
			case "5":
				do {
					boardSVC.loginlistArticle();
					System.out.println("1. 글내용보기");
					System.out.println("2. 수정");
					System.out.println("3. 삭제");
					System.out.println("4. 돌아가기");
					System.out.print("메뉴를 선택하세요>> ");
					menu = sc.next();
					switch(menu) {
					case "1":
						System.out.println("글번호를 입력하세요>> ");
						id = sc.next();
						boardSVC.showArticle(id);
						break;
					case "2":
						System.out.print("수정할 글 번호를 입력하세요>> ");
						id = sc.next();
						System.out.print("작성자의 비밀번호를 입력하세요>> ");
						passwd = sc.next();
						boardSVC.updateArticle(id,passwd);
						break;
					case "3":
						System.out.print("삭제할 글번호를 입력하세요>> ");
						id = sc.next();
						System.out.print("작성자의 비밀번호를 입력하세요>> ");
						passwd = sc.next();
						boardSVC.deleteArticle(id,passwd);
						break;
					case "4":
						returns = true;
						break;
					default :
						System.out.println("올바른 메뉴를 입력하세요!");
						break;
					}
				}
				while(!returns);
				break;
			case "6":
				return;
			default :
				System.out.println("올바른 메뉴를 입력하세요!");
				break;
		}
		}
		while(true);
	}
	public void login(Scanner sc) {
		if(loginSVC.loginCheck==false) {
			loginSVC.loginUser(sc);
		}
		boolean returns = false;
		if(loginSVC.loginCheck==true) {
			while(loginSVC.loginCheck) {
				System.out.println("===="+loginSVC.loginId+"====");
				System.out.println("1. 내정보보기");
				System.out.println("2. 글 목록");
				System.out.println("3. 로그아웃");
				System.out.println("4. 회원탈퇴");
				System.out.print("메뉴를 선택하세요>> ");
				String menu = sc.next();
				switch(menu) {
				case "1":
					loginSVC.userInfo();
					break;
				case "2":
					list(sc);
					break;
				case "3":
					loginSVC.loginCheck=false;
					break;
				case "4":
					loginSVC.deleteUser(sc);
					break;
				default :
					System.out.println("올바른 메뉴를 입력하세요!");
					break;
				}
			}
			
		}
	}
	
}
