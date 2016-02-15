package com.danisagan.dwarfgame.utils;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by daniel on 2/13/16.
 */
public class Clock {
    private long startTime = TimeUtils.millis();
    private long lastTime = startTime;

    public float reset() {
        long now = TimeUtils.millis();
        float elapsedSeconds = TimeUtils.timeSinceMillis(lastTime) / 1000.f;
        lastTime = now;
        return elapsedSeconds;
    }

    public long getElapsedTimeMillis() {
        return TimeUtils.timeSinceMillis(lastTime);
    }

    public float getElapsedTime() {
        return (float)(getElapsedTimeMillis()) / 1000.f;
    }
}
