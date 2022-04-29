package com.divine.sbdemo.controller;

import com.divine.sbdemo.MyConstants;
import com.divine.sbdemo.Response;
import com.divine.sbdemo.daos.impl.FileDaoImpl;
import com.divine.sbdemo.entities.FileBean;
import com.divine.sbdemo.utils.ControllerUtils;
import com.divine.sbdemo.utils.Utils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class FileController {
    private static FileDaoImpl mFileDaoImpl;
    @Value("${spring.servlet.multipart.location}")
    private String UPLOAD_PATH;

    static {
        mFileDaoImpl = new FileDaoImpl();
    }

    @RequestMapping(path = "/upload/img", method = RequestMethod.POST)
    public Response uploadImg(MultipartFile file) {
        //过滤合法的文件类型
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String allowSuffix = "gif,jpg,jpeg,bmp,png,ico";
        if (allowSuffix.indexOf(suffix) == -1) {
            return new Response(1, "", "文件类型错误");
        }
        try { //在上传目录中创建新文件
            String fileId = Utils.getRandomUUID();
            String newFileName = Utils.getNowDateStr("") + fileId + "." + suffix;
            File dir = new File(UPLOAD_PATH + File.separator);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File newFile = new File(dir + File.separator + newFileName);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile, false));

            FileBean fileBean = new FileBean();
            fileBean.setFileId(fileId);
            fileBean.setFileName(fileName);
            fileBean.setFileSize(file.getSize());
            fileBean.setFileUrl(MyConstants.server + MyConstants.imageServer + "/" + newFileName);
            fileBean.setFileType("image");

            mFileDaoImpl.insertFile(fileBean);

            return new Response<FileBean>(0, fileBean, "上传文件成功");
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(1, "", "上传文件失败," + e.getMessage());

        }
    }

    @RequestMapping(path = "/file/delete", method = RequestMethod.POST)
    public Response deleteFile(HttpServletRequest request) {
        try {
            FileBean file = new Gson().fromJson(ControllerUtils.solveServletRequest(request), FileBean.class);
            int i = mFileDaoImpl.deleteFile(file);
            return new Response(0, "", "删除成功");

        } catch (IOException e) {
            e.printStackTrace();
            return new Response(1, "", "删除失败," + e.getMessage());
        }
    }

    @RequestMapping(path = "/file/queryImage", method = RequestMethod.POST)
    public Response queryImage() {
        ArrayList<FileBean> images = mFileDaoImpl.queryImage();
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<FileBean>>() {
//        }.getType();
//        String json = gson.toJson(images, type);
//        System.out.println(json);
        return new Response<ArrayList<FileBean>>(0, images, "查询成功");
    }
}
