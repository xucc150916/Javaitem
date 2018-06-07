package chatroom;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


class OutToServer implements Runnable {
    private Socket client;

    public OutToServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            PrintStream ps = new PrintStream(client.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
            while (true) {
                System.out.println("请输入...");
                if(scanner.hasNext()) {
                    String str = scanner.next();
                    ps.println(str);
                    // 结束
                    if(str.equals("bye")) {
                        System.out.println("客户端退出");
                        ps.close();
                        scanner.close();
                        client.close();
                        break;

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class ReadFromServer implements Runnable {
    private Socket client;

    public ReadFromServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(client.getInputStream());
            while (scanner.hasNext()) {
                System.out.println("服务器发来的信息："+scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



public class Client {
    public static void main(String[] args) throws Exception{
        // 建立与 指定IP地址端口号的服务器的连接
        // client("192.168.43.250",8080);  // hj
        client("192.168.72.1",8080);
    }


    // 先读再写
    public static void client(String serverIP,int port) throws Exception{
        Socket client = new Socket(serverIP, port);
        Thread readThread = new Thread(new ReadFromServer(client));
        Thread writeThread = new Thread(new OutToServer(client));
        readThread.start();
        writeThread.start();
    }
}
