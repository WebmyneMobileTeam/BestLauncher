package com.webmyne.bestlauncher.apps;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.webmyne.bestlauncher.R;

import java.util.ArrayList;
import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<AppModel> mValues;
    private Context _ctx;

    public MyItemRecyclerViewAdapter(Context _ctx) {
        this._ctx = _ctx;
        mValues = new ArrayList<>();
    }


    public MyItemRecyclerViewAdapter(Context _ctx, List<AppModel> items) {
        mValues = items;
        this._ctx = _ctx;
    }

    public void setData(List<AppModel> items){
        this.mValues = items;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_icon_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.txtAppName.setText(mValues.get(position).getLabel());
        holder.imgAppIcon.setImageDrawable(mValues.get(position).getIcon());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtAppName;
        public final ImageView imgAppIcon;
        public AppModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtAppName = (TextView) view.findViewById(R.id.text);
            imgAppIcon = (ImageView) view.findViewById(R.id.icon);
        }

    }
}
