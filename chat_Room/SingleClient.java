package singleChatRoom;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class SingleClient {


    public static void main(String[] args) {
        // 建立与 指定IP地址端口号的服务器的连接
        client("192.168.110.1",12345);
    }


    // 先读再写
    public static void client(String serverIP,int port){

        try {
            // 1. 建立客户端socket连接，指定服务器IP及端口
            Socket client = new Socket(serverIP,port);

            // 得到客户端socket输入流
            Scanner scanner = new Scanner(client.getInputStream());
            System.out.println("等待服务器响应...");
            scanner.useDelimiter("\n");
            if(scanner.hasNext()) {
                System.out.println("接收到服务器的信息："+scanner.next());
            }

            // 得到客户端socket读写流

            PrintStream ps = new PrintStream(client.getOutputStream(), true);

            // 通过输出流对socket进行读写操作
            String info="我是客户端";
            ps.print(info);

            // 关闭流
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
