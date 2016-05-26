package com.byronginvest.modulecollections.data.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.byronginvest.modulecollections.R;
import com.byronginvest.modulecollections.data.util.VariableUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gosha on 2016-02-19.
 */
public class ListAdapter extends RecyclerView.Adapter {
    private List<? extends Map<String, ?>> mData;
    private Context ctx;

    public ListAdapter(Context ctx, List<? extends Map<String, ?>> mData) {
        this.mData = mData;
        this.ctx = ctx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(ctx, R.layout.listitem_listview, null);
        return new ListViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HashMap<String, String> map = (HashMap<String, String>) mData.get(position);
        ListViewViewHolder viewHolder = (ListViewViewHolder) holder;
        //为控件设置值
        if (!VariableUtil.StringUtil.isEmtyOrNull(map.get("title"))) {
            viewHolder.title.setText(map.get("title"));
        }

        if (!VariableUtil.StringUtil.isEmtyOrNull(map.get("content"))) {
            viewHolder.content.setText(map.get("content"));
        }

        if (!VariableUtil.StringUtil.isEmtyOrNull(map.get("image"))) {
            ImageLoader.getInstance().displayImage(map.get("image"), viewHolder.image);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ListViewViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.content)
        TextView content;

        public ListViewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            title = (TextView) itemView.findViewById(R.id.title);
//            image = (ImageView) itemView.findViewById(R.id.image);
//            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
