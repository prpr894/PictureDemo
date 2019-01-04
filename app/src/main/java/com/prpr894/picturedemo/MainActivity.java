package com.prpr894.picturedemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.github.florent37.retrojsoup.RetroJsoup;
import com.prpr894.picturedemo.beans.mainpage.MainPageList;
import com.prpr894.picturedemo.retrojsoupinterfaces.AndroidArsenal;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.main_recycler);
        loadWithRetroJsoup();
    }

    @SuppressLint("CheckResult")
    public void loadWithRetroJsoup() {
        final AndroidArsenal tutosAndroidFrance = new RetroJsoup.Builder()
                .url("https://www.taotu55.net/w/1/list_1_2.html")
                .build()
                .create(AndroidArsenal.class);

        tutosAndroidFrance.projects()
                .toList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mainPageLists -> {
                            for (MainPageList mainPageList : mainPageLists) {
                                Log.d("flag", mainPageList.toString());
                            }
                        },
                        t -> Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }

}
