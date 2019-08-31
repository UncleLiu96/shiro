package com.shiro.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Util {
    /**
     * 账户密码加密
     * @param username
     * @param pwd
     * @return
     */
    public static String MD5Pwd(String username, String pwd) {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = pwd;//密码原值
        ByteSource salt = ByteSource.Util.bytes(username);//以账号作为盐值
        System.out.println(salt);
        int hashIterations = 1024;//加密1024次
        return new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations).toString();

    }
    public static void main(String[] args) {
        System.out.println(IpUtils.internetIp());
//        System.out.println(MD5Pwd("admin","123456"));
    }
}
