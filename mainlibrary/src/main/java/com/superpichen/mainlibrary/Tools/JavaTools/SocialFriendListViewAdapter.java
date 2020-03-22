package com.superpichen.mainlibrary.Tools.JavaTools;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shehuan.niv.NiceImageView;
import com.superpichen.mainlibrary.MyView.MyFonts.HanbiaoshuangjiancutiFont;
import com.superpichen.mainlibrary.R;

import java.util.List;

public class SocialFriendListViewAdapter extends BaseAdapter {
    private Context context;
    private List<SocialFriendInfo> data;

    public SocialFriendListViewAdapter(Context context, List<SocialFriendInfo> data) {
        this.context = context;
        this.data = data;
    }

    public SocialFriendListViewAdapter() {
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
            convertView=View.inflate(context, R.layout.item_social_friend,null);
            findViews(holder,convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        SocialFriendInfo info=data.get(position);
        if(info.getPaiming()==1){
            holder.TvItemSocialFriendPai.setVisibility(View.INVISIBLE);
        }else if(info.getPaiming()==2){
            holder.TvItemSocialFriendPai.setVisibility(View.INVISIBLE);
            holder.IvItemSocialFriendPai.setImageResource(R.drawable.social_silver);
        }else if(info.getPaiming()==3){
            holder.TvItemSocialFriendPai.setVisibility(View.INVISIBLE);
            holder.IvItemSocialFriendPai.setImageResource(R.drawable.social_bronze);
        }else {
            holder.IvItemSocialFriendPai.setVisibility(View.INVISIBLE);
            holder.TvItemSocialFriendPai.setText(""+(position+1));
        }
        holder.IvItemSocialFriendTouxiang.setImageResource(info.getImg());
        holder.TvItemSocialFriendName.setText(info.getName());
        holder.TvItemSocialFriendPetCount.setText("获得碳宠物"+info.getPetCount());
        holder.TvItemSocialFriendPoint.setText(""+info.getPoint());
        return convertView;
    }



    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-03-22 23:07:34 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(ViewHolder holder,View view) {
        holder.IvItemSocialFriendPai = view.findViewById( R.id.IvItemSocialFriendPai );
        holder.TvItemSocialFriendPai = view.findViewById( R.id.TvItemSocialFriendPai );
        holder.IvItemSocialFriendTouxiang = view.findViewById( R.id.IvItemSocialFriendTouxiang );
        holder.TvItemSocialFriendName = view.findViewById( R.id.TvItemSocialFriendName );
        holder.TvItemSocialFriendPetCount = view.findViewById( R.id.TvItemSocialFriendPetCount );
        holder.TvItemSocialFriendPoint = view.findViewById( R.id.TvItemSocialFriendPoint );
    }


    class ViewHolder{
        public ImageView IvItemSocialFriendPai;
        public HanbiaoshuangjiancutiFont TvItemSocialFriendPai;
        public NiceImageView IvItemSocialFriendTouxiang;
        public TextView TvItemSocialFriendName;
        public TextView TvItemSocialFriendPetCount;
        public TextView TvItemSocialFriendPoint;
    }
}
