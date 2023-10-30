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
			System.out.println("1. �� ���");
			System.out.println("2. �� ����");
			System.out.println("3. �� ����");
			System.out.println("4. �� �˻�");
			System.out.println("5. �ۼ��� ����");
			System.out.println("6. ���ư���");
			System.out.print("�޴��� �����ϼ���>> ");
			String menu = sc.next();
			switch(menu) {
			case "1":
				boardSVC.loginwriteArticle(sc);
				break;
			case "2":
				System.out.print("������ �� ��ȣ�� �Է��ϼ���>> ");
				String id = sc.next();
				System.out.print("�ۼ����� ��й�ȣ�� �Է��ϼ���>> ");
				String passwd = sc.next();
				boardSVC.updateArticle(id,passwd);
				break;
			case "3":
				System.out.print("������ �۹�ȣ�� �Է��ϼ���>> ");
				id = sc.next();
				System.out.print("�ۼ����� ��й�ȣ�� �Է��ϼ���>> ");
				passwd = sc.next();
				boardSVC.deleteArticle(id,passwd);
				break;
			case "4":
				System.out.println("1. �۹�ȣ�� ã��");
				System.out.println("2. �ۼ��ڷ� ã��");
				System.out.println("3. ������� ã��");
				System.out.println("4. �۳���� ã��");
				System.out.println("5. ���ư���");
				System.out.print("�޴��� �����ϼ���>> ");
				menu = sc.next();
				boardSVC.searchArticle(sc, menu);
				break;
			case "5":
				do {
					boardSVC.loginlistArticle();
					System.out.println("1. �۳��뺸��");
					System.out.println("2. ����");
					System.out.println("3. ����");
					System.out.println("4. ���ư���");
					System.out.print("�޴��� �����ϼ���>> ");
					menu = sc.next();
					switch(menu) {
					case "1":
						System.out.println("�۹�ȣ�� �Է��ϼ���>> ");
						id = sc.next();
						boardSVC.showArticle(id);
						break;
					case "2":
						System.out.print("������ �� ��ȣ�� �Է��ϼ���>> ");
						id = sc.next();
						System.out.print("�ۼ����� ��й�ȣ�� �Է��ϼ���>> ");
						passwd = sc.next();
						boardSVC.updateArticle(id,passwd);
						break;
					case "3":
						System.out.print("������ �۹�ȣ�� �Է��ϼ���>> ");
						id = sc.next();
						System.out.print("�ۼ����� ��й�ȣ�� �Է��ϼ���>> ");
						passwd = sc.next();
						boardSVC.deleteArticle(id,passwd);
						break;
					case "4":
						returns = true;
						break;
					default :
						System.out.println("�ùٸ� �޴��� �Է��ϼ���!");
						break;
					}
				}
				while(!returns);
				break;
			case "6":
				return;
			default :
				System.out.println("�ùٸ� �޴��� �Է��ϼ���!");
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
				System.out.println("1. ����������");
				System.out.println("2. �� ���");
				System.out.println("3. �α׾ƿ�");
				System.out.println("4. ȸ��Ż��");
				System.out.print("�޴��� �����ϼ���>> ");
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
					System.out.println("�ùٸ� �޴��� �Է��ϼ���!");
					break;
				}
			}
			
		}
	}
	
}
