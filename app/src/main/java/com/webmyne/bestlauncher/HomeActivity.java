package com.webmyne.bestlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.webmyne.bestlauncher.apps.AppListActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void gotoAppList(View view) {
        Intent iAppList = new Intent(this, AppListActivity.class);
        startActivity(iAppList);
    }
}
