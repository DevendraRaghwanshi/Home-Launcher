package com.homelauncher;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import com.homelauncher.adapter.AppListAdapter;
import com.launcher.test.AppList;
import com.launcher.test.LauncherListener;
import com.launcher.test.model.AppInfo;

import java.util.ArrayList;

public class AppListActivity extends AppCompatActivity implements LauncherListener {
    private ArrayList<AppInfo> filteredModelList, appArrayList;
    private AppListAdapter adapter;

    private AppList appList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        AppCompatEditText etSearch = findViewById(R.id.etSearch);
        RecyclerView recyclerView = findViewById(R.id.rvApps);

        appArrayList = new ArrayList<>();
        filteredModelList = appArrayList;
        adapter = new AppListAdapter(this, appArrayList);
        recyclerView.setAdapter(adapter);

        appList = new AppList(this, this);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filteredModelList = filter(appArrayList, etSearch.getText().toString());

                adapter.setFilter(filteredModelList);
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void appsLoaded(ArrayList<AppInfo> list) {
        appArrayList.clear();
        appArrayList.addAll(list);
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public void appInstalled() {
        appList.reLoad();
    }


    private ArrayList<AppInfo> filter(ArrayList<AppInfo> models, String query) {
        query = query.toLowerCase();
        final ArrayList<AppInfo> filteredModelList = new ArrayList<>();
        for (AppInfo model : models) {
            final String text = model.label.toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}