package com.divine.sbdemo.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerUtils {
    public static String solveServletRequest(HttpServletRequest request) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String readLine = br.readLine();
        while (readLine != null) {
            sb.append(readLine);
            readLine = br.readLine();
        }
        return sb.toString();
    }
}
