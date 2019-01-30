package com.e_trans.virtualtourism.Base;

/**
 * @author MarkQiao
 * @create 2019/1/22
 * @Describe
 */
public class Url {
    public static String URLPublic="http://39.104.51.116:14808/hpai/";
    public static String URLGZ="http://app.quanjingke.com/uploadfile/720/";
    public static String URLYY="http://app.quanjingke.com/uploadfile/720audio/";
    public static String getURLWEB(int i){
        if(i==0){
            return "http://m.auyou.cn/xianlu/list.asp?areano=060101";
        }else if(i==1){
            return "http://m.auyou.cn/jingdian/list.asp?areano=060101";
        }else if(i==2){
            return "http://m.auyou.cn/gonglve/list.asp?areano=060101";
        }else {
            return "http://m.auyou.cn/city/situational.asp?areano=060101";
        }
    }
}
