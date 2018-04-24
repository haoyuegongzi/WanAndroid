package com.wanandroid.logic.mainpage.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanandroid.R;

import java.util.List;

/**
 * 作者：chen1 on 2018/3/20 14
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class DrawerLayoutAdapter extends BaseAdapter{
    Context context;
    List<String> drawerItemTitle;
    List<Integer> drawerItemIcon;

    public DrawerLayoutAdapter(Context context, List<String> drawerItemTitle, List<Integer> drawerItemIcon){
        this.context = context;
        this.drawerItemTitle = drawerItemTitle;
        this.drawerItemIcon = drawerItemIcon;
    }

//    public void refreshData(List<String> drawerItemTitle, List<Integer> drawerItemIcon){
//        this.drawerItemTitle = drawerItemTitle;
//        this.drawerItemIcon = drawerItemIcon;
//        Log.i("TAG", "Adapter的refreshData: drawerItemTitle.size() ===" + drawerItemTitle.size() + "\n" +
//                                "drawerItemIcon.size() ===" + drawerItemIcon.size());
//        notifyDataSetChanged();
//    }

    @Override
    public int getCount() {
        return drawerItemTitle.size() > 0 ? drawerItemTitle.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return drawerItemTitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_drawer_layout, null);
            holder = new ViewHolder();
            holder.tvDrawerItemIcon = convertView.findViewById(R.id.tvDrawerItemIcon);
            holder.tvDrawerItemTitle = convertView.findViewById(R.id.tvDrawerItemTitle);
            holder.tvDrawerItemEntrence = convertView.findViewById(R.id.tvDrawerItemEntrence);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvDrawerItemIcon.setImageResource(drawerItemIcon.get(position));
        holder.tvDrawerItemTitle.setText(drawerItemTitle.get(position));
        holder.tvDrawerItemEntrence.setImageResource(R.mipmap.entrence);
        return convertView;
    }

    static class ViewHolder{
        TextView tvDrawerItemTitle;
        ImageView tvDrawerItemIcon, tvDrawerItemEntrence;
    }
}
