package com.divine.sbdemo.entities;

public class FileBean {
    private String fileId, fileName, fileUrl, fileType;
    private double fileSize;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

//    @Override
//    public String toString() {
//        return "File{" + "fileId='" + fileId + '\''+"fileName='" + fileName + '\'' + ", fileUrl='" + fileUrl + '\'' + ", fileSize='" + fileSize + '\'' + ", fileType='" + fileType + '\'' + '}';
//    }
}
