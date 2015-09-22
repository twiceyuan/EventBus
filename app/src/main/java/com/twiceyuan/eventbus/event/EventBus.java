package com.twiceyuan.eventbus.event;

import com.squareup.otto.Bus;

/**
 * Created by twiceYuan on 9/22/15.
 */
public class EventBus {

    private static final Bus bus = new Bus();

    public static Bus getBus() {
        return bus;
    }

    private EventBus() {}
}
