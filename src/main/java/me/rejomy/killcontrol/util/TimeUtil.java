package me.rejomy.killcontrol.util;

import me.rejomy.killcontrol.Main;

public class TimeUtil {

    private long timeInMillis;

    public TimeUtil(String time) {

        String format = time.replaceAll("[0-9]", "");

        if (format.length() != 1) {
            Main.getInstance().getLogger().warning("Format: " + time + " not supported!");
            timeInMillis = 0;
            return;
        }

        int value = Integer.parseInt(time.substring(0, time.length() - 1));

        if (format.equalsIgnoreCase("h")) {
            timeInMillis = (long) value * 60 * 60 * 1000;
        } else if (format.equalsIgnoreCase("m")) {
            timeInMillis = (long) value * 60 * 1000;
        } else if (time.equalsIgnoreCase("d")) {
            timeInMillis = (long) value * 24 * 60 * 60 * 1000;
        } else {
            timeInMillis = value * 1000L;
        }

    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

}
