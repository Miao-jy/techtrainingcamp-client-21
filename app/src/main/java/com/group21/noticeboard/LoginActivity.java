package com.group21.noticeboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.group21.noticeboard.domain.LoginResponse;

import java.util.concurrent.TimeUnit;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 登录界面
 */
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
        usernameEdit = findViewById(R.id.username_edit);
        passwordEdit = findViewById(R.id.password_edit);
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        checkPassword = findViewById(R.id.display_password);
        checkPassword.setOnClickListener(this);
    }

    /**
     *绑定点击事件
     * 点击登录按钮发送网络请求
     * 点击显示密码，展示或隐藏密码
     */
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

    /**
     * 开辟新线程发送网络请求
     */
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

    /**
     * 从请求结果中解析token
     */
    private String getToken(String responseData) {
        Gson gson = new Gson();
        LoginResponse loginResponse = gson.fromJson(responseData, LoginResponse.class);
        return loginResponse.getToken();
    }

    /**
     *持久化保存token
     */
    private void saveToken(String token) {
        SharedPreferences.Editor editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        editor.putString("token", token);
        editor.apply();
    }

    /**
     *修改字体
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}