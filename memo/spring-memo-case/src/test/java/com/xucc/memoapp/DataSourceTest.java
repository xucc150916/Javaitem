package com.xucc.memoapp;

import com.xucc.memoapp.service.MemoGroupService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;

public class DataSourceTest {

    private static ApplicationContext context;

    private static DataSource dataSource;

    @BeforeClass
    public static void beforeClass() {
        // 获取数据源
        context = new ClassPathXmlApplicationContext("application-context.xml");
        dataSource = context.getBean(DataSource.class);
    }

    /**
     * 数据库驱动对事务的支持测试
     * @throws Exception
     */
    @Test
    public void transactionSupportTest() throws Exception {
        Connection connection = dataSource.getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println("支持事务："+databaseMetaData.supportsTransactions());
        System.out.println("支持保存点 Savepoint："+databaseMetaData.supportsSavepoints());
        System.out.println("支持 ANSI92 Full SQL："+databaseMetaData.supportsANSI92FullSQL());
        System.out.println("支持 REPEATABLE_READ 隔离级别："+databaseMetaData.supportsTransactionIsolationLevel(
                Connection.TRANSACTION_REPEATABLE_READ
        ));
    }

    /**
     * 模拟事务测试
     */
    @Test
    public void transactionTest() {
        Connection connection = null;
        Statement statement = null;
        try {
            // 获取数据库连接
            connection = dataSource.getConnection();
            // 关闭自动提交
            connection.setAutoCommit(false);
            // 设置隔离级别，默认的可重复读其实已经够用，这里仅为了练习设置为可序列化
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            // 创建一组sql
            statement = connection.createStatement();
            int updateEffect = statement.executeUpdate(
                    "update memo_group set name='xc' where id = 1"
            );
            int deleteEffect = statement.executeUpdate(
                    "delete from memo_group where id = 2"
            );
            // 提交事务
            if(updateEffect==1 && deleteEffect==1) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



//
//    @Test
//    public void demo() {
//        ArrayBlockingQueue
//    }

}
