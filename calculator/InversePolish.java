package test;

import java.util.*;


/**
 * @author xucc
 */
public class InversePolish {
    /**
     * 存放运算符的栈
     */
    private Stack<String> operationStack = new Stack<>();

    /**
     * 运算栈
     */
    private static Stack<String> stack = new Stack<>();

    /**
     * 存放按运算符切分好了的中缀表达式
     * 在构造里进行切分赋值
     */
    private ArrayList<String> middleExpressionList = new ArrayList<>();

    /**
     * 存放转换之后的逆波兰表达式
     */
    private ArrayList<String> inversePolishList = new ArrayList<>();

    /**
     * 运算符优先级
     * TODO 可以使用枚举类进行优化
     */
    private static final int PRIORITY_THREE = 3;
    private static final int PRIORITY_TWO = 2;
    private static final int PRIORITY_ONE = 1;

    /**
     * 在构造方法里将传进来的逆波兰式进行切分
     * @param str 逆波兰表达式字符串
     */
    public InversePolish(String str) {
        // 使用 StringTokenizer 将表达式切分
        StringTokenizer stringTokenizer =
                new StringTokenizer(str, "+-*/()", true);
        while (stringTokenizer.hasMoreTokens()) {
            // 将切分之后的中序表达式放入数组中
            String tmp = stringTokenizer.nextToken();
            this.middleExpressionList.add(tmp);
        }
    }

    /**
     * 判断字符串内容是否为数字
     * @param str 字符串
     * @return 真假
     */
    private boolean isNumber(String str) {
        // 使用正则表达式判断是否为数字
        String numberFlag = "[0-9]+";
        return str.matches(numberFlag);
    }

    /**
     * 是否为操作符
     * @param str 字符串
     * @return 真假
     */
    private boolean isOperation(String str) {
        // 正则表达式判断是否为操作符
        String operationFlag = "[\\+\\-\\*\\/\\(\\)]";
        return str.matches(operationFlag);
    }

    /**
     * 获取运算符的优先级
     * @param str 运算符
     * @return 优先级，最高为3，最低为1，非法输入返回-1
     */
    private int getPriority(String str) {

        switch (str) {
            case "(" :
                return PRIORITY_THREE;
            case "*":
            case "/":
                return PRIORITY_TWO;
            case "+":
            case "-":
                return PRIORITY_ONE;
            default:
                return -1;
        }
    }

    /**
     * 判断两个运算符优先级大小
     * @param str1 运算符1
     * @param str2 运算符2
     * @return 1>2，返回true，否则返回false
     */
    private boolean comparePriority(String str1, String str2) {
        return getPriority(str1) > getPriority(str2);
    }

    /**
     * 对运算符进行入栈处理
     * @param str 当前字符串元素
     */
    private void operationPush(String str) {
        // 判空
        if(operationStack.isEmpty()) {
            // 操作符栈为空时，直接将操作符入栈
            operationStack.push(str);
            return;
        }

        // 当操作符为 "(" 时，直接入栈
        if("(".equals(str)) {
            operationStack.push(str);
            return;
        }

        // 当操作符为 ")" 时，出栈一直到 "("，出栈的元素添加到结果中
        if (")".equals(str)) {
            String tmp = null;
            operationStack.push(str);
            // 将当前操作符栈顶元素出栈并赋给tmp，tmp不是"("就一直循环
            while (!"(".equals(operationStack.peek())) {
                tmp = operationStack.pop();
                inversePolishList.add(tmp);
            }
            return;
        }

        // 当前操作符栈栈顶元素为"("，直接入栈
        if("(".equals(operationStack.peek())) {
            operationStack.push(str);
            return;
        }

        // 如果当前操作符比栈顶操作符优先级大，则直接入栈
        if(comparePriority(str, operationStack.peek())) {
            operationStack.push(str);
            return;
        } else {// 否则，出栈操作符并将其存放到结果中，再调用依次本函数
            inversePolishList.add(operationStack.pop());
            operationPush(str);
            return;
        }

    }

    /**
     * 中缀表达式变为后缀表达式
     */
    private void conversion() {
        for(String str : middleExpressionList) {
            if(isNumber(str)) {
                // 中序表达式的数组直接添加
                inversePolishList.add(str);
            } else if(isOperation(str)) {
                // 操作符进行判断之后再添加
                operationPush(str);
            } else {
                System.out.println("非法输入");
            }
        }

        // 遍历完了操作符栈中可能还会留有一些操作符，将它们一次性出栈到逆波兰表达式中
        while (!operationStack.isEmpty()) {
            inversePolishList.add(operationStack.pop());
        }
//        // 返回逆波兰表达式，方便测试
//        for(String tmp : inversePolishList) {
//            System.out.println(tmp);
//        }
    }

    /**
     * 实现逆波兰表达式的计算
     * @param s1 数字
     * @param s2 数字
     * @param s3 操作符
     * @return 结果
     */
    private int calculator(String s1, String s2, String s3) {
        int num1 = Integer.parseInt(s1);
        int num2 = Integer.parseInt(s2);

        switch (s3) {
            case "+":return num1+num2;
            case "-":return num1-num2;
            case "*":return num1*num2;
            case "/":return num1/num2;
//            case "(":
//            case ")":
            default:return 0;
        }
    }

    /**
     * 实现逆波兰表达式的计算
     * @return 计算值
     */
    public int calculator() {
        // 将中序表达式转为逆波兰表达式
        conversion();

        System.out.println(inversePolishList);
        for(String str : inversePolishList) {
            System.out.println(str);
            if(isNumber(str)) {
                stack.push(str);
            } else {
                // 跳过括号
                if("(".equals(str) || ")".equals(str)) {
                    continue;
                } else {
                    String second = stack.pop();
                    String first = stack.pop();
                    stack.push(
                            String.valueOf(calculator(
                                    first, second, str)));
                }
            }
        }
        return Integer.parseInt(stack.peek());
    }

    public static void main(String[] args) {
        // 从键盘接收输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入表达式:>");
        String input = scanner.next();

        InversePolish inversePolish = new InversePolish(input);

        // 计算逆波兰表达式
        System.out.println("结果是:>");
        System.out.println(inversePolish.calculator());
    }

}
