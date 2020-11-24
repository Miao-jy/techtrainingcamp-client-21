package com.group21.recyclerview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText usernameEdit;
    private EditText passwordEdit;
    private CheckBox checkPassword;
    private String username;
    private String password;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        usernameEdit = findViewById(R.id.username_edit);
        passwordEdit = findViewById(R.id.password_edit);
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        checkPassword = findViewById(R.id.display_password);
        checkPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {
            username = usernameEdit.getText().toString();
            password = passwordEdit.getText().toString();
            sendRequestWithHttpURLConnection();
            Toast.makeText(v.getContext(), "登录成功", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (v.getId() == R.id.display_password) {
            if (checkPassword.isChecked()) {
                passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        }
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60, TimeUnit.SECONDS)
                            .build();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username", username)
                            .add("password", password)
                            .build();
                    Request request = new Request.Builder()
                            .url("https://vcapi.lvdaqian.cn/login")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    String token = getToken(responseData);
                    saveToken(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String getToken(String responseData) {
        Gson gson = new Gson();
        LoginResponse loginResponse = gson.fromJson(responseData, LoginResponse.class);
        return loginResponse.getToken();
    }

    private void saveToken(String token) {
        SharedPreferences.Editor editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        editor.putString("token", token);
        editor.apply();
    }
}