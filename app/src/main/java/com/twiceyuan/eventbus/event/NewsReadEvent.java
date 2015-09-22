package com.twiceyuan.eventbus.event;

public class NewsReadEvent extends ReadEvent {
    public NewsReadEvent(Type add) {
        super(add);
    }

    public NewsReadEvent() {
        super();
    }
}
