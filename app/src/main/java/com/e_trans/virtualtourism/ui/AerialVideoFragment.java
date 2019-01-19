package com.e_trans.virtualtourism.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_trans.virtualtourism.R;


public class AerialVideoFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";

    public static AerialVideoFragment newInstance(String title)
    {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        AerialVideoFragment fragment = new AerialVideoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
