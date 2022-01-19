package com.angian.libgdx.pipes;


import java.util.Random;

public enum PipeType {
    HORIZONTAL,
    VERTICAL,
    CROSS,
    BEND_LEFT_UP,
    BEND_UP_RIGHT,
    BEND_RIGHT_DOWN,
    BEND_DOWN_LEFT,

    SOURCE_LEFT,
    SOURCE_UP,
    SOURCE_RIGHT,
    SOURCE_DOWN;


    private static final Random rnd = new Random();

    public boolean isPlayable() {
        return (
                this == HORIZONTAL ||
                        this == VERTICAL ||
                        this == CROSS ||
                        this == BEND_LEFT_UP ||
                        this == BEND_UP_RIGHT ||
                        this == BEND_RIGHT_DOWN ||
                        this == BEND_DOWN_LEFT
        );
    }

    public boolean isSource() {
        return (
                this == SOURCE_LEFT ||
                        this == SOURCE_UP ||
                        this == SOURCE_RIGHT ||
                        this == SOURCE_DOWN
        );
    }

    public static PipeType randomPlayableType() {
        PipeType[] types = {
                PipeType.HORIZONTAL,
                PipeType.VERTICAL,
                PipeType.CROSS,
                PipeType.BEND_LEFT_UP,
                PipeType.BEND_UP_RIGHT,
                PipeType.BEND_RIGHT_DOWN,
                PipeType.BEND_DOWN_LEFT,
        };

        int iChoice = rnd.nextInt(types.length);
        return types[iChoice];
    }

    public static PipeType randomSourceType() {
        PipeType[] types = {
                PipeType.SOURCE_LEFT,
                PipeType.SOURCE_UP,
                PipeType.SOURCE_RIGHT,
                PipeType.SOURCE_DOWN,
        };

        int iChoice = rnd.nextInt(types.length);
        return types[iChoice];
    }
}