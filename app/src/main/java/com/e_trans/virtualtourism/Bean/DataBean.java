package com.e_trans.virtualtourism.Bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class DataBean implements Serializable {


    private int goodsId;
    private int goodsType;
    private String goodsUrl;
    private int type;
    private String goodsDesc;
    private String goodsName;
    private String goodsPrice;
    private String address;
    private String common;
    private String date;
    private String count;

    public String getCount() {
        return count == null ? "" : count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommon() {
        return common == null ? "" : common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getDate() {
        return date == null ? "" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private List<GoodsInfoBean> goodsInfo;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public List<GoodsInfoBean> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<GoodsInfoBean> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public static class GoodsInfoBean implements Serializable {


        private int goodsId;
        private int goodsType;
        private String goodsUrl;
        private String goodname;
        private String goodsDesc;
        private String goodsPrice;
        private String address;
        private String common;
        private String date;
        private String count;
        private String shopName;

        public String getShopName() {
            return shopName == null ? "" : shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getGoodsDesc() {
            return goodsDesc == null ? "" : goodsDesc;
        }

        public void setGoodsDesc(String goodsDesc) {
            this.goodsDesc = goodsDesc;
        }

        public String getGoodsPrice() {
            return goodsPrice == null ? "" : goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getAddress() {
            return address == null ? "" : address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCommon() {
            return common == null ? "" : common;
        }

        public void setCommon(String common) {
            this.common = common;
        }

        public String getDate() {
            return date == null ? "" : date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCount() {
            return count == null ? "" : count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getGoodname() {
            return goodname == null ? "" : goodname;
        }

        public void setGoodname(String goodname) {
            this.goodname = goodname;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(int goodsType) {
            this.goodsType = goodsType;
        }

        public String getGoodsUrl() {
            return goodsUrl;
        }

        public void setGoodsUrl(String goodsUrl) {
            this.goodsUrl = goodsUrl;
        }
    }
}
