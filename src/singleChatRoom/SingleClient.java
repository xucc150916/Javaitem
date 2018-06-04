package singleChatRoom;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SingleClient {

	public static void main(String[] args) {
		try {
			

//			Socket socket = new Socket("127.0.0.1", 1997);
//			Socket socket = new Socket("[图片]192.168.43.27", 6666);//guo
//			Socket socket = new Socket("192.168.43.80", 6666);//xu
			Socket socket = new Socket("192.168.43.163", 8080);//ziji
			//输入
			Scanner scanner = new Scanner(socket.getInputStream());
			scanner.useDelimiter("\n");
			if (scanner.hasNext()) {
				System.out.println(scanner.next());
			}
			//输出
			PrintStream ps = new PrintStream(socket.getOutputStream(),true);
			ps.print(" Jan "+socket.getLocalPort()+"\n");
			socket.close();

			
			
//			// 创建客户端Socket
//			Socket clientSocket = new Socket("192.168.43.163",8080);
//			System.out.println("hihi");
//			
//			// 获得输出流
//			PrintStream print = new PrintStream(clientSocket.getOutputStream(),true);
//			print.print("i am client"+"\n");
//
//			// 获得输入流
//			Scanner scan = new Scanner(clientSocket.getInputStream());
//			// 指定输入分隔符
//			scan.useDelimiter("\n");
//			if(scan.hasNext()) {
//				System.out.println(scan.next());
//			}
//			
//			
//			// 关闭socket流
//			clientSocket.close();
			
			
		} catch(Exception e) {
			
		}

	}

}
