package com.twiceyuan.eventbus.event;

/**
 * Created by twiceYuan on 9/22/15.
 */
public abstract class ReadEvent {

    public enum Type {
        ADD,
        REDUCE
    }

    public Type type;

    public ReadEvent(Type type) {
        this.type = type;
    }

    public ReadEvent() {
        type = Type.REDUCE;
    }
}
