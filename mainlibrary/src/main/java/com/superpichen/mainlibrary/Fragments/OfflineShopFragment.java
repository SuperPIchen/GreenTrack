package com.superpichen.mainlibrary.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfflineShopFragment extends BaseFragment {


    public OfflineShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offline_shop, container, false);
    }

    @Override
    protected View initView() {
        return null;
    }

}
