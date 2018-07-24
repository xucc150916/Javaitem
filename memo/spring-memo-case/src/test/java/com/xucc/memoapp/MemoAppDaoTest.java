package com.xucc.memoapp;

import com.xucc.memoapp.entity.MemoGroup;
import com.xucc.memoapp.service.MemoGroupService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Date;


@Transactional
public class MemoAppDaoTest {

    private static ApplicationContext context;

    private static MemoGroupService memoGroupService;

    private static Logger logger = LoggerFactory.getLogger(MemoAppDaoTest.class);

    @BeforeClass
    public static void beforeClass() {
        context = new ClassPathXmlApplicationContext("application-context.xml");
        memoGroupService = context.getBean(MemoGroupService.class);
    }

    /**
     * 查询指定名称的便签组数量测试
     */
    @Test
    public void queryMemoGroupByNameCountTest() {
        System.out.println(memoGroupService.queryMemoGroupCountDetail("xucc"));
    }

    /**
     * 根据id查询便签组信息测试
     */
    @Test
    public void queryMemoGroupTest() {
        System.out.println(memoGroupService.queryMemoGroupDetail(0));
    }

    /**
     * 给便签组插入一个便签测试
     */
    @Test
    public void insetMemoGroupTest() {
        MemoGroup memoGroup = new MemoGroup();
        memoGroup.setName("t1");
        memoGroup.setCreatedTime(new Date());
        System.out.println(memoGroupService.addMemoGroupDetail(memoGroup));
    }






}
