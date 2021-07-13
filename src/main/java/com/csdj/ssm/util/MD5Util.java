package com.csdj.ssm.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
    /**
     *
     * @param plainText 需要加密的字符串
     * @param lat 盐度
     * @return
     */

    public static String md5(String plainText,int lat){
        for(int i = 0;i < lat;i++){
            plainText = md5(plainText);
        }
        return plainText;
    }

    public static void main(String[] args) {
        System.out.println(md5("123456"));
    }
}

