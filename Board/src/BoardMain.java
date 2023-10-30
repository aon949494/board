import java.util.Scanner;
public class BoardMain {

	public static void main(String[] args) {
		BoardSVC boardSVC = new BoardSVC();
		LoginSVC loginSVC = new LoginSVC();
		LoginMain loginMain = new LoginMain();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("====�Խ���====");
			System.out.println("1. ȸ������");
			System.out.println("2. �α���");
			System.out.println("3. �۸�Ϻ���");
			System.out.println("4. ����");
			System.out.print("�޴��� �����ϼ���>> ");
			String menu = sc.next();
			switch(menu) {
			case "1":
				System.out.println("====ȸ������===");
				loginSVC.joinUser(sc);
				break;
			case "2":
				loginMain.login(sc);
				break;
			case "3" :
				boolean back = false;
				while(!back) {
					boardSVC.listArticle();
					System.out.println("====�Խ���====");
					System.out.println("1. �۵��");
					System.out.println("2. �� ���뺸��");
					System.out.println("3. �� ����");
					System.out.println("4. �� ����");
					System.out.println("5. �� �˻�");
					System.out.println("6. ���ư���");
					System.out.print("�޴��� �����ϼ���>> ");
					menu = sc.next();
					switch(menu) {
					case "1":
						boardSVC.writeArticle(sc);
						break;
					case "2":
						System.out.print("�۹�ȣ�� �Է��ϼ���>> ");
						String id = sc.next();
						boardSVC.showArticle(id);
						break;
					case "3":
						System.out.print("������ �� ��ȣ�� �Է��ϼ���>> ");
						id = sc.next();
						System.out.print("�ۼ����� ��й�ȣ�� �Է��ϼ���>> ");
						String passwd = sc.next();
						boardSVC.updateArticle(id,passwd);
						break;
					case "4":
						System.out.print("������ �۹�ȣ�� �Է��ϼ���>> ");
						id = sc.next();
						System.out.print("�ۼ����� ��й�ȣ�� �Է��ϼ���>> ");
						passwd = sc.next();
						boardSVC.deleteArticle(id,passwd);
						break;
					case "5":
						System.out.println("1. �۹�ȣ�� ã��");
						System.out.println("2. �ۼ��ڷ� ã��");
						System.out.println("3. ������� ã��");
						System.out.println("4. �۳���� ã��");
						System.out.println("5. ���ư���");
						System.out.print("�޴��� �������ּ���>> ");
						menu = sc.next();
						boardSVC.searchArticle(sc, menu);
						break;
					case "6":
						back=true;
						break;
					default :
						System.out.println("�ùٸ� �޴��� �Է��ϼ���!");
						break;
					}
				}
				break;
			case "4" :
				return;
			default : 
				System.out.println("�ùٸ� �޴��� �Է��ϼ���!");
				break;
			}
			
			
		}

	}

}
