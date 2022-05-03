package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerChat {

	public static void main(String[] args) {
		ServerSocket server = null; 
		Socket socket = null;
		BufferedReader in = null;
		BufferedWriter out = null;
		Scanner sc = new Scanner(System.in);
		
		try { // 있거나 없으면 죽기 대문에 try catch문 작성
			server = new ServerSocket(9999);// 9999포트를열어서 소통할게!
			System.out.println("연결 대기중~~~"); // 서버가 떠있는지 안떠있는지 구분하기 위하여
			
			socket = server.accept(); //accept return시 바로 socket생성
			System.out.println("연걸 되었습니다~~~");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 소켓으로부터 input 받을게! 읽고
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));// 쓰기
			
			while(true){//반복
				String inMessage = in.readLine();// in으로 부터 한 줄로 읽어옴
				if(inMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트가 나갔습니다!");
					break;
				}
				// 정상 메시지인 경우
				System.out.println("클라이언트 : "+inMessage);
				
				System.out.print("보내기 >>");
				String outMessage = sc.nextLine(); 
				out.write(outMessage+ "\n");
				out.flush(); 
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
	
			try { 
				sc.close();
				out.close();
				in.close();
				socket.close();
				server.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		

	}

}
