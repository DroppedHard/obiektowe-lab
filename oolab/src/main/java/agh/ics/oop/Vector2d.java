package agh.ics.oop;

class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    Vector2d upperRight(Vector2d other) {
        if (this.precedes(other)) {
            return other;
        } else if (this.follows(other)) {
            return this;
        }
        Vector2d pkt1 = new Vector2d(this.x, other.y);
        Vector2d pkt2 = new Vector2d(other.x, this.y);
        if (pkt1.precedes(pkt2)) {
            return pkt2;
        } else {
            return pkt1;
        }
    }

    Vector2d lowerLeft(Vector2d other) {
        if (this.follows(other)) {
            return other;
        } else if (this.precedes(other)) {
            return this;
        }
        Vector2d pkt1 = new Vector2d(this.x, other.y);
        Vector2d pkt2 = new Vector2d(other.x, this.y);
        if (pkt1.follows(pkt2)) {
            return pkt2;
        } else {
            return pkt1;
        }
    }

    Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    Vector2d substract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d that))
            return false;
        return this.x == that.x && this.y == that.y;
    }

    Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }
}
