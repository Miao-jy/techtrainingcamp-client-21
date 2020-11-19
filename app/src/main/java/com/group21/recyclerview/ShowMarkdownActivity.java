package com.group21.recyclerview;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

public class ShowMarkdownActivity extends AppCompatActivity {

    private String article;
    private String TAG = "ShowMarkdownActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_markdown);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        String id = getIntent().getStringExtra("id");
        TextView textView = (TextView) findViewById(R.id.article_id);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        try {
            getArticle(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "article: " + article);
        textView.setText(article);
    }

    private void getArticle(final String id) throws InterruptedException {
        Thread articleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences pref = getBaseContext().getSharedPreferences("token", MODE_PRIVATE);
                    final String token = pref.getString("token", "");
                    OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60, TimeUnit.SECONDS)
                            .authenticator(new Authenticator() {
                                @Override
                                public Request authenticate(Route route, Response response) throws IOException {
                                    return response.request().newBuilder().header("Authorization", "Bearer " + token).build();
                                }
                            })
                            .build();
                    Request request = new Request.Builder()
                            .url("https://vcapi.lvdaqian.cn/article/" + id)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    ArticleResponse articleResponse = gson.fromJson(responseData, ArticleResponse.class);
                    article = articleResponse.getData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        articleThread.start();
        articleThread.join();
    }
}