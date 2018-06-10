package chatroom;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class Server {

    /**
     * 使用Map存储客户端用户名与客户端socket
     */
    private static Map<String, Socket> clientMap = new HashMap<>();


    /**
     * 内部类，多线程处理客户端读写
     */
    static class ExcuteClientServer implements Runnable {
        private Socket client;

        /**
         * 通过构造注入使客户端与线程连接
         * @param client 客户端socket
         */
        public ExcuteClientServer(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                // 拿到客户端输入流
                Scanner scanner = new Scanner(client.getInputStream());

                while (true) {
                    if(scanner.hasNext()) {
                        String str = scanner.nextLine();
                        // 使用正则表达式对客户端输入进行处理，将字符串首的换行剔除，方便之后的处理
                        Pattern pattern = Pattern.compile("\r|\n|\n\r");
                        Matcher matcher = pattern.matcher(str);
                        str = matcher.replaceAll("");

                        // 判断
                        if(str.startsWith("userName")) {// 新用户注册，格式 userName:name
                            String userName = str.split(":")[1];
                            registerUser(userName, client);
                        } else if(str.startsWith("G:")) {// 群聊，格式 G:msg
                            String msg = str.split(":")[1];
                            groupChat(msg);
                        } else if(str.startsWith("P:")) {// 私聊，格式：P:user-msg
                            String tmp = str.split(":")[1];
                            // 私聊对象的用户名
                            String userName = tmp.split("-")[0];
                            // 私聊消息
                            String msg = tmp.split("-")[1];
                            privateChat(userName, msg);
                        } else if(str.contains("bye")) {// 下线，格式：bye

                            userExit(client);

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 新用户注册
         * @param userName 新用户用户名
         * @param userSocket 新用户socket
         */
        private void registerUser(String userName, Socket userSocket) {
            // 将新用户添加到当前用户列表中
            clientMap.put(userName, userSocket);
            // 向其他用户广播上线信息
            String msg = "用户"+userName+"上线了，当前在线人数："+clientMap.size();
            groupChat(msg);
        }

        /**
         * 群聊
         * @param msg 群聊发送的消息
         */
        private void groupChat(String msg) {

            String transmit = currentUserName(client)+"说:>"+msg;

            Iterator<Map.Entry<String, Socket>> iterator = clientMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Socket socket = iterator.next().getValue();
                try {
                    sendMsg(socket, transmit);
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }
        }


        /**
         * 私聊
         * @param privateUser 私聊对象用户名
         * @param msg 私聊的消息
         */
        private void privateChat(String privateUser, String msg) {

            Socket privateSocket = clientMap.get(privateUser);

            String transmit = currentUserName(privateSocket)+"说:>"+msg;

            try {
                PrintStream printStream = new PrintStream(privateSocket.getOutputStream(), true);
                printStream.println(transmit);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 下线指定用户
         * @param user 下线用户名
         */
        private void userExit(Socket user) {
            String userName = currentUserName(client);
            clientMap.remove(userName);
            // 向所有用户通知用户下线
            String msg = "用户"+userName+"下线，当前在线人数："+clientMap.size();
            groupChat(msg);

        }


        /**
         * 通过socket找到其对应的用户名
         * @param socket 要查找的用户socket
         * @return 对应的用户名
         */
        private String currentUserName(Socket socket) {

            // 遍历用户列表所有用户名
            for(String curUserName : clientMap.keySet()) {
                if(clientMap.get(curUserName).equals(socket)) {
                    // 拿到当前用户的用户名
                    return curUserName;
                }
            }
            return null;

        }

        /**
         * 向指定socket发送信息
         * @param socket 用户socket
         * @param msg 发送的信息
         */
        private void sendMsg(Socket socket, String msg) {
            try {
                PrintStream printStream = new PrintStream(socket.getOutputStream(), true);
                printStream.println(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            ExecutorService executorService = Executors.newFixedThreadPool(20);
            for(int i = 0; i < 20; i++) {
                System.out.println("等待客户端连接，本机端口："+serverSocket.getLocalPort());
                Socket socket = serverSocket.accept();
                executorService.execute(new ExcuteClientServer(socket));
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}