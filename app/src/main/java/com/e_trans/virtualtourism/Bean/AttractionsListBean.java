package com.e_trans.virtualtourism.Bean;

import android.os.Parcel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author MarkQiao
 * @create 2019/1/22
 * @Describe
 */
public class AttractionsListBean {

    public ArrayList<AttractionsBean> getDynamic() {
        return dynamic;
    }

    public void setDynamic(ArrayList<AttractionsBean> dynamic) {
        this.dynamic = dynamic;
    }

    ArrayList<AttractionsBean> dynamic;

    public static class AttractionsBean implements Serializable {

        protected AttractionsBean(Parcel in) {
            audioPlayCount = in.readString();
            title = in.readString();
            description = in.readString();
            address = in.readString();
            lat = in.readString();
            lng = in.readString();
            city = in.readString();
            publishPanoId = in.readString();
        }

        public String getAudioPlayCount() {
           return audioPlayCount;
       }

       public void setAudioPlayCount(String audioPlayCount) {
           this.audioPlayCount = audioPlayCount;
       }

       public String getTitle() {
           return title;
       }

       public void setTitle(String title) {
           this.title = title;
       }

       public String getDescription() {
           return description;
       }

       public void setDescription(String description) {
           this.description = description;
       }

       public String getAddress() {
           return address;
       }

       public void setAddress(String address) {
           this.address = address;
       }

       public String getLat() {
           return lat;
       }

       public void setLat(String lat) {
           this.lat = lat;
       }

       public String getLng() {
           return lng;
       }

       public void setLng(String lng) {
           this.lng = lng;
       }

       public String getCity() {
           return city;
       }

       public void setCity(String city) {
           this.city = city;
       }

       public String getPublishPanoId() {
           return publishPanoId;
       }

       public void setPublishPanoId(String publishPanoId) {
           this.publishPanoId = publishPanoId;
       }

       private String audioPlayCount;  //图片下载地址
        private String title;           //标题
        private String description;     //简介
        private String address;       //地址
        private String lat;       //纬度
        private String lng;       //经度
        private String city;       //城市
        private String publishPanoId;   //图片地址

    }
}

/*      "id": "169",
        "publishPanoId": "9303",
        "title": "黄果树瀑布",
        "audioLength": "93",
        "address": "安顺镇宁布依族苗族自治县",
        "province": "贵州",
        "city": "安顺",
        "lat": "25.989445",
        "lng": "105.670264",
        "audioPlayCount": "10247",
        "commentCount": "17",
        "loveCount": "60",
        "publishTime": "1359080696",
        "description": "黄果树瀑布位于贵州省安顺市镇宁县境内的白水河上，是中国第一大瀑布，也是世界最阔大壮观的瀑布之一。奔腾的河水，从70多米高的悬崖绝壁上飞泻而下，跌落犀牛潭中，发出震耳欲聋的轰响声，好像千鼓齐鸣，震人心魄！瀑布激起的水珠，闪耀飞腾，令人目不暇接。而最奇的莫过于隐在大瀑布半山腰的水帘洞，要进入洞内，必须先穿过大瀑布，这需要相当的勇气。不进水帘洞，就不能真正领略黄果树瀑布的雄奇和壮观，也观赏不到美丽的七色彩虹！但是，这里的震撼远远不止这些！沿着黄果树下游行走6公里，3000多盆盆景，变化多端的奇峰异石，为您带来最美的视觉享受；再往下游走1公里，更能看到“美女榕”、“鸳鸯藤”等树木奇观，让人忍不住的赞叹这神来之笔的奇妙！盛夏时节，这里水量充足，瀑布也展现出了它最壮观的一面，此时游览最佳。",
        "isenjoy": "0",
        "user": {
        "circleId": "21",
        "circleName": "贵州",
        "circleImg": "",
        "circleMiddleImage": "\"\"",
        "circleOriginalImage": "\"\"",
        "circleThumbImage": "\"\"",
        "circleDes": "",
        "friendsCount": "0",
        "followersCount": "0",
        "backgroundPano": "\"\"",
        "panoCount": "13",
        "isfollow": "-2"
        }
        },*/



