package chatroom;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 向服务器发送消息
 */
class OutToServer implements Runnable {
    private Socket client;

    // 通过构造注入拿到服务器socket
    public OutToServer(Socket client) {
        this.client = client;
    }

    // 向服务器发送消息
    @Override
    public void run() {
        try {
            // 使用PrintStram流向服务器输出
            PrintStream ps = new PrintStream(client.getOutputStream());
            // Scanner从键盘接收要发送的消息
            Scanner scanner = new Scanner(System.in);
            // scanner.useDelimiter("\n");
            while (true) {
                // System.out.print("请输入消息：");
                if(scanner.hasNext()) {
                    String str = scanner.next();
                    ps.println(str);
                    // 约定输入 bye 代表本次聊天结束
                    if(str.equals("bye")) {
                        System.out.println("退出聊天室");
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

/**
 * 读取服务器发送的消息
 */
class ReadFromServer implements Runnable {
    private Socket client;

    // 通过构造注入拿到服务器socket
    public ReadFromServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            // 使用Scanner进行消息的显示
            Scanner scanner = new Scanner(client.getInputStream());
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }

            // 这里不用关闭client，在发送线程中进行当前client的关闭
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


public class Client {
    public static void main(String[] args){
        // 建立与 指定IP地址端口号的服务器的连接
        // client("192.168.43.250",8080);  // hj
        client("192.168.72.1",12345);
    }


    // 先读再写
    public static void client(String serverIP,int port){
        try {
            Socket client = new Socket(serverIP, port);
            // 读线程
            Thread readThread = new Thread(new ReadFromServer(client));

            // 写线程
            Thread writeThread = new Thread(new OutToServer(client));
            readThread.start();
            writeThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
