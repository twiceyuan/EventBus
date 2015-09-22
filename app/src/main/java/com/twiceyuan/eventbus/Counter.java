package com.twiceyuan.eventbus;

import com.squareup.otto.Subscribe;
import com.twiceyuan.eventbus.event.ChatReadEvent;
import com.twiceyuan.eventbus.event.EventBus;
import com.twiceyuan.eventbus.event.MineReadEvent;
import com.twiceyuan.eventbus.event.NewsReadEvent;

/**
 * Created by twiceYuan on 9/22/15.
 *
 * 计数器类，订阅事件总线来维护订阅数
 */
public class Counter {

    private static final Counter counter = new Counter();

    public int chatUnread = 0;
    public int newsUnread = 0;
    public int mineUnread = 0;

    private Counter() {
        EventBus.getBus().register(this);
    }

    public static Counter getCounter() {
        return counter;
    }

    public static void destory() {
        EventBus.getBus().unregister(getCounter());
    }

    @Subscribe
    public void readNews(NewsReadEvent event) {
        if (newsUnread == 0) return;
        newsUnread--;
    }

    @Subscribe
    public void readChat(ChatReadEvent event) {
        if (chatUnread == 0) return;
        chatUnread--;
    }

    @Subscribe
    public void readMine(MineReadEvent event) {
        if (mineUnread == 0) return;
        mineUnread--;
    }
}
