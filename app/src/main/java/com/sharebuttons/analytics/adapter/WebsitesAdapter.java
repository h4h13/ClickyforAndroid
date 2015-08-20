package com.sharebuttons.analytics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sharebuttons.analytics.Clicky.Websites;
import com.sharebuttons.analytics.R;

/**
 * Created by Monkey D Luffy on 5/3/2015.
 */
public class WebsitesAdapter extends BaseAdapter {
    private Context mContext;
    private Websites[] mWebsites;

    public WebsitesAdapter(Context context, Websites[] websites) {
        mContext = context;
        mWebsites = websites;
    }

    @Override
    public int getCount() {
        return mWebsites.length;
    }

    @Override
    public Object getItem(int i) {
        return mWebsites[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.one_item_row, null);
            holder = new ViewHolder();

            holder.title = (TextView) convertView.findViewById(R.id.websiteTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Websites website = mWebsites[position];

        holder.title.setText(website.getHostname());
        return convertView;
    }

    private static class ViewHolder {
        TextView title;
    }
}
