package com.wsd.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by tm on 2018/8/17.
 * 密码解析工具类
 */
public class EncrUtil {

    public static void main(String[] orgs){
        md5();
        md5AndSalt();
        sha1();
    }

    /*不加盐md5加密*/
    public static void md5(){
        //加密方式
        String hashAlgorithmName = "MD5";
        //源密码
        Object credentials = "123456";
        //盐值
        Object salt = null;
        //加密次数
        int hashIterations = 10;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }

    /*加盐md5加密*/
    public static void md5AndSalt(){
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        //盐值一般为唯一值，如用户名，手机号等
        Object salt = null;
       // salt = ByteSource.Util.bytes("wsd2");
        salt = ByteSource.Util.bytes("wsd3");
        int hashIterations = 10;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }

    /*不加盐sha1加密*/
    public static void sha1(){
        //加密方式
        String hashAlgorithmName = "SHA1";
        //源密码
        Object credentials = "123456";
        //盐值
        Object salt = null;
        //加密次数
        int hashIterations = 10;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }
}
