package com.android.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.myapplication.model.MainData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.HolderView> implements View.OnClickListener {
    private Context context;
    private ArrayList<MainData> data;
    private setItemOnClickListener mItemOnClickListener;
    public MainAdapter(Context context, ArrayList<MainData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public void onClick(View view) {

    }
    // 定义一个接口方法用来绑定事件
    public void setmItemOnClickListener(setItemOnClickListener mItemOnClickListener) {
        this.mItemOnClickListener = mItemOnClickListener;
    }

    // 定义点击事件的接口
    public interface setItemOnClickListener {
        void onClick(int position);
    }



    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activity_main, parent, false);
        HolderView holderView = new HolderView(view);
        return holderView;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {
        MainData mainData = data.get(position);
        holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
        holder.title.setText(mainData.getTitle());
        holder.subTitle.setText(mainData.getSubTitle());
        holder.subTitleTwo.setText(mainData.getSubTitleTwo());
        holder.itemView.setOnClickListener(view -> mItemOnClickListener.onClick(position));
    }



    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class HolderView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, subTitle, subTitleTwo;

        public HolderView(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
            subTitleTwo = itemView.findViewById(R.id.subTitleTwo);
        }
    }
}
