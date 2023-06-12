package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dto.AccountDto;
import singleton.Singleton;

public class FileProc {

	File file = null;
	Singleton s = Singleton.getInstance();

	public FileProc() {
	}

	public FileProc(String filename) {

		file = new File("/Users/yeonju/Desktop/tmp/" + filename + ".txt");

		try {
			System.out.println(file.createNewFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write() {

		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

			for (int i = 0; i < s.accountList.size(); i++) {
				AccountDto dto = s.accountList.get(i);
				pw.println(dto.print());
			}

			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("파일 저장 완료");

	}

	public void read() {

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String str = br.readLine();
			while (str != null) {
				String arr[] = str.split("-");

				AccountDto dto = new AccountDto(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]), arr[4]);
				s.accountList.add(dto);

				str = br.readLine();
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
