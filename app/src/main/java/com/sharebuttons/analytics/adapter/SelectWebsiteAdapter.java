package com.sharebuttons.analytics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sharebuttons.analytics.R;

import java.util.ArrayList;

/**
 * Created by Monkey D Luffy on 6/8/2015.
 */
public class SelectWebsiteAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mWebsites;

    public SelectWebsiteAdapter(Context context, ArrayList<String> websites) {
        mContext = context;
        mWebsites = websites;
    }


    @Override
    public int getCount() {
        return mWebsites.size();
    }

    @Override
    public Object getItem(int position) {
        return mWebsites.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.one_item_row, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(mWebsites.get(position));
        return convertView;
    }

    public static class ViewHolder {
        TextView mTextView;

        public ViewHolder(View view) {
            mTextView = (TextView) view.findViewById(R.id.websiteTitle);
        }
    }
}
