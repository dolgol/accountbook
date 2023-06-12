package main;

import java.util.Scanner;

import dao.AccountDao;
import file.FileProc;

public class MainClass {

	public static void main(String[] args) {

		System.out.println("~~~~~가계부~~~~~");
		
		Scanner sc = new Scanner(System.in);
		AccountDao dao = new AccountDao();
		FileProc fp = new FileProc("accountbook");
		
		fp.read();

		boolean play = true;
		while (play) {
			System.out.println("---------------");
			System.out.println("1. 추가");
			System.out.println("2. 삭제");
			System.out.println("3. 검색");
			System.out.println("4. 수정");
			System.out.println("5. 출력");
			System.out.println("6. 저장");
			System.out.println("7. 종료");

			System.out.print("메뉴 번호 입력 >> ");
			int number = sc.nextInt();
			
			switch (number) {
			case 1:
				dao.insert();
				break;
			case 2:
				dao.delete();
				break;
			case 3:
				dao.select();
				break;
			case 4:
				dao.update();
				break;
			case 5:
				dao.allDataPrint();
				break;
			case 6:
				fp.write();
				break;
			case 7:
				System.out.println("~~~~~~~~~~~~~~");
				play = false;
				break;
			default:
				break;
			}
		}

	}

}
