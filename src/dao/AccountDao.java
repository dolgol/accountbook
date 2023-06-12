package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import dto.AccountDto;
import singleton.Singleton;

public class AccountDao {

	Scanner sc = new Scanner(System.in);
	Singleton s = Singleton.getInstance();

	public AccountDao() {
	}

	// 추가
	public void insert() {
		System.out.print("날짜 ex)230609 >> ");
		String date = sc.next();

		System.out.print("용도(제목) >> ");
		String title = sc.next();

		System.out.print("수입/지출 >> ");
		String type = sc.next();

		System.out.print("금액 >> ");
		int price = sc.nextInt();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("내용(상세) >> ");
//		String detail = sc.next();
		String detail = "";
		try {
			detail = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		AccountDto dto = new AccountDto(date, title, type, price, detail);
		s.accountList.add(dto);
	}

	// 삭제
	public void delete() {

		System.out.print("삭제할 용도(제목) 입력 >> ");
		String search = sc.next();

		boolean isFind = false;

		for (int i = 0; i < s.accountList.size(); i++) {
			if (search.equals(s.accountList.get(i).getTitle())) { // 검색어랑 정확히 일치해야함

				System.out.println(s.accountList.get(i).toString());
				s.accountList.remove(i);
				System.out.println("위 항목 삭제됨");

				isFind = true;
				break; // 중복되는 제목이 있어도 제일 처음 것을 찾고 하나만 삭제한 뒤 탈출하게 해놓은 상태 -->
			}
		}

		if (isFind == false) {
			System.out.println("일치하는 항목 없음");
		}

	}

	// 검색
	public void select() {

		System.out.println("---------------");
		System.out.println("1. 용도(제목) 검색");
		System.out.println("2. 날짜 검색");
		System.out.println("3. 월별 결산");
		System.out.println("4. 지정 기간별 결산");
		System.out.print("검색 방법 선택 >> ");

		int select = sc.nextInt();
		boolean isFind = false;

		switch (select) {
		case 1: // 1. 용도(제목) 검색어 포함 전부
			System.out.print("검색할 용도(제목) 입력 >> ");
			String search = sc.next();

			for (int i = 0; i < s.accountList.size(); i++) {
				if (s.accountList.get(i).getTitle().contains(search)) {
					System.out.println(s.accountList.get(i).toString());
					isFind = true;
				}
			}

			break;
		case 2: // 2. 해당 날짜 전부
			System.out.print("검색할 날짜 입력 ex)230609 >> ");
			search = sc.next();

			for (int i = 0; i < s.accountList.size(); i++) {
				if (s.accountList.get(i).getDate().equals(search)) {
					System.out.println(s.accountList.get(i).toString());
					isFind = true;
				}
			}

			break;
		case 3: // 3. 월별결산
			System.out.print("원하는 월 입력 >> ");
			int searchMonth = sc.nextInt();

			int income = 0;
			int spending = 0;

			for (int i = 0; i < s.accountList.size(); i++) {
				int month = Integer.parseInt(s.accountList.get(i).getDate().substring(2, 4));
				if (month == searchMonth) {
					isFind = true;

					if (s.accountList.get(i).getType().equals("수입")) {
						income += s.accountList.get(i).getPrice();
					} else { // .equals("지출")
						spending += s.accountList.get(i).getPrice();
					}
				}
			}

			System.out.println(searchMonth + "월 결산 : 수입 " + income + "원 / 지출 " + spending + "원");
			break;
		case 4: // 4. 주어진 기간별 결산
			System.out.print("시작 날짜 입력 ex)230609 >> ");
			String date01 = sc.next();
			System.out.print("마지막 날짜 입력 ex)230609 >> ");
			String date02 = sc.next();

			income = 0;
			spending = 0;

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");

			try {
				Date date1 = dateFormat.parse(date01);
				Date date2 = dateFormat.parse(date02);

				for (int i = 0; i < s.accountList.size(); i++) {
					Date compareDate = dateFormat.parse(s.accountList.get(i).getDate());

					if ((date1.equals(compareDate) || date1.before(compareDate))
							&& (date2.equals(compareDate) || date2.after(compareDate))) {
						isFind = true;

						if (s.accountList.get(i).getType().equals("수입")) {
							income += s.accountList.get(i).getPrice();
						} else { // .equals("지출")
							spending += s.accountList.get(i).getPrice();
						}
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			System.out.println(date01 + " ~ " + date02 + " 결산 : 수입 " + income + "원 / 지출 " + spending + "원");
			break;
		default:
			break;
		}

		if (isFind == false) {
			System.out.println("일치하는 항목 없음");
		}

	}

	// 수정
	public void update() {

		boolean isFind = false;

		System.out.print("수정할 용도(제목) 입력 >> ");
		String search = sc.next();

		for (int i = 0; i < s.accountList.size(); i++) {
			if (search.equals(s.accountList.get(i).getTitle())) {
				// 수정과정
				System.out.print("날짜 ex)230609 >> ");
				String date = sc.next();

				System.out.print("용도(제목) >> ");
				String title = sc.next();

				System.out.print("수입/지출 >> ");
				String type = sc.next();

				System.out.print("금액 >> ");
				int price = sc.nextInt();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("내용(상세) >> ");
				String detail = "";
				try {
					detail = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}

				AccountDto dto = s.accountList.get(i);
				dto.setDate(date);
				dto.setTitle(title);
				dto.setType(type);
				dto.setPrice(price);
				dto.setDetail(detail);

				System.out.println("수정 완료");
				isFind = true;
				break;
			}
		}

		if (isFind == false) {
			System.out.println("일치하는 항목 없음");
		}

	}

	// 출력
	public void allDataPrint() {
		for (int i = 0; i < s.accountList.size(); i++) {
			System.out.println(s.accountList.get(i).toString());
		}
	}

}
