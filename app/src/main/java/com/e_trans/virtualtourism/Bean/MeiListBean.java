package com.e_trans.virtualtourism.Bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author MarkQiao
 * @create 2019/1/22
 * @Describe
 */
public class MeiListBean {

    public ArrayList<MeisBean> getDynamic() {
        return picture_list;
    }

    public void setDynamic(ArrayList<MeisBean> dynamic) {
        this.picture_list = dynamic;
    }

    ArrayList<MeisBean> picture_list;

    public static class MeisBean implements Serializable {

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        private String create_time;  //时间
        private String location;       //地址
        private String path;   //图片地址

    }
}




