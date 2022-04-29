package com.divine.sbdemo.controller;

import com.divine.sbdemo.Response;
import com.divine.sbdemo.daos.impl.UserDaoImpl;
import com.divine.sbdemo.entities.UserBean;
import com.divine.sbdemo.entities.UserPass;
import com.divine.sbdemo.enums.LoginEnum;
import com.divine.sbdemo.utils.ControllerUtils;
import com.divine.sbdemo.utils.Utils;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

@RestController
public class UserController {
    private static UserDaoImpl mUserDaoImpl;

    static {
        mUserDaoImpl = new UserDaoImpl();
    }

    /**
     * 注册接口
     *
     * @param request 用户名和密码
     * @return
     */
    @RequestMapping(path = "/user/register", method = RequestMethod.POST)
    public Response userRegister(HttpServletRequest request) {
        try {
            UserBean registerUserBean = new Gson().fromJson(ControllerUtils.solveServletRequest(request), UserBean.class);
            UserBean queryUserBeanByName = mUserDaoImpl.getUserByName(registerUserBean.getUserName());
            UserBean queryUserBeanByPhone = mUserDaoImpl.getUserByPhone(registerUserBean.getUserPhone());
            if (queryUserBeanByName != null) {
                return new Response(1, "{}", "用户名已存在");
            } else if (queryUserBeanByPhone != null) {
                if (registerUserBean.getUserVerCode().equals(queryUserBeanByPhone.getUserVerCode())) {
                    int update = mUserDaoImpl.updateUserInfo(registerUserBean);
                    if (update > 0) {
                        return new Response(0, "{}", "注册成功");
                    } else
                        return new Response(1, "{}", "用户名创建失败");
                } else {
                    return new Response(1, "{}", "验证码错误");
                }
            } else {
                int insert = mUserDaoImpl.insertUser(registerUserBean);
                if (insert > 0) {
                    return new Response(0, "{}", "注册成功");
                } else
                    return new Response(1, "{}", "用户名创建失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(1, "{}", e.getMessage());
        }
    }

    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    public Response userLogin(HttpServletRequest request) {
        try {
            UserBean loginParam = new Gson().fromJson(ControllerUtils.solveServletRequest(request), UserBean.class);
            switch (checkLogin(loginParam)) {
                case USER_NAME_NULL:
                    return new Response(1, "{}", "用户名不能为空");
                case USER_PASS_NULL:
                    return new Response(1, "{}", "用户密码不能为空");
                case USER_NAME_ERROR:
                    return new Response(1, "{}", "用户名错误");
                case USER_PASS_ERROR:
                    return new Response(1, "{}", "用户密码错误");
                case LOGIN_SUCCESS:
                    return new Response(0, "{}", "登录成功");
                default:
                    return new Response(1, "{}", "未知错误");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new Response(1, "{}", e.getMessage());
        }
    }

    @RequestMapping(path = "/user/getSmsVer", method = RequestMethod.POST)
    public Response userGetSmsVer(HttpServletRequest request) {
        try {
            UserBean userBeanSms = new Gson().fromJson(ControllerUtils.solveServletRequest(request), UserBean.class);
            Random mRandom = new Random();
            int phoneVer = mRandom.nextInt(8999) + 1000;
            userBeanSms.setUserVerCode(String.valueOf(phoneVer));
            UserBean userBeanByPhone = mUserDaoImpl.getUserByPhone(userBeanSms.getUserPhone());
            if (userBeanByPhone != null) {
                int update = mUserDaoImpl.updateUserPhoneVer(userBeanSms);
                if (update > 0) {
                    return new Response(0, "{\"smsVer\":\"" + phoneVer + "\"}", "获取验证码成功");
                } else {
                    return new Response(1, "{}", "获取验证码失败");
                }
            } else {
                int insert = mUserDaoImpl.insertUserPhone(userBeanSms);
                if (insert > 0) {
                    return new Response(0, "{\"smsVer\":\"" + phoneVer + "\"}", "获取验证码成功");
                } else {
                    return new Response(1, "{}", "获取验证码失败");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(1, "{}", e.getMessage());
        }
    }

    @RequestMapping(path = "/user/smsLogin", method = RequestMethod.POST)
    public Response userSmsLogin(HttpServletRequest request) {
        try {
            UserBean userBeanSms = new Gson().fromJson(ControllerUtils.solveServletRequest(request), UserBean.class);
            UserBean userBeanByPhone = mUserDaoImpl.getUserByPhone(userBeanSms.getUserPhone());
            if (userBeanByPhone != null) {
                if (userBeanSms.getUserVerCode().equals(userBeanByPhone.getUserVerCode())) {
                    if (Utils.isEmpty(userBeanByPhone.getUserName())) {
                        userBeanByPhone.setUserName("user-" + (new Random().nextInt(899999) + 1000000));
                        userBeanByPhone.setUserPass("123456");
                        int update = mUserDaoImpl.updateUserInfo(userBeanByPhone);
                        if (update > 0) {
                            return new Response(0, "{}", "新账号注册登录成功");
                        } else {
                            return new Response(1, "{}", "新账号注册失败");
                        }
                    }
                    return new Response(0, "{}", "登录成功");
                } else {
                    return new Response(1, "{}", "验证码错误");
                }
            } else {
                return new Response(1, "{}", "未获取验证码");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(1, "{}", e.getMessage());
        }
    }

    @RequestMapping(path = "/user/resetPass", method = RequestMethod.POST)
    public Response userResetPass(HttpServletRequest request) {
        try {
            UserPass userPass = new Gson().fromJson(ControllerUtils.solveServletRequest(request), UserPass.class);
            UserBean userBeanByName = mUserDaoImpl.getUserByName(userPass.getUser());
            UserBean userBeanByPhone = mUserDaoImpl.getUserByPhone(userPass.getUser());
            boolean isExist;
            UserBean userBeanNew;
            String userPassStr;
            if (userBeanByName != null) {
                isExist = true;
                userBeanNew = userBeanByName;
                userPassStr = userBeanByName.getUserPass();
            } else if (userBeanByPhone != null) {
                isExist = true;
                userBeanNew = userBeanByPhone;
                userPassStr = userBeanByPhone.getUserPass();
            } else {
                return new Response(1, "{}", "用户不存在");
            }
            if (isExist && !Utils.isEmpty(userPassStr) && userPassStr.equals(userPass.getUserPassOld())) {
                userBeanNew.setUserPass(userPass.getUserPassNew());
                int update = mUserDaoImpl.updateUserPass(userBeanNew);
                if (update > 0) {
                    return new Response(0, "{}", "密码修改成功");
                } else {
                    return new Response(1, "{}", "密码修改失败");
                }
            } else {
                return new Response(1, "{}", "用户不存在");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(1, "{}", e.getMessage());
        }
    }

    private LoginEnum checkLogin(UserBean loginParam) {
        UserBean userBeanByName = mUserDaoImpl.getUserByName(loginParam.getUserName());
        if (Utils.isEmpty(loginParam.getUserName())) {
            return LoginEnum.USER_NAME_NULL;
        } else if (Utils.isEmpty(loginParam.getUserPass())) {
            return LoginEnum.USER_PASS_NULL;
        } else {
            if (!loginParam.getUserName().equals(userBeanByName.getUserName())) {
                return LoginEnum.USER_NAME_ERROR;
            } else if (!loginParam.getUserPass().equals(userBeanByName.getUserPass())) {
                return LoginEnum.USER_PASS_ERROR;
            } else {
                return LoginEnum.LOGIN_SUCCESS;
            }
        }
    }


}
