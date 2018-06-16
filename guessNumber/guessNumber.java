import java.util.Random;
import java.util.Scanner;

class Game {
    // 开始菜单
    public void menu() {
        System.out.println("*******************************************************************");
        System.out.println("************************* 猜数字小游戏 *****************************");
        System.out.println("******************** 0. 退出游戏   1. 开始游戏 *********************");
        System.out.println("*******************************************************************");
    }
    // 生成随机数
    public int random() {
        Random random = new Random();
        return random.nextInt(100)+1;
    }
    // 判断输入数字与随机数之间的关系，大于返回大于0，小于返回小于0，等于返回0
    public int compare(int num, int random) {
        return num-random;
    }
    // 根绝 compare 返回的值输出提示语句，并返回接下来是否继续进行， 猜对了返回1，游戏结束，猜错了返回0，游戏继续
    public int message(int compare) {
        if(compare > 0) {
            System.out.println("太大了");
            return 0;
        } else if(compare < 0) {
            System.out.println("太小了");
            return 0;
        } else {
            System.out.println("猜对了");
            return 1;
        }
    }
    // 接受stdin输入的字符，返回为对应数字
    public int scanf() {
        System.out.print("请输入:>");
        Scanner input = new Scanner(System.in);
        String str = input.next();
        int num = Integer.parseInt(str);
        return num;
    }

    // 逻辑函数
    public void realGame() {

        int num = 0;
        int flag = 0; 
        do {
            menu();
            flag = scanf();
            switch(flag) {
                case 1: {// 开始游戏
                    int random = this.random();
                    System.out.println(random);
                    num = this.scanf();
                    while(message(compare(num, random)) == 0) {
                        num = this.scanf();
                    }
                    flag = 1; 
                }
                break;
                case 0: {
                    flag = 0;
                    System.out.println("游戏退出");
                }
                break;
                default: {
                    System.out.println("请输入正确选项");
                    flag = 1;
                }
                break;
            }
        }while(flag == 1);
    }
}

class PlayGame {
    // 在构造方法里打印游戏开始菜单

    private Game game = new Game();
    // 调用Game类中的游戏方法
    public void start() {
        game.realGame();
    }


}

public class Day5 {
    public static void main(String[] args) {
        /* System.out.println("input");

        if( str.equals("0")) {
            System.out.println(str);
        } else {
            System.out.println("hah");
        } */
        PlayGame game = new PlayGame();
        game.start();
    }
}