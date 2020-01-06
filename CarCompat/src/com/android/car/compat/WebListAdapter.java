package com.andorid.car.compat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class WebListAdapter extends ArrayAdapter<WebSite> {
    public WebListAdapter(Context context, ArrayList<WebSite> webSites) {
        super(context,0,webSites);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final WebSite web = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.web_listview, parent, false);
        }

        TextView displayName = convertView.findViewById( R.id.label);
        displayName.setText(web.mDisplayName);
        displayName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WebViewBrowserActivity.class);
                intent.setData(Uri.parse(web.mUrl));
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
