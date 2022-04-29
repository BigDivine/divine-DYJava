package com.divine.sbdemo.daos.impl;

import com.divine.sbdemo.daos.FileDao;
import com.divine.sbdemo.entities.FileBean;
import com.divine.sbdemo.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class FileDaoImpl implements FileDao {
    @Override
    public int insertFile(FileBean file) {
        return getFileDao().insertFile(file);
    }

    @Override
    public ArrayList<FileBean> queryImage() {
        return getFileDao().queryImage();
    }

    @Override
    public int deleteFile(FileBean file) {
        return getFileDao().deleteFile(file);
    }

    public FileDao getFileDao() {
        SqlSession session = MyBatisUtils.getSqlSession(true);
        return session.getMapper(FileDao.class);
    }
}
