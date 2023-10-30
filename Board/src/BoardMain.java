import java.util.Scanner;
public class BoardMain {

	public static void main(String[] args) {
		BoardSVC boardSVC = new BoardSVC();
		LoginSVC loginSVC = new LoginSVC();
		LoginMain loginMain = new LoginMain();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("====게시판====");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 글목록보기");
			System.out.println("4. 종료");
			System.out.print("메뉴를 선택하세요>> ");
			String menu = sc.next();
			switch(menu) {
			case "1":
				System.out.println("====회원가입===");
				loginSVC.joinUser(sc);
				break;
			case "2":
				loginMain.login(sc);
				break;
			case "3" :
				boolean back = false;
				while(!back) {
					boardSVC.listArticle();
					System.out.println("====게시판====");
					System.out.println("1. 글등록");
					System.out.println("2. 글 내용보기");
					System.out.println("3. 글 수정");
					System.out.println("4. 글 삭제");
					System.out.println("5. 글 검색");
					System.out.println("6. 돌아가기");
					System.out.print("메뉴를 선택하세요>> ");
					menu = sc.next();
					switch(menu) {
					case "1":
						boardSVC.writeArticle(sc);
						break;
					case "2":
						System.out.print("글번호를 입력하세요>> ");
						String id = sc.next();
						boardSVC.showArticle(id);
						break;
					case "3":
						System.out.print("수정할 글 번호를 입력하세요>> ");
						id = sc.next();
						System.out.print("작성자의 비밀번호를 입력하세요>> ");
						String passwd = sc.next();
						boardSVC.updateArticle(id,passwd);
						break;
					case "4":
						System.out.print("삭제할 글번호를 입력하세요>> ");
						id = sc.next();
						System.out.print("작성자의 비밀번호를 입력하세요>> ");
						passwd = sc.next();
						boardSVC.deleteArticle(id,passwd);
						break;
					case "5":
						System.out.println("1. 글번호로 찾기");
						System.out.println("2. 작성자로 찾기");
						System.out.println("3. 글제목로 찾기");
						System.out.println("4. 글내용로 찾기");
						System.out.println("5. 돌아가기");
						System.out.print("메뉴를 선택해주세요>> ");
						menu = sc.next();
						boardSVC.searchArticle(sc, menu);
						break;
					case "6":
						back=true;
						break;
					default :
						System.out.println("올바른 메뉴를 입력하세요!");
						break;
					}
				}
				break;
			case "4" :
				return;
			default : 
				System.out.println("올바른 메뉴를 입력하세요!");
				break;
			}
			
			
		}

	}

}
