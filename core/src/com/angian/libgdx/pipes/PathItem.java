package com.angian.libgdx.pipes;

import com.badlogic.gdx.math.GridPoint2;

public class PathItem {
    public GridPoint2 pos;
    public Direction outDir;

    public PathItem(GridPoint2 p, Direction d) {
        pos = p;
        outDir = d;
    }

    public String toString() {
        return "(" + pos.x + ", " + pos.y + ") going " + outDir;
    }
}
