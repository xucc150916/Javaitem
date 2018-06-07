package chatroom;

import javafx.css.Match;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



// 多线程
class ExcuteClientServer implements Runnable {
    // 接收用户连接的socket
    private Socket client;

    public ExcuteClientServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println(client.getInetAddress()+" 已接入...");

            // 向客户端写入数据
            PrintStream ps = new PrintStream(client.getOutputStream(), true);
            ps.print("我是服务器!!\n");


            // 读取客户端传过来的数据
            Scanner scanner = new Scanner(client.getInputStream());
            scanner.useDelimiter("\n");
            while(scanner.hasNext()) {
                System.out.println("客户端发送："+scanner.next());
            }

            // 关闭
            // System.out.println(client.getInetAddress()+"断开连接");
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(8080);

        // 构建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for(int i = 0; i < 20; i++) {

            Socket socket = null;
            try {
                System.out.println("等待远程连接，本机端(服务器)端口号为："+server.getLocalPort());
                // 监听
                socket = server.accept();

            } catch (Exception e) {
                e.printStackTrace();
            }
            // 将监听到的客户端加入到线程池中
            executorService.execute(new ExcuteClientServer(socket));
        }
        server.close();
    }
}

