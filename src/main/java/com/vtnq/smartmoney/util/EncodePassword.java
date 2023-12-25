package com.vtnq.smartmoney.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class EncodePassword {
    private static String NewHashCode;
    public static String hashCodeInDatabase;
    private static String Base64Code;
    private static void CreateHashCode(){
        Random random=new Random();
        int min=10000000;
        int max=99999999;
        NewHashCode= String.valueOf(random.nextInt(max-min+1)+min);
    }
    private static void ChangeToBase64New(String password){
        String hashCode=password+NewHashCode;
        Base64Code= Base64.getEncoder().encodeToString(hashCode.getBytes());
    }
    private static void ChangeToBase64DB(String password){
        String hashCode=password+hashCodeInDatabase;
        Base64Code= Base64.getEncoder().encodeToString(hashCode.getBytes());
    }
    public static String MD5(String password){
        StringBuilder stringBuilder = null;
        ChangeToBase64New(password);
        ChangeToBase64DB(password);
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            messageDigest.update(Base64Code.getBytes());
            byte[]digest=messageDigest.digest();
            stringBuilder=new StringBuilder();
            for(byte b:digest){
                stringBuilder.append(String.format("%02x",b&0xff));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
