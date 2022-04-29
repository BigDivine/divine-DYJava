package com.divine.sbdemo.daos;

import com.divine.sbdemo.entities.UserBean;

public interface UserDao {

    int insertUser(UserBean userBean);

    int insertUserPhone(UserBean userBean);

    int updateUserPass(UserBean userBean);

    int updateUserPhoneVer(UserBean userBean);

    int updateUserInfo(UserBean userBean);

    UserBean getUserByName(String userName);

    UserBean getUserByPhone(String userPhone);
}
