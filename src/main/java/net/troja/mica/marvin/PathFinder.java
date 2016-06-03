package net.troja.mica.marvin;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PathFinder {
    // http://www.mtholyoke.edu/~blerner/cs201/Examples/Lecture25/QueueMazeSolver.java

    public char[] calculatePath(PlayerState source, PlayerState destination, char[][] field) {
        final StringBuilder path = new StringBuilder();
        final Queue<Location> squaresToExplore = new LinkedList<Location>();
        final Set<String> visited = new HashSet<String>();
        final int maxY = field.length;
        final int maxX = field[0].length;

        // Begin exploration at the starting square
        squaresToExplore.add(new Location(source.getPosX(), source.getPosY()));

        // While there are squares we have found but not yet explored
        while (!squaresToExplore.isEmpty()) {
            // Get the next square in the queue to explore
            final Location nextSquare = squaresToExplore.remove();

            visited.add(getLocationString(nextSquare));
            // Get all the neighbors of this square
            final Iterator<Location> neighbors = nextSquare.neighbors();
            while (neighbors.hasNext()) {
                final Location adjacentSquare = neighbors.next();
                final int nextX = adjacentSquare.getX();
                final int nextY = adjacentSquare.getY();

                // Make sure the next neighbor is a square that we have not yet
                // explored.
                if ((nextX >= 0) && (nextY >= 0) && (nextX < maxX) && (nextY < maxY)) {
                    if (!isWall(field, nextX, nextY) && !visited.contains(getLocationString(adjacentSquare))) {

                        // Reached the destination
                        if ((nextX == destination.getPosX()) && (nextY == destination.getPosY())) {
                            System.out.println("Path found");

                            // Color this square as part of the path
                            maze.onPath(nextX, nextY);

                            // Color the path
                            highlightPath(adjacentSquare);
                            return true;
                        }

                        // We didn't reach the destination, so add this neighbor
                        // to
                        // the queue to explore later
                        squaresToExplore.add(adjacentSquare);
                    }
                }

            }
        }
        System.out.println("No path");

        return path.toString().toCharArray();
    }

    private boolean isWall(char[][] field, int nextX, int nextY) {
        final char pos = field[nextY][nextX];
        return (pos == '#') || (pos == 'w');
    }

    private String getLocationString(final Location nextSquare) {
        return nextSquare.getY() + "-" + nextSquare.getX();
    }
}
