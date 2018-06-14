package singleChatRoom;


import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 服务器端采用 ServerSocket
 * 客户端采用   Socket
 */

public class SingleServer {

    public static void main(String[] args) {
        server(12345);
    }


    public static void server(int port){

        try {
            System.out.println("等待客户端连接...");
            ServerSocket server = new ServerSocket(port);

            // 监听连接到服务器的socket，阻塞
            Socket client = server.accept();

            System.out.println(client.getInetAddress()+" 接入...");

            // 向客户端写入数据
            PrintStream ps = new PrintStream(client.getOutputStream(), true);
            ps.print("我是服务器："+server.getInetAddress()+"\n");


            // 读取客户端传过来的数据
            System.out.println("客户端发送消息：");
            Scanner scanner = new Scanner(client.getInputStream());
            scanner.useDelimiter("\n");
            if(scanner.hasNext()) {
                System.out.println(scanner.next());
            }


            // 关闭
            server.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
