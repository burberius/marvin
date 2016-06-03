package net.troja.mica.marvin;

import java.util.Iterator;

public class Location {
    private final int x;
    private final int y;
    private Location from;
    private int distance;

    public Location(int x, int y, Location from) {
        this.x = x;
        this.y = y;

        if (from != null) {
            this.from = from;
            distance = from.distance + 1;
        }
    }

    public Location getFrom() {
        return from;
    }

    public Location(int x, int y) {
        this(x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return distance;
    }

    public Iterator<Location> neighbors() {
        return new LocationIterator();
    }

    private class LocationIterator implements Iterator<Location> {
        private final int xOffsets[] = { -1, 1, 0, 0 };
        private final int yOffsets[] = { 0, 0, -1, 1 };
        private int next = 0;

        public boolean hasNext() {
            return next < xOffsets.length;
        }

        public Location next() {
            final Location nextLocation = new Location(x + xOffsets[next], y + yOffsets[next], Location.this);
            next++;
            return nextLocation;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
