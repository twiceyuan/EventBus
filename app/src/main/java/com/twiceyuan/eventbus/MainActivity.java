package com.twiceyuan.eventbus;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.squareup.otto.Subscribe;
import com.twiceyuan.eventbus.event.ChatReadEvent;
import com.twiceyuan.eventbus.event.MineReadEvent;
import com.twiceyuan.eventbus.event.NewsReadEvent;
import com.twiceyuan.eventbus.fragment.ChatFragment;
import com.twiceyuan.eventbus.fragment.MineFragment;
import com.twiceyuan.eventbus.fragment.NewsFragment;

import butterknife.ButterKnife;

/**
 * Fragment 的容器 Activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Counter.getCounter().chatUnread = 100;
        Counter.getCounter().newsUnread = 100;
        Counter.getCounter().mineUnread = 100;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.ll_container1, new ChatFragment());
        transaction.add(R.id.ll_container2, new NewsFragment());
        transaction.add(R.id.ll_container3, new MineFragment());
        transaction.commit();

        updateUnread();
    }

    public void press(View view) {
        EventBus.getBus().post(new ChatReadEvent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getBus().unregister(this);
    }

    @Subscribe
    public void onRead(Object event) {
        if (event instanceof ChatReadEvent || event instanceof NewsReadEvent || event instanceof MineReadEvent) {
            updateUnread();
        }
    }

    public void updateUnread() {
        int unread = Counter.getCounter().chatUnread +
                Counter.getCounter().newsUnread +
                Counter.getCounter().mineUnread;
        setTitle("未读：" + unread);
    }
}
