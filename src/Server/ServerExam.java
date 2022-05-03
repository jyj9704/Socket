package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.text.SimpleDateFormat;
 
public class ServerExam {
 
    public static void main(String[] args) {
 
        ServerSocket serversocket = null;
        
        try {
            serversocket = new ServerSocket(1225);
            System.out.println("서버 연결이 준비되었습니다.");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        while (true) {
            try {
                // 연결 요청이 올때 까지 대기
                System.out.println(getTime() + "연결 대기중");
                
                // Accept
                Socket socket = serversocket.accept();
                
                System.out.println(getTime() + "연결 요청 :" + socket.getInetAddress());
        
                /* Server에서 Client로 보내기 위한 통로 */
                OutputStream out = socket.getOutputStream();
                /* Server에서 Client로 데이터를 보내기  위한 통로 */
                DataOutputStream dos = new DataOutputStream(out);
                
                
                dos.writeUTF("Test Message");
                System.out.println(getTime() + "데이터 전송");
                
                dos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(new Date());
    }
 
}

