package com.prpr894.picturedemo.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.ImageView;

import com.prpr894.picturedemo.R;
import com.prpr894.picturedemo.constant.IPConstants;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class PicassoUtil {
    private static PicassoUtil instance;
    private static OkHttpClient client;
    private static Picasso picasso;

    public static PicassoUtil whit(Context context) {
        if (instance == null) {
            instance = new PicassoUtil();
        }
        if (client == null) {
            client = new OkHttpClient.Builder().cache(new Cache(Environment.getDownloadCacheDirectory(),1024*1024*100))
                    .addInterceptor(chain -> {
                        Request newRequest = chain.request().newBuilder()
                                .removeHeader("User-Agent")
                                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                                .removeHeader("Accept")
                                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                                .removeHeader("Accept-Encoding")
                                .addHeader("Accept-Encoding", "gzip, deflate, br")
                                .removeHeader("Accept-Language")
                                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                                .removeHeader("Connection")
                                .addHeader("Connection", "keep-alive")
                                .removeHeader("Upgrade-Insecure-Requests")
                                .addHeader("Upgrade-Insecure-Requests", "1")
                                .removeHeader("Cache-Control")
                                .addHeader("Cache-Control", "max-age=0")
                                .removeHeader("Referer")
                                .addHeader("Referer", IPConstants.BASE_URL)
                                .build();
                        return chain.proceed(newRequest);
                    })
                    .build();
        }
        if (picasso == null) {
            picasso = new Picasso.Builder(context.getApplicationContext())
                    .downloader(new OkHttp3Downloader(client))
                    .build();
        }
        return instance;
    }

    public void loadPicture(String url, final String refererUrl, ImageView imageView) {
        client.newBuilder().cache(new Cache(Environment.getDownloadCacheDirectory(),1024*1024*100)).addInterceptor(chain -> {
            Request newRequest = chain.request().newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                    .removeHeader("Accept")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .removeHeader("Accept-Encoding")
                    .addHeader("Accept-Encoding", "gzip, deflate, br")
                    .removeHeader("Accept-Language")
                    .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                    .removeHeader("Connection")
                    .addHeader("Connection", "keep-alive")
                    .removeHeader("Upgrade-Insecure-Requests")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .removeHeader("Cache-Control")
                    .addHeader("Cache-Control", "max-age=0")
                    .removeHeader("Referer")
                    .addHeader("Referer", refererUrl)
                    .build();
            return chain.proceed(newRequest);
        });
        picasso.load(url)
                .placeholder(R.drawable.loading)
                .into(imageView);
    }
}
