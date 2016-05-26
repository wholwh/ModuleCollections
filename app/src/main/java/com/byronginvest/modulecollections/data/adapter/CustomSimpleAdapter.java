package com.byronginvest.modulecollections.data.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
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
 * Created by Gosha on 2015-12-23.
 */
public class CustomSimpleAdapter extends SimpleAdapter {
    private int[] mTo;
    private String[] mFrom;
    private Context ctx;
    private List<? extends Map<String, ?>> mData;


    public CustomSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.ctx = context;
        this.mFrom = from;
        this.mTo = to;
        this.mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HashMap<String, String> map = (HashMap<String, String>) mData.get(position);
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = View.inflate(ctx, R.layout.listitem_listview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
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
        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.content)
        TextView content;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
