package com.divine.sbdemo.daos;

import com.divine.sbdemo.entities.FileBean;

import java.util.ArrayList;

public interface FileDao {
    int insertFile(FileBean file);

    ArrayList<FileBean> queryImage();

    int deleteFile(FileBean file);
}
