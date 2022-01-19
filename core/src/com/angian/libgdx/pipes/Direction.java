package com.angian.libgdx.pipes;


public enum Direction {
    W,
    N,
    E,
    S;

    public Direction flip() {
        switch (this) {
            case W:
                return E;
            case N:
                return S;
            case E:
                return W;
            case S:
                return N;
        }

        return null;
    }
}