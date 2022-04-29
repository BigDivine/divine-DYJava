package com.divine.sbdemo.daos.impl;

import com.divine.sbdemo.daos.ShopHomeDao;
import com.divine.sbdemo.entities.ShopHomeFunctionBean;
import com.divine.sbdemo.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class ShopHomeDaoImpl implements ShopHomeDao {
    private ShopHomeDao getShopHomeDao() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
        return sqlSession.getMapper(ShopHomeDao.class);
    }
    @Override
    public ArrayList<ShopHomeFunctionBean> queryFunction() {
        return getShopHomeDao().queryFunction();
    }

    @Override
    public int insertFunction(ShopHomeFunctionBean function) {
        return getShopHomeDao().insertFunction(function);
    }

    @Override
    public int deleteFunction(ShopHomeFunctionBean function) {
        return getShopHomeDao().deleteFunction(function);
    }

    @Override
    public int updateFunction(ShopHomeFunctionBean function) {
        return getShopHomeDao().updateFunction(function);
    }
}
