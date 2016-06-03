package net.troja.mica.marvin;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PathFinderTest {
    private final PlayerState source = new PlayerState();
    private final PlayerState destination = new PlayerState();
    private final PathFinder classToTest = new PathFinder();

    @Before
    public void setUp() {
    }

    @Test
    public void calulatePaths() {
        source.setPosX(3);
        source.setPosY(3);
        destination.setPosX(5);
        destination.setPosY(6);

        final char[] result = "awww".toCharArray();

        final char[] way = classToTest.calculatePath(source, destination, getField());

        assertThat(way, equalTo(result));
    }

    private char[][] getField() {
        final char[][] field = new char[8][8];
        field[0] = "########".toCharArray();
        field[1] = "#      #".toCharArray();
        field[2] = "#w######".toCharArray();
        field[3] = "# #    #".toCharArray();
        field[4] = "#   #w #".toCharArray();
        field[5] = "#ww #  #".toCharArray();
        field[6] = "#      #".toCharArray();
        field[7] = "########".toCharArray();
        return field;
    }
}
