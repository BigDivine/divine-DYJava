package com.divine.sbdemo.controller;

import com.divine.sbdemo.Response;
import com.divine.sbdemo.daos.impl.ShopHomeDaoImpl;
import com.divine.sbdemo.entities.ShopHomeFunctionBean;
import com.divine.sbdemo.utils.ControllerUtils;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class ShopHomeController {
    private static ShopHomeDaoImpl mShopHomeDaoImpl;

    static {
        mShopHomeDaoImpl = new ShopHomeDaoImpl();
    }

    @RequestMapping(path = "/shop/home/function/query", method = RequestMethod.POST)
    public Response queryFunction() {
        ArrayList<ShopHomeFunctionBean> shopHomeFunctionBeans = mShopHomeDaoImpl.queryFunction();
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<ShopHomeFunctionBean>>() {
//        }.getType();
//        String json = gson.toJson(shopHomeFunctionBeans, type);
        return new Response(0, shopHomeFunctionBeans, "查询成功");
    }

    @RequestMapping(path = "/shop/home/function/insert", method = RequestMethod.POST)
    public Response insertFunction(HttpServletRequest request) {
        try {
            String functionJson = ControllerUtils.solveServletRequest(request);
            Gson gson = new Gson();
            ShopHomeFunctionBean function = gson.fromJson(functionJson, ShopHomeFunctionBean.class);
            mShopHomeDaoImpl.insertFunction(function);

            return new Response(0, function, "新增成功");
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(1, "", "新增失败," + e.getMessage());
        }
    }

    @RequestMapping(path = "/shop/home/function/delete", method = RequestMethod.POST)
    public Response deleteFunction(HttpServletRequest request) {
        try {
            String functionJson = ControllerUtils.solveServletRequest(request);
            Gson gson = new Gson();
            ShopHomeFunctionBean function = gson.fromJson(functionJson, ShopHomeFunctionBean.class);
            mShopHomeDaoImpl.deleteFunction(function);

            return new Response(0, function, "删除成功");
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(1, "", "删除失败," + e.getMessage());
        }
    }

    @RequestMapping(path = "/shop/home/function/update", method = RequestMethod.POST)
    public Response updateFunction(HttpServletRequest request) {
        try {
            String functionJson = ControllerUtils.solveServletRequest(request);
            Gson gson = new Gson();
            ShopHomeFunctionBean function = gson.fromJson(functionJson, ShopHomeFunctionBean.class);
            mShopHomeDaoImpl.updateFunction(function);

            return new Response(0, function, "更新成功");
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(1, "", "更新失败," + e.getMessage());
        }
    }
}
