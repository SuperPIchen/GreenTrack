package com.superpichen.mainlibrary.Tools.JavaTools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shehuan.niv.NiceImageView;
import com.superpichen.mainlibrary.R;

import java.util.List;

public class OfflineShopRecycleAdapter extends RecyclerView.Adapter<OfflineShopRecycleAdapter.ViewHolder> {
    private Context context;
    private List<FraOfflineRecycleInfo> data;

    public OfflineShopRecycleAdapter(Context context, List<FraOfflineRecycleInfo> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_fraoffline_recycleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.NvFraOfflineImg.setImageResource(data.get(position).getImg());
        holder.TvFraOfflineTitle.setText(data.get(position).getTitle());
        holder.TvFraOfflineDizhi.setText(data.get(position).getLocal());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        NiceImageView NvFraOfflineImg;
        TextView TvFraOfflineTitle;
        TextView TvFraOfflineDizhi;

        public ViewHolder(View itemView) {
            super(itemView);
            NvFraOfflineImg = itemView.findViewById( R.id.NvFraOfflineImg );
            TvFraOfflineTitle = itemView.findViewById( R.id.TvFraOfflineTitle );
            TvFraOfflineDizhi = itemView.findViewById( R.id.TvFraOfflineDizhi );
        }
    }
}
