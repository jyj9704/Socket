package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;
 
public class ClientExam {
    
    public static void main(String args[]) {
    
        try {
            
            // 서버 연결
            String serverIp = "자신의 서버아이피";
            System.out.println("IP :" + serverIp + "연결 중입니다.");
            
            // 소켓 생성
            Socket socket = new Socket(serverIp, 1225);
            
            /* Server에서 보낸 값을 받기 위한 통로 */
            InputStream in = socket.getInputStream();
            /* Server에서 보낸 데이터를 받기 위한 통로 */
            DataInputStream dis = new DataInputStream(in);
            
            // 데이터 출력
            System.out.println("Message : " + dis.readUTF());  //readUTF 문자열을 읽어오기 위한 메서드
            
            // 소켓 종료
            dis.close();
            socket.close();
            System.out.println("연결 종료.");
            
        } catch (ConnectException ce) {
            ce.printStackTrace(); //예외 로그 남기기 (에러의 발생 근원지를 찾아서 단계별로 에러를 출력해줌.)
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
