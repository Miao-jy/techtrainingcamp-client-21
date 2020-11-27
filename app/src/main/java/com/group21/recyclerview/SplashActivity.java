package com.group21.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * app的启动界面
 */
public class SplashActivity extends Activity {
    private Handler handler = new Handler();
    private boolean isStartMainActivity = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, 1200);
    }

    private void startMainActivity() {
        if (!isStartMainActivity){
            isStartMainActivity = true;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startMainActivity();
        return super.onTouchEvent(event);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy() {
        // 移除延迟函数
        handler.removeCallbacks(null);
        super.onDestroy();
    }
}
