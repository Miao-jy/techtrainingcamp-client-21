package com.group21.recyclerview;

import android.app.Application;
import android.content.Context;

import org.jetbrains.annotations.NotNull;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.InflateResult;
import io.github.inflationx.viewpump.Interceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
