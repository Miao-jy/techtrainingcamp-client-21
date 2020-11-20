package com.group21.recyclerview;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
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
        String title = getIntent().getStringExtra("title");
        TextView textTitle = findViewById(R.id.article_title);
        textTitle.setText(title);
        String author = getIntent().getStringExtra("author");
        TextView textAuthor = findViewById(R.id.article_author);
        textAuthor.setText(author);
        String publishTime = getIntent().getStringExtra("publishTime");
        TextView textPublishTime = findViewById(R.id.article_publishTime);
        textPublishTime.setText(publishTime);
        TextView textView = findViewById(R.id.article_id);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        try {
            getArticle(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "article: " + article);
        try {
            if (article != null) {
                textView.setText(parseText(article));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                            .url("https://vcapi.lvdaqian.cn/article/" + id + "?markdown=true")
                            //.url("https://vcapi.lvdaqian.cn/article/" + id)
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

    private SpannableStringBuilder parseText(String article) throws IOException {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        BufferedReader reader = new BufferedReader(new StringReader(article));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.length() == 0) {
                spannableStringBuilder.append("\n");
                Log.d(TAG, "空行_" + "\n" + "_");
            } else if (line.charAt(0) == '#' && line.charAt(1) == '#') {
                line = line.substring(2);
                SpannableString string = new SpannableString(line);
                string.setSpan(new AbsoluteSizeSpan(16, true), 0, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                string.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(string).append("\n");
                Log.d(TAG, "二级标题_" + string + "_");
            } else if (line.charAt(0) == '#' && line.charAt(1) != '#') {
                line = line.substring(1);
                SpannableString string = new SpannableString(line);
                string.setSpan(new AbsoluteSizeSpan(17, true), 0, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                string.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(string).append("\n");
                Log.d(TAG, "一级标题_" + string + "_");
            } else if (line.charAt(0) == '-') {
                line = "   ·" + line.substring(1);
                SpannableString string = new SpannableString(line);
                string.setSpan(new AbsoluteSizeSpan(15, true), 0, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(string).append("\n");
                Log.d(TAG, "无序列表_" + string + "_");
            } else if ((line.charAt(0) == '1' || line.charAt(0) == '2'
                    || line.charAt(0) == '3' || line.charAt(0) == '4'
                    || line.charAt(0) == '5' || line.charAt(0) == '6'
                    || line.charAt(0) == '7' || line.charAt(0) == '8'
                    || line.charAt(0) == '9') && line.charAt(1) == '.') {
                line = "    " + line;
                SpannableString string = new SpannableString(line);
                string.setSpan(new AbsoluteSizeSpan(15, true), 0, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(string).append("\n");
                Log.d(TAG, "有序列表_" + string + "_");
            } else if (line.charAt(0) == '!' && line.charAt(1) == '[') {
                char[] chars = line.toCharArray();
                int start = 0, end = 0;
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == '(') {
                        start = i;
                    } else if (chars[i] == ')') {
                        end = i;
                    }
                }
                String resourceName = new String(chars, start + 1, end - start).toLowerCase().split("\\.")[0];
                Log.d(TAG, "resourceName: " + resourceName);
                int id = getResources().getIdentifier(resourceName, "drawable", getPackageName());
                Log.d(TAG, "resourceId: " + id);
                Drawable drawable = getResources().getDrawable(id);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                SpannableString string = new SpannableString(line);
                string.setSpan(new ImageSpan(drawable), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(string).append('\n');
            } else {
                SpannableString string = new SpannableString(line);
                string.setSpan(new AbsoluteSizeSpan(15, true), 0, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(string).append("\n");
                Log.d(TAG, "正文_" + string + "_");
            }
        }
        return spannableStringBuilder;
    }
}