package com.prpr894.picturedemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.github.florent37.retrojsoup.RetroJsoup;
import com.prpr894.picturedemo.adapters.MainPageRecyclerAdapter;
import com.prpr894.picturedemo.beans.mainpage.MainPageListItem;
import com.prpr894.picturedemo.retrojsoupinterfaces.AndroidArsenal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private List<MainPageListItem> mList;
    private MainPageRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.main_recycler);
        mAdapter = new MainPageRecyclerAdapter(mList, this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mAdapter);
        loadWithRetroJsoup();
    }

    @SuppressLint("CheckResult")
    public void loadWithRetroJsoup() {
        final AndroidArsenal tutosAndroidFrance = new RetroJsoup.Builder()
                .url("https://www.taotu55.net/")
                .build()
                .create(AndroidArsenal.class);

        tutosAndroidFrance.projects()
                .toList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mainPageLists -> {
                            mAdapter.addAll(mainPageLists);
                            for (MainPageListItem mainPageListItem : mainPageLists) {
                                Log.d("flag", mainPageListItem.toString());
                            }
                        },
                        t -> Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }

}
