package com.divine.sbdemo.daos.impl;

import com.divine.sbdemo.daos.UserDao;
import com.divine.sbdemo.entities.UserBean;
import com.divine.sbdemo.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class UserDaoImpl implements UserDao {
    @Override
    public int insertUser(UserBean userBean) {
        return getUserDao().insertUser(userBean);
    }

    @Override
    public int insertUserPhone(UserBean userBean) {
        return getUserDao().insertUserPhone(userBean);
    }

    @Override
    public int updateUserPass(UserBean userBean) {
        return getUserDao().updateUserPass(userBean);
    }

    @Override
    public int updateUserPhoneVer(UserBean userBean) {
        return getUserDao().updateUserPhoneVer(userBean);
    }

    @Override
    public int updateUserInfo(UserBean userBean) {
        return getUserDao().updateUserInfo(userBean);
    }

    @Override
    public UserBean getUserByName(String userName) {
        return getUserDao().getUserByName(userName);
    }

    @Override
    public UserBean getUserByPhone(String userPhone) {
        return getUserDao().getUserByPhone(userPhone);
    }

    private UserDao getUserDao() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
        return sqlSession.getMapper(UserDao.class);
    }
}
