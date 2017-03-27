package com.pro2on.democlean.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pro2on.democlean.R;

import static java.security.AccessController.getContext;

/**
 * Date: 27.03.17
 * Time: 20:38
 * Created by pro2on in project DemoClean
 */

public class RepositoriesListAdapter extends ArrayAdapter<String> {

    public RepositoriesListAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_repository, parent, false);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(getItem(position));
        return convertView;
    }

    private static class ViewHolder {
        TextView tvName;
    }
}