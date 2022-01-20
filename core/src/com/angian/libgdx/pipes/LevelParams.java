package com.angian.libgdx.pipes;

public class LevelParams {
    public float waterStartTime;
    public float waterStartSpeed; //px per second
    public int scoreFactor;
    public int distance2win;

    private LevelParams(float _waterStartTime, float _waterStartSpeed, int _scoreFactor, int _distance2win) {
        waterStartTime = _waterStartTime;
        waterStartSpeed = _waterStartSpeed;
        scoreFactor = _scoreFactor;
        distance2win = _distance2win;
    }

    public static LevelParams get(int level) {
        if (level <= 0)
            return null;

        if (level == 1)
            return new LevelParams(20, 20, 100, 5);

        if (level == 2)
            return new LevelParams(15, 30, 120, 7);

        if (level == 3)
            return new LevelParams(10, 40, 150, 9);

        return new LevelParams(5, 60, 200, 11);
    }
}
