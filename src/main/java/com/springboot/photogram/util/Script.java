package com.springboot.photogram.util;

public class Script {

    public static String back(String msg) {
        System.out.println("alert떠라!");
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+msg+"');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

}
