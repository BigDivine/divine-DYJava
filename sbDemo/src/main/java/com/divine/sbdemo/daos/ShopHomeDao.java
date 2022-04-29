package com.divine.sbdemo.daos;

import com.divine.sbdemo.entities.ShopHomeFunctionBean;

import java.util.ArrayList;

public interface ShopHomeDao {
    ArrayList<ShopHomeFunctionBean> queryFunction();
    int insertFunction(ShopHomeFunctionBean function);
    int deleteFunction(ShopHomeFunctionBean function);
    int updateFunction(ShopHomeFunctionBean function);
}
