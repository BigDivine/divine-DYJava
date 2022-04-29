package com.divine.sbdemo.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtils {
    private static SqlSessionFactory mSqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("MyBatis-config.xml");
            mSqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(boolean aotuCommit) {
        return mSqlSessionFactory.openSession(aotuCommit);
    }
}
