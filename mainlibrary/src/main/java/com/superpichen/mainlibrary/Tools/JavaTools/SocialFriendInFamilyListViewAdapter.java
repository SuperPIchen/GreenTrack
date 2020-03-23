package com.superpichen.mainlibrary.Tools.JavaTools;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shehuan.niv.NiceImageView;
import com.superpichen.mainlibrary.R;

import java.util.List;

public class SocialFriendInFamilyListViewAdapter extends BaseAdapter {
    private Context context;
    private List<SocialFriendInfo> data;

    public SocialFriendInFamilyListViewAdapter(Context context, List<SocialFriendInfo> data) {
        this.context = context;
        this.data = data;
    }

    public SocialFriendInFamilyListViewAdapter() {
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context, R.layout.item_social_friendinfamily,null);
            findViews(holder,convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        SocialFriendInfo info=data.get(position);
        holder.IvItemSocialFriendInFamilyTouxiang.setImageResource(info.getImg());
        holder.TvItemSocialFriendInFamilyName.setText(info.getName());
        ViewHolder finalHolder = holder;
        holder.TvItemSocialSendInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalHolder.TvItemSocialSendInvite.getText().toString().equals("发送邀请")){
                    finalHolder.TvItemSocialSendInvite.setText("取消邀请");
                }else {
                    finalHolder.TvItemSocialSendInvite.setText("发送邀请");
                }

            }
        });
        return convertView;
    }



    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-03-22 23:07:34 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(ViewHolder holder,View view) {
        holder.IvItemSocialFriendInFamilyTouxiang = view.findViewById( R.id.IvItemSocialFriendInFamilyTouxiang );
        holder.TvItemSocialFriendInFamilyName = view.findViewById( R.id.TvItemSocialFriendInFamilyName );
        holder.TvItemSocialSendInvite = view.findViewById( R.id.TvItemSocialSendInvite );
    }


    class ViewHolder{
        public NiceImageView IvItemSocialFriendInFamilyTouxiang;
        public TextView TvItemSocialFriendInFamilyName;
        public TextView TvItemSocialSendInvite;
    }
}
