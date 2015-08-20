package com.sharebuttons.analytics.adapter;

/**
 * Created by heman_000 on 4/10/2015.
 *//*
public class ListAdapter extends BaseAdapter {

    LayoutInflater inflater;
    private MoreDetails[] mMoreDetails;

    public ListAdapter(Context context, MoreDetails[] details) {

        mMoreDetails = details;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mMoreDetails.length;
    }

    @Override
    public Object getItem(int position) {
        return mMoreDetails[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        ItemViewHolder holder;

        View row = convertView;
        if (row == null) {
            row = inflater.inflate(R.layout.two_list, viewGroup, false);
            holder = new ItemViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ItemViewHolder) row.getTag();
        }
        MoreDetails details = mMoreDetails[position];

        holder.NavListItemDate.setText(details.getDate());
        holder.NavListItemText.setText(details.getItem());

        return row;
    }

    public static class ItemViewHolder {
        TextView NavListItemDate, NavListItemText;

        ItemViewHolder(View v) {
            NavListItemDate = (TextView) v.findViewById(R.id.dateView);
            NavListItemText = (TextView) v.findViewById(R.id.itemView);
        }
    }


}
*/


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
public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private MoreDetails[] mMoreDetails;

    public ListAdapter(Context context, MoreDetails[] moreDetails) {
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

            convertView = LayoutInflater.from(mContext).inflate(R.layout.two_list, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        MoreDetails moreDetails = mMoreDetails[position];

        holder.title.setText(moreDetails.getDate());

        holder.value.setText(moreDetails.getItem());

        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView value;

        public ViewHolder(View convertView) {
            title = (TextView) convertView.findViewById(R.id.dateView);
            value = (TextView) convertView.findViewById(R.id.itemView);
        }
    }
}
