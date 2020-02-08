package com.superpichen.mainlibrary.Tools.JavaTools;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 作用：基类，公共类
 * CommonFragment,ThirdPartyFragment,CustomFragment,OtherFragment等都要继承该类
 */
public abstract class BaseFragment extends Fragment {
    /**
     * 上下文
     */
    protected Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * @return
     * 强制子类重写，实现子类特有的UI
     */
    protected abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当孩子需要初始化数据，或者联网请求绑定数据，展示数据的，等等都可以重写该方法
     */
    protected void initData(){

    }
}
