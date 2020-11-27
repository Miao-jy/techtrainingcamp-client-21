package com.group21.recyclerview;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group21.recyclerview.domain.Message;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity {

    private List<Message> messageList = new ArrayList<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMessages();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        MessageAdapt adapt = new MessageAdapt(messageList,MainActivity.this);
        recyclerView.setAdapter(adapt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }

    private void initMessages() {
        Message message0 = new Message(
                "event_01", "2020字节跳动全球员工摄影大赛邀请函", "bytedance",
                "2020年10月7日", 0);
        messageList.add(message0);

        Message message1 = new Message(
                "event_02", "Lark·巡洋计划开发者大赛圆满结束", "bytedance",
                "2019年10月7日", 1, R.drawable.event_02);
        messageList.add(message1);

        Message message2 = new Message(
                "bytetalk_01", "绝对坦率：打造反馈文化", "bytedance",
                "2020年7月7日", 2, R.drawable.tancheng);
        messageList.add(message2);

        Message message3 = new Message(
                "teamBuilding_04", "4-12 虹桥天地，蹦起来吧！", "vc team",
                "2019年4月11日", 3, R.drawable.teambuilding_04);
        messageList.add(message3);

        Message message4 = new Message(
                "teamBuilding_09", "9月18日淀山湖户外团建", "vc mobile team",
                "2020年9月7日", 4,
                new int[]{R.drawable.tb09_1, R.drawable.tb09_2, R.drawable.tb09_3, R.drawable.tb09_4});
        messageList.add(message4);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}