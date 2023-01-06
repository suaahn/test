package main;

import java.util.Scanner;

import dao.AddressDao;

public class MainClass {

	public static void main(String[] args) {
		/*
		 CRUD (Create Read Update Delete) : 추가검색수정삭제 기능
		 
		 Main (Menu) -> 프론트엔드
		  ^
		  |
		 DAO (Data Access Object) : CRUD를 수행하는 클래스 -> 데이터를 다루는 사람
		  ^		ㄴ [데이터 저장, 불러오기]
		  |
		 DTO (Data Transfer Object) : 데이터를 담는 오브젝트 -> 데이터 종이 한 장 : 쓰기, 읽기
		 = VO (Value Object) : 주로 읽기전용
		 */
		
		// menu
		Scanner sc = new Scanner(System.in);
		
		AddressDao dao = new AddressDao();
		
		all:while (true) {
			System.out.println(" << 주소록 >> ");
			System.out.println("1. 지인 추가");
			System.out.println("2. 지인 삭제");
			System.out.println("3. 지인 검색");
			System.out.println("4. 지인 수정");
			System.out.println("5. 모두 출력");
			System.out.println("6. 데이터 저장");
			System.out.println("7. 종료");
			
			System.out.print(" >> ");
			int choice = sc.nextInt();
			
			switch (choice) {
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
				dao.filesave();
				break;
			case 7:
				break all;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
			
		}
		

	}

}
