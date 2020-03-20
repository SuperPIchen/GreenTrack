package com.superpichen.mainlibrary.Tools.JavaTools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.superpichen.mainlibrary.R;

import java.util.List;

public class HomelandBagAdapater extends RecyclerView.Adapter<HomelandBagAdapater.ViewHolder>{
    private Context context;
    private List<HomelandGoodsTool> data;
    private OnItemClickListener mOnItemClickListener;

    public HomelandBagAdapater(Context context, List<HomelandGoodsTool> data) {
        this.context = context;
        this.data = data;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HomelandBagAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_homelandbag_recycleview,parent,false);
        if(view.getTag()==null){
            HomelandBagAdapater.ViewHolder viewHolder=new HomelandBagAdapater.ViewHolder(view);
            view.setTag(viewHolder);
        }
        return (HomelandBagAdapater.ViewHolder) view.getTag();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.IvItemHomelandBagPic.setImageResource(data.get(position).getImg());
        holder.TvItemHomelandBagName.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView IvItemHomelandBagPic;
        TextView TvItemHomelandBagName;
        ViewHolder(View itemView) {
            super(itemView);
            IvItemHomelandBagPic = itemView.findViewById(R.id.IvItemHomelandBagPic);
            TvItemHomelandBagName = itemView.findViewById(R.id.TvItemHomelandBagName);
        }
    }
}
