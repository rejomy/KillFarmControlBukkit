package me.rejomy.killcontrol.util;

public class Timer {

    public long lastMS = System.currentTimeMillis();

    public boolean hasTimeElapsed(long time) {
        return System.currentTimeMillis() - lastMS < time;
    }

    public void reset() {
        lastMS = System.currentTimeMillis();
    }

}
