package com.prpr894.picturedemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prpr894.picturedemo.R;
import com.prpr894.picturedemo.beans.mainpage.MainPageListItem;
import com.prpr894.picturedemo.utils.PicassoUtil;

import java.util.Collection;
import java.util.List;

public class MainPageRecyclerAdapter extends RecyclerView.Adapter<MainPageRecyclerAdapter.ViewHolder> {
    private List<MainPageListItem> mList;
    private Context mContext;

    public MainPageRecyclerAdapter(List<MainPageListItem> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_page, viewGroup, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        PicassoUtil.whit(mContext)
                .loadPicture(mList.get(i).img,mList.get(i).img,viewHolder.mImageView);
        viewHolder.mTextViewTitle.setText(mList.get(i).title);
        viewHolder.mTextViewDescribe.setText(mList.get(i).category);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(Collection<? extends MainPageListItem> collection){
        if (mList!=null) {
            int size = mList.size();
            mList.addAll(collection);
            notifyItemRangeChanged(size,mList.size()-1);
        }
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        private TextView mTextViewTitle,mTextViewDescribe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.img_main_item);
            mTextViewTitle=itemView.findViewById(R.id.tv_main_item_title);
            mTextViewDescribe=itemView.findViewById(R.id.tv_main_item_describe);
        }
    }
}
