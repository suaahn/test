package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.FileProc;
import dto.HumanDto;

public class AddressDaoList {
	
	Scanner sc = new Scanner(System.in);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	// ArrayList
	private List<HumanDto> humanList = new ArrayList<HumanDto>();
	private FileProc fp;
	
	// Constructor
	public AddressDaoList() {
		fp = new FileProc("address");
		fileload();
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
		humanList.add(new HumanDto(name, age, phone, address, memo));
		
	}
	
	// TODO delete
	public void delete() {
		System.out.print("삭제할 지인의 이름 = ");
		String name = sc.next();
		int index = search(name);
		
		// 삭제
		humanList.remove(index);
		
		System.out.println("지정한 지인을 삭제하였습니다.");
		
	}
	
	// TODO select
	public void select() {
		// 검색
		System.out.print("검색할 지인의 이름 = ");
		String name = sc.next();
		
		for (HumanDto h : humanList) {
			if (name.equals(h.getName())) {
				System.out.println(h.toString());
			}
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
			
			humanList.get(index).setAge(age);
			humanList.get(index).setPhone(phone);
			humanList.get(index).setAddress(address);
			humanList.get(index).setMemo(memo);
			
			System.out.println("지정한 지인을 수정하였습니다.");
		}
		
	}
		
	private int search(String name) {
		int index = -1;
		
		for (int i = 0; i < humanList.size(); i++) {
			
			if (name.equals(humanList.get(i).getName())) {
				index = i;
				break;
			}
		}
		
		if (index == -1) {
			System.out.println("지인의 정보를 찾을 수 없습니다.");
		}
		
		return index;
		
	}
	
	// TODO file save
	public void filesave() {
		
		String dataArr[] = new String[humanList.size()];
		
		for (int i = 0; i < humanList.size(); i++) {
			dataArr[i] = humanList.get(i).toString();
		}
		
		fp.write(dataArr); 
		
	}
	
	// TODO file load
	public void fileload() {
		
		String data[] = fp.read();
		
		for (int i = 0; i < data.length; i++) {
			
			String str[] = data[i].split("\\*");
			HumanDto h = new HumanDto(str[0], Integer.parseInt(str[1]), str[2], str[3], str[4]);
			
			humanList.add(h);
		}
	}
	
	public void allDataPrint() {
		for (HumanDto h : humanList) {
			System.out.println(h.toString());
		}
	}

}
