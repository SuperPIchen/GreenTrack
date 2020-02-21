package com.superpichen.mainlibrary.Tools.JavaTools;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superpichen.mainlibrary.MyView.PileLayout;
import com.superpichen.mainlibrary.R;

import java.util.ArrayList;
import java.util.List;

public class TujianPileLayoutAdapter extends PileLayout.Adapter {
    private List<TujianPileLayoutInfo> data=new ArrayList<>();
    private Context context;

    public TujianPileLayoutAdapter(List<TujianPileLayoutInfo> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tujian_pilelayout;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void bindView(View view, int index) {
        super.bindView(view, index);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.IvTujianPilePet = (ImageView) view.findViewById(R.id.IvTujianPilePet);
            viewHolder.TvTujianPileName=view.findViewById(R.id.TvTujianPileName);
            viewHolder.TvTujianPileId = view.findViewById(R.id.TvTujianPileId);
            viewHolder.RlTujianPileBackground = view.findViewById(R.id.RlTujianPileBackground);
            view.setTag(viewHolder);
        }
        viewHolder.IvTujianPilePet.setImageResource(data.get(index).getImg());
        viewHolder.TvTujianPileName.setText(data.get(index).getName());
        viewHolder.TvTujianPileId.setText(data.get(index).getId());
        switch (index%3){
            case 0:
                viewHolder.RlTujianPileBackground.setBackgroundResource(R.drawable.yuanjiaojuxing_purple_shape);
                break;
            case 1:
                viewHolder.RlTujianPileBackground.setBackgroundResource(R.drawable.yuanjiaojuxing_blue_shape);
                break;
            case 2:
                viewHolder.RlTujianPileBackground.setBackgroundResource(R.drawable.yuanjiaojuxing_green_shape);
                break;
        }
    }

    @Override
    public void displaying(int position) {
        super.displaying(position);
    }

    @Override
    public void onItemClick(View view, int position) {
        super.onItemClick(view, position);
    }

    private class ViewHolder{
        private TextView TvTujianPileName;
        private ImageView IvTujianPilePet;
        private TextView TvTujianPileId;
        private RelativeLayout RlTujianPileBackground;
    }
}
