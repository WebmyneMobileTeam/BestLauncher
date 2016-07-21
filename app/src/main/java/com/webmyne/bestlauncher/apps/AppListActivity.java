package com.webmyne.bestlauncher.apps;


import android.graphics.Rect;
import android.os.Bundle;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.webmyne.bestlauncher.R;

import java.util.ArrayList;

public class AppListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<AppModel>> {

    private RecyclerView recyclerViewApps;
    private MyItemRecyclerViewAdapter adapter;
    private int mColumnCount = 3;
    private EditText edSearch;
    private ArrayList<AppModel> allApps;
    private ArrayList<AppModel> filteredApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        allApps = new ArrayList<>();
        edSearch = (EditText)findViewById(R.id.edSearch);
        recyclerViewApps = (RecyclerView) findViewById(R.id.recyclerApps);
        recyclerViewApps.setLayoutManager(new GridLayoutManager(this, mColumnCount));
        recyclerViewApps.addItemDecoration(new SpacesItemDecoration(2));
        adapter = new MyItemRecyclerViewAdapter(this);
        recyclerViewApps.setAdapter(adapter);

        // create the loader to load the apps list in background
        getSupportLoaderManager().initLoader(0, null,this);


        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                s = s.toString().toLowerCase();

                if(s.length() == 0){
                    adapter.setData(allApps);
                }else{

                    filteredApps = new ArrayList<AppModel>();
                    for(AppModel appModel : allApps){
                        if(appModel.getLabel().toLowerCase().contains(s)){
                            filteredApps.add(appModel);
                        }
                    }

                    adapter.setData(filteredApps);

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    @Override
    public Loader<ArrayList<AppModel>> onCreateLoader(int id, Bundle args) {
        return new AppsLoader(AppListActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<AppModel>> loader, ArrayList<AppModel> data) {
        allApps = data;
        adapter.setData(allApps);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<AppModel>> loader) {
        adapter.setData(null);
    }


    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}
