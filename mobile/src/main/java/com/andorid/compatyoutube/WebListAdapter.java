package com.andorid.compatyoutube;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final WebSite web = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.web_listview, parent, false);
        }

        TextView displayName = convertView.findViewById( R.id.label);
        displayName.setText(web.mDisplayName);
        convertView.setOnClickListener(new View.OnClickListener() {
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
