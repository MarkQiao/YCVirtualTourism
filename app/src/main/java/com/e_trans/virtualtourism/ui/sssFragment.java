package com.e_trans.virtualtourism.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_trans.virtualtourism.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class sssFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";

    public static sssFragment newInstance(String title)
    {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        sssFragment fragment = new sssFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);


    }
    public static String getJson(String fileName,Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
