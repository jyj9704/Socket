package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {

	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		BufferedWriter out = null;
		Scanner sc = new Scanner(System.in);
		
		try { // 있거나 없으면 죽기 대문에 try catch문 작성
			socket = new Socket("localhost", 9999); // 9999포트를열어서 소통할게!
			
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 소켓으로부터 input 받을게! 읽고
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));// 쓰기
			
			while(true){
				System.out.print("보내기 >>");
				String outMessage = sc.nextLine(); 
				out.write(outMessage+ "\n");
				out.flush(); 
				
				String inMessage = in.readLine();// in으로 부터 한 줄로 읽어옴
				if(inMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트가 나갔습니다.!");
					break;
				}
				// 정상 메시지인 경우
				System.out.println("클라이언트 : "+inMessage);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
	
			try { 
				sc.close();
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		

	}

}
