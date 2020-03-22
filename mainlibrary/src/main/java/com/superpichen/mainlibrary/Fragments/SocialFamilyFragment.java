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
public class SocialFamilyFragment extends BaseFragment {

    public SocialFamilyFragment() {
        // Required empty public constructor
    }

    @Override
    protected View initView() {
        View view=View.inflate(getContext(),R.layout.fragment_social_family,null);
        return view;
    }
}
