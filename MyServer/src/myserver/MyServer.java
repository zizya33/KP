package myserver;

import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class MyServer {
    public static int count=0;
    
   public static void main(String[] args) throws IOException, SQLException {
        Frame start_frame = new Frame(300,180);
        ServerSocket server = null;
        DBConnection.isAdminExist();
        
        int PORT_NUM=0;
        String result="";
        FileReader reader = new FileReader("config.txt");
        try
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                result += (char)c;
            }
            PORT_NUM=Integer.parseInt(result);
        }
        catch(IOException ex){

            PORT_NUM=2525;
        }
            
        
        try {
            System.out.println("Начало прослушивания");
            System.out.println("Порт: "+PORT_NUM);
            
            server = new ServerSocket(PORT_NUM);
        } 
        catch (IOException e) {
            System.out.println("Невозможно использовать порт: "+PORT_NUM);
            System.exit(-1);
        }
        while (true) {
            try {
                Socket socket = server.accept();
                Session session = new Session(socket);
                
		new Thread(session).start();
                
            }
            catch (IOException e) {
                System.out.println("Проблемы с соединением");
		System.out.println(e.getMessage());
                server.close();
		System.exit(-1);
            }
	}
    }
}

