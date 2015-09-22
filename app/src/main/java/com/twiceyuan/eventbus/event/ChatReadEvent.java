package com.twiceyuan.eventbus.event;

public class ChatReadEvent extends ReadEvent {
    public ChatReadEvent() {
        super();
    }
    public ChatReadEvent(Type add) {
        super(add);
    }
}
