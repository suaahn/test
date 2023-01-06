package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dao.AddressDao;

public class FileProc {
	// 파일에 data 저장, 불러오기(쓰기, 읽기)
	private File file;
	
	
	public FileProc(String filename) {
		file = new File("c:\\javaSamples\\addressbook\\src\\data\\" + filename + ".txt");
		
		try {
			if (file.createNewFile()) {
				System.out.println("파일 생성 성공!");
			} else {
				System.out.println("기존 파일이 있습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] read() {
		String data[] = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			// 데이터의 갯수를 세서 배열 할당
			int count = 0;
			while (br.readLine() != null) {
				count++;
			}
			br.close();
			
			data = new String[count];
			
			// 커서 초기화 후 배열에 데이터 저장
			br = new BufferedReader(new FileReader(file));
			
			count = 0;
			String str = "";
			while ((str = br.readLine()) != null) {
				data[count] = str;
				count++;
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public void write(String data[]) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (int i = 0; i < data.length; i++) {
				pw.println(data[i]);
			}
			
			pw.close();	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("파일에 저장되었습니다.");
	}
}
