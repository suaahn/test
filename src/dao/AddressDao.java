package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import data.FileProc;
import dto.HumanDto;

public class AddressDao {
	// data에 접근하고 편집하는 오브젝트
	
	Scanner sc = new Scanner(System.in);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	// data를 담을 그릇 - 변수 100개
	private HumanDto humanArr[] = new HumanDto[100];
	private int count;
	private FileProc fp;
	
	public AddressDao() {
		
		// 견본 데이터
//		humanArr[0] = new HumanDto("홍길동", 24, "1111-1111", "서울", "친구");
//		humanArr[1] = new HumanDto("성춘향", 21, "1111-0000", "서울", "친구");
//		humanArr[2] = new HumanDto("홍길동", 41, "1211-1111", "서울", "친구");
		
		fp = new FileProc("address");
		fileload();
		
		int nextcount = 0;
		for (int i = 0; i < humanArr.length; i++) {
			
			if (humanArr[i] != null ) {
				nextcount++;
			}
		}
		count = nextcount;
		
	}
	
	// TODO insert
	public void insert() {		// db에서 insert 명령쓰므로 통일
		System.out.println("지인을 추가합니다.");
		
		System.out.print("이름 = ");
		String name = sc.next();
		
		System.out.println("나이 = ");
		int age = sc.nextInt();
		
		System.out.println("전화번호 = ");
		String phone = sc.next();
		
		System.out.println("주소 = ");
		String address = "";
		try {
			address = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("메모 = ");
		String memo = sc.next();
		
		// 생성
		humanArr[count] = new HumanDto(name, age, phone, address, memo);
		count++;
		
	}
	
	// TODO delete
	public void delete() {
		System.out.print("삭제할 지인의 이름 = ");
		String name = sc.next();
		int index = search(name);
		
		if (index != -1) {
			// 삭제
			humanArr[index].setName("");
			humanArr[index].setAge(0);
			humanArr[index].setPhone("");
			humanArr[index].setAddress("");
			humanArr[index].setMemo("");
			
			System.out.println("지정한 지인을 삭제하였습니다.");
		}
	}
	
	// TODO select
	public void select() {
		// 검색
		System.out.print("검색할 지인의 이름 = ");
		String name = sc.next();
		
		/*
		index = -1;
		for (int i = 0; i < humanArr.length; i++) {
		
			if (humanArr[i] != null 
					&& name.equals(humanArr[i].getName())) {
				index = i;
				System.out.println(humanArr[index].toString());
			}
		}
		
		if (index == -1) {
			System.out.println("지인의 정보를 찾을 수 없습니다.");
		}
		*/
		// 동명이인 수 찾기
		int count = 0;
		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null 
					&& name.equals(humanArr[i].getName())) {
				count++;
			}
		}
		
		HumanDto selectHum[] = new HumanDto[count];
		
		int n = 0;
		for (int i = 0; i < humanArr.length; i++) {
			if (humanArr[i] != null 
					&& name.equals(humanArr[i].getName())) {
				selectHum[n] = humanArr[i];
				n++;
			}
		}
		
		for (int j = 0; j < selectHum.length; j++) {
			System.out.println(selectHum[j].toString());
		}
		
	}
	
	// TODO update
	public void update() {
		// 검색
		System.out.print("수정할 지인의 이름 = ");
		String name = sc.next();
		int index = search(name);
		
		// 수정
		if (index != -1) {
			// 수정			
			System.out.println("나이 = ");
			int age = sc.nextInt();
			
			System.out.println("전화번호 = ");
			String phone = sc.next();
			
			System.out.println("주소 = ");
			String address = "";
			try {
				address = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("메모 = ");
			String memo = sc.next();
			
			humanArr[index].setAge(age);
			humanArr[index].setPhone(phone);
			humanArr[index].setAddress(address);
			humanArr[index].setMemo(memo);
			
			System.out.println("지정한 지인을 수정하였습니다.");
		}
	}
	
	private int search(String name) {
		int index = -1;
		for (int i = 0; i < humanArr.length; i++) {
		
			if (humanArr[i] != null 
					&& name.equals(humanArr[i].getName())) {
				index = i;
				break;
			}
		}
		
		if (index == -1) {
			System.out.println("지인의 정보를 찾을 수 없습니다.");
		}
		
		return index;
	}
	
	// TODO filesave
	public void filesave() {
		
		int count = 0;
		for (int i = 0; i < humanArr.length; i++) {
			// 빈 객체, 삭제된 객체 제외
			if (humanArr[i] != null 
					&& humanArr[i].getName().equals("") == false) {
				count++;
			}
		}
		
		String dataArr[] = new String[count];
		
		count = 0;
		for (int i = 0; i < humanArr.length; i++) {
			
			if (humanArr[i] != null 
					&& humanArr[i].getName().equals("") == false) {
				dataArr[count] = humanArr[i].toString();
				count++;
			}
		}
		
		fp.write(dataArr); 
		
	}
	
	// TODO fileload
	public void fileload() {
		
		String data[] = fp.read();
		
		for (int i = 0; i < data.length; i++) {
			String str[] = data[i].split("\\*");
			humanArr[i] = new HumanDto(str[0], Integer.parseInt(str[1]), str[2], str[3], str[4]);
		}
	}
	
	public void allDataPrint() {
		for (int i = 0; i < humanArr.length; i++) {
			
			if (humanArr[i] != null 
					&& humanArr[i].getName().equals("") == false) {
				System.out.println(humanArr[i].toString());
			}
		}
	}
	
	
}
