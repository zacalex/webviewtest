package com.andorid.compatyoutube;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WebSelectActivity extends AppCompatActivity {

    private static final ArrayList<WebSite> mWebSites;
    static {
        mWebSites = new ArrayList<>();
        mWebSites.add(new WebSite("Youtube", "https://www.youtube.com"));
        mWebSites.add(new WebSite("Netflix", "https://www.netflix.com"));
        mWebSites.add(new WebSite("Tesla", "https://www.tesla.com/support/model-3-videos"));
        mWebSites.add(new WebSite("Hulu", "https://www.hulu.com"));
        mWebSites.add(new WebSite("CBS", "https://www.cbs.com"));
        mWebSites.add(new WebSite("Amazon prime", "https://www.amazon.com"));
        mWebSites.add(new WebSite("ABC", "https://www.abc.com"));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_selection);
        ListView webList = (ListView) findViewById(R.id.weblist);
        WebListAdapter webListAdapter = new WebListAdapter(this, mWebSites);
        webList.setAdapter(webListAdapter);
    }
}

class WebSite {
    String mDisplayName;
    String mUrl;

    public WebSite(String name, String url){
        mDisplayName = name;
        mUrl = url;
    }

    @NonNull
    @Override
    public String toString() {
        return mDisplayName;
    }
}

