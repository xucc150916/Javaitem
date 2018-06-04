package singleChatRoom;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SingleServer {

	public static void main(String[] args) {
		try {
			// 打开服务器socket
			ServerSocket serverSocket = new ServerSocket(6666);
			System.out.println("等待客户端连接...");
			
			// 获得客户端Socket实例
			Socket clientSocket = serverSocket.accept();
			
			// 获取客户端输出流
			PrintStream print = new PrintStream(clientSocket.getOutputStream(),true);
			// 给客户端发送信息
			print.print("i am setver, my port is "+serverSocket.getLocalPort()+"\n");
			
			// 获取客户端输入流
			Scanner scan = new Scanner(clientSocket.getInputStream());
			// 指定分隔符
			scan.useDelimiter("\n");
			// 打印客户端信息
			if(scan.hasNext()) {
				System.out.println(scan.next());
			}
			
			System.out.println("hhh");
			
			// 关闭流
			serverSocket.close();
			
		} catch(Exception e) {
			
		}

	}

}
