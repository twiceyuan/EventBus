package com.twiceyuan.eventbus.event;

public class MineReadEvent extends ReadEvent {
    public MineReadEvent(Type add) {
        super(add);
    }

    public MineReadEvent() {
        super();
    }
}
