package com.twiceyuan.eventbus;

import com.squareup.otto.Subscribe;
import com.twiceyuan.eventbus.event.ChatReadEvent;
import com.twiceyuan.eventbus.event.MineReadEvent;
import com.twiceyuan.eventbus.event.NewsReadEvent;
import com.twiceyuan.eventbus.event.ReadEvent;

/**
 * Created by twiceYuan on 9/22/15.
 * <p/>
 * 计数器类，订阅事件总线来维护订阅数
 */
public class Counter {

    private static final Counter counter = new Counter();

    public int chatUnread = 0;
    public int newsUnread = 0;
    public int mineUnread = 0;

    private Counter() {
        EventBus.getBus().register(this);
//        Observable.create(subscriber -> {
//            // 每过一定时间让未读数都增加1
//            while (true) {
//                SystemClock.sleep((long) (20000 * Math.random()));
//                subscriber.onNext(new Object());
//            }
//        }).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(o -> {
//                    EventBus.getBus().post(new ChatReadEvent(ReadEvent.Type.ADD));
//                    EventBus.getBus().post(new NewsReadEvent(ReadEvent.Type.ADD));
//                    EventBus.getBus().post(new MineReadEvent(ReadEvent.Type.ADD));
//                });
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
        if (event.type == ReadEvent.Type.REDUCE) newsUnread--;
        if (event.type == ReadEvent.Type.ADD) newsUnread++;
    }

    @Subscribe
    public void readChat(ChatReadEvent event) {
        if (chatUnread == 0) return;
        if (event.type == ReadEvent.Type.REDUCE) chatUnread--;
        if (event.type == ReadEvent.Type.ADD) chatUnread++;
    }

    @Subscribe
    public void readMine(MineReadEvent event) {
        if (mineUnread == 0) return;
        if (event.type == ReadEvent.Type.REDUCE) mineUnread--;
        if (event.type == ReadEvent.Type.ADD) mineUnread++;
    }
}
