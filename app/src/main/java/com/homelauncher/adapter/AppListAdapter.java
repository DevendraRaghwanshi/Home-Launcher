package com.homelauncher.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.homelauncher.R;
import com.launcher.test.model.AppInfo;

import java.util.ArrayList;
import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {
    private Context context;
    private List<AppInfo> list;


    public AppListAdapter(Context context, ArrayList<AppInfo> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AppListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(AppListAdapter.ViewHolder viewHolder, int i) {
        AppInfo info = list.get(i);

        viewHolder.textView.setText(info.label);
        viewHolder.tvPackage.setText(info.packageName);
        viewHolder.tvTarget.setText(info.targetActivity);
        viewHolder.tvVCode.setText("Version Code " + info.versionCode);
        viewHolder.tvVName.setText("Version Name " + info.versionName);
        viewHolder.img.setImageDrawable(info.icon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setFilter(List<AppInfo> providerItems) {
        list = new ArrayList<>();
        list.addAll(providerItems);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView, tvPackage, tvTarget, tvVCode, tvVName;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            //Finds the views from our row.xml
            img = itemView.findViewById(R.id.img);
            textView = itemView.findViewById(R.id.tvName);
            tvPackage = itemView.findViewById(R.id.tvPackage);
            tvTarget = itemView.findViewById(R.id.tvTarget);
            tvVCode = itemView.findViewById(R.id.tvVCode);
            tvVName = itemView.findViewById(R.id.tvVName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(list.get(getAdapterPosition()).packageName.toString());
            context.startActivity(launchIntent);
        }
    }
}