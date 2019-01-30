package com.e_trans.virtualtourism.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author MarkQiao
 * @create 2019/1/22
 * @Describe
 */
public class videoBean implements Parcelable {
    public  videoBean(String imgPath,String vdioPath,String Explain ){
        this.ImgPath=imgPath;
        this.vdioPath=vdioPath;
        this.Explain=Explain;
    }

    protected videoBean(Parcel in) {
        vdioPath = in.readString();
        ImgPath = in.readString();
        Explain = in.readString();
    }

    public static final Creator<videoBean> CREATOR = new Creator<videoBean>() {
        @Override
        public videoBean createFromParcel(Parcel in) {
            return new videoBean(in);
        }

        @Override
        public videoBean[] newArray(int size) {
            return new videoBean[size];
        }
    };

    public String getVdioPath() {
        return vdioPath;
    }

    public void setVdioPath(String vdioPath) {
        this.vdioPath = vdioPath;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
    }
    public String getExplain() {
        return Explain;
    }
    public void setExplain(String explain) {
        Explain = explain;
    }
    private String vdioPath;
    private String ImgPath;
    private String Explain;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vdioPath);
        dest.writeString(ImgPath);
        dest.writeString(Explain);
    }
}
