package edu.health.config;

import edu.health.util.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;

public class CmsConfig {

    //用户状态
    private static Map<String,String> userState;

    //系统配置
    public final static String CMS_NAME="健康管理系统";
    public final static String CMS_DESC="";
    public final static String CMS_CPRT="版权所有&copy;xx科技 张三所有";
    public final static String BROWSER_TITLE="健康管理系统";
    public final static String BROWSER_DESC="健康管理系统";

    //公共文件目录
    private static String uploadPath = null;

    //默认图片
    public final static String CMS_NOIMG = "/lib/blogres/img/no_img.jpg";
    //默认头像
    public final static String CMS_AVATAR = "/lib/blogres/img/header2.png";

    //分类缓存
    //todo 设置分类缓存时间
    private static Map<String,String> catMap = null;
    private static long catMapStartDate = 0;
    private static long catMapTimeExpired = 60;
    //用户信息缓存

    //附件信息
    public final  static String  STR_JA="chapter_ja";//教案
    public final  static String  STR_KJ="chapter_kj";//课件
    public final  static String  STR_VIDEO="chapter_video";//是视频


    public static Map<String,String> getUserState(){
        if(userState == null){
            userState = new HashMap<>();
            userState.put("0","禁用");
            userState.put("1","正常");
        }
        return userState;
    }

    public static String getUploadPath(){
        if(uploadPath == null || uploadPath.length() == 0){
            PropertiesUtil.readProperties("application.properties");
            uploadPath = PropertiesUtil.getProperty("upload.uploadPath");
        }
        return uploadPath;
    }




}
