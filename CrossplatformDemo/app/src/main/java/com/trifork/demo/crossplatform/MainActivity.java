package com.trifork.demo.crossplatform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xplatformdemo.libsharedcode.NetworkingHelper;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView text = findViewById(R.id.some_text);

        NetworkingHelper helper = new NetworkingHelper();
        try {
            JSONArray json = helper.getData();
            StringBuilder jsonText = new StringBuilder();
            for (int i=0; i<json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                jsonText.append("Username:"+obj.getString("username")+" Password:"+obj.getString("password")+"\n");
            }
            text.setText(jsonText.toString());
        } catch (Exception e) {
            text.setText(e.getMessage());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

