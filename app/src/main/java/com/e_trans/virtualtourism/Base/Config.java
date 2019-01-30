package com.e_trans.virtualtourism.Base;

import android.os.Environment;

/**
 * Created by liuzhao on 2018/1/22.
 */

public class Config {

    //保存登录状态下ID
    public static final String LOGIN_DATA_BEAN_ID = "ID";
    //保存登录状态下NAME
    public static final String LOGIN_DATA_BEAN_NAME = "Name";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_ACCOUNT = "Account";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_PASSWORD = "Password";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_STATUS = "Status";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_STATUS_TEXT = "StatusText";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_SORT = "Sort";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_NOTE = "Note";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_ROGANIZE_ID = "OrganizeID";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_ORGANIZE_NAME = "OrganizeName";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_COMPANY_NAME = "CompanyName";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_COMPANY_NUMBER = "CompanyNumber";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_DEPART_ID = "DepartId";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_DEPART_CODE = "DepartCode";
    //保存登录状态下
    public static final String LOGIN_DATA_BEAN_DEPART_NAME = "DepartName";
    //保存登录状态下公司id
    public static final String LOGIN_DATA_BEAN_COMPANY_ID = "CompanyId";

    //执法人员1
    public static final String LAW_ENFORCEMENT_OFFICIALS_ONE = "LawEnforcementOfficialsOne";
    //执法人员1帐号
    public static final String LAW_ENFORCEMENT_OFFICIALS_ONE_NUMBER = "LawEnforcementOfficialsOneNumber";
    //执法人员2
    public static final String LAW_ENFORCEMENT_OFFICIALS_TWO = "LawEnforcementOfficialsTwo";
    //执法人员2帐号
    public static final String LAW_ENFORCEMENT_OFFICIALS_TWO_NUMBER = "LawEnforcementOfficialsTwoNumber";
    //记录人员
    public static final String RECORD = "Record";

    public static final String FilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/HDZFData/";
    public static final String PAGE_SIZE = "page_size";//配置当前列表一次请求多少条数据
    public static final String USER_NAME = "userName";//用户名
    public static final String PASS_WORD = "passWord";//密码
    public static final String SELECTVIEWPAGER = "select";//保存viewpager被选中的page

}
