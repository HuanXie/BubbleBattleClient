package com.example.huan.bubblebattle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final ListView listview1 = (ListView) findViewById(R.id.listView1);
        final ListView listview2 = (ListView) findViewById(R.id.listView2);
        String[] titles = new String[] { "Player", "ID", "Totalscore", "Wins" };
        final ArrayList<Info> list = new ArrayList<Info>();
        for (int i = 0; i < titles.length; i++) {
            list.add(new Info(titles[i], "0"));
        }

        /*ArrayList<Info> arrayOfinfos = new ArrayList<Info>();
        InfoAdapter adapter = new InfoAdapter(this, arrayOfinfos);
        JSONArray jsonArray = ...;
        arrayOfinfos = Info.fromJson(jsonArray);
        adapter.addAll(arrayOfinfos);
        listview.setAdapter(adapter);*/

        InfoAdapter infosAd = new InfoAdapter(this,list);
        listview1.setAdapter(infosAd);
        listview2.setAdapter(infosAd);

    }
}
