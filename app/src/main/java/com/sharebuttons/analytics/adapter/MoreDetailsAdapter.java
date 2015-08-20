package com.sharebuttons.analytics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sharebuttons.analytics.Clicky.MoreDetails;
import com.sharebuttons.analytics.R;

/**
 * Created by Monkey D Luffy on 5/3/2015.
 */
public class MoreDetailsAdapter extends BaseAdapter {
    private Context mContext;
    private MoreDetails[] mMoreDetails;

    public MoreDetailsAdapter(Context context, MoreDetails[] moreDetails) {
        mContext = context;
        mMoreDetails = moreDetails;
    }

    @Override
    public int getCount() {
        return mMoreDetails.length;
    }

    @Override
    public Object getItem(int i) {
        return mMoreDetails[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.two_item_row, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        MoreDetails moreDetails = mMoreDetails[position];

        holder.title.setText(moreDetails.getTitle());

        holder.value.setText(moreDetails.getValue());

        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView value;

        public ViewHolder(View convertView) {
            title = (TextView) convertView.findViewById(R.id.titleTextView);
            value = (TextView) convertView.findViewById(R.id.valueTextView);
        }
    }
}
