import java.lang.annotation.Retention;
import java.lang.reflect.Array;
import java.util.Objects;

/**
 * A 2D cartesian plane implemented as with an array. Each (x,y) coordinate can
 * hold a single item of type <T>.
 *
 * @param <T> The type of element held in the data structure
 */
public class ArrayCartesianPlane<T> implements CartesianPlane<T> {

    /**
     * Constructs a new ArrayCartesianPlane object with given minimum and
     * maximum bounds.
     * <p>
     * Note that these bounds are allowed to be negative.
     *
     * @param minimumX A new minimum bound for the x values of
     * elements.
     * @param maximumX A new maximum bound for the x values of
     * elements.
     * @param minimumY A new minimum bound for the y values of
     * elements.
     * @param maximumY A new maximum bound for the y values of
     * elements.
     * @throws IllegalArgumentException if the x minimum is greater
     * than the x maximum
     * (and resp. with y min/max)
     */

    //the plane need to be create
    private T[][] plane;

    //the min value of x
    private int minimumX;

    //the max value of x
    private int maximumX;

    //the min value of y
    private int minimumY;

    //the max value of y
    private int maximumY;

    public ArrayCartesianPlane(int minimumX, int maximumX, int minimumY,
                               int maximumY) throws IllegalArgumentException {
        // TODO: implement the constructor
        if ((minimumX > maximumX) || (minimumY > maximumY)) {
            throw new IllegalArgumentException("minimum is greater " +
                    "than the maximum ");
        }

        this.maximumX = maximumX;
        this.minimumX = minimumX;
        this.maximumY = maximumY;
        this.minimumY = minimumY;

        plane = (T[][]) new Object
                [maximumX - minimumX + 1][maximumY - minimumY + 1];

    }

    // TODO: you are to implement all of ArrayCartesianPlanes's methods here
    @Override
    public void add(int x, int y, T element) throws IllegalArgumentException {
        if (!outOfBounds(x, y)) {
            throw new IllegalArgumentException();
        }
        plane[x - minimumX][y - minimumY] = element;

    }

    @Override
    public T get(int x, int y) throws IndexOutOfBoundsException {
        if (!outOfBounds(x, y)) {
            throw new IndexOutOfBoundsException();
        }
        return plane[x - minimumX][y - minimumY];
    }

    @Override
    public boolean remove(int x, int y) throws IndexOutOfBoundsException {
        if (plane[x - minimumX][y - minimumY] == null) {
            return false;
        }
        plane[x - minimumX][y - minimumY] = null;
        return true;
    }

    @Override
    public void clear() {
        plane = (T[][]) new Object
                [maximumX - minimumX + 1][maximumY - minimumY + 1];
    }

    @Override
    public void resize(int newMinimumX, int newMaximumX,
                       int newMinimumY, int newMaximumY)
            throws IllegalArgumentException {
        //create a new Array
        if ((newMinimumX > newMaximumX) || (newMinimumY > newMaximumY)) {
            throw new IllegalArgumentException("minimum is greater " +
                    "than the maximum ");
        }
        T[][] newPlane = (T[][]) new Object
                [newMaximumX - newMinimumX + 1][newMaximumY - newMinimumY + 1];

        //Traverse the original array
        for (int i = 0; i <= maximumX - minimumX; i++) {
            for (int j = 0; j <= maximumY - minimumY; j++) {
                //if the positon is not null, set it in the new array
                if (plane[i][j] != null) {
                    //if the number of position in new plane
                    if ((i + minimumX >= newMinimumX) &&
                            (i + minimumX <= newMaximumX) &&
                            (j + minimumY >= newMinimumY) &&
                            (j + minimumY <= newMaximumY)) {
                        newPlane[i + minimumX - newMinimumX]
                                [j + minimumY - newMinimumY] = plane[i][j];
                    } else {
                        throw new IllegalArgumentException("an element would " +
                                "be lost after this resizing operation");
                    }
                }
            }
        }
        plane = newPlane;
        this.maximumX = newMaximumX;
        this.minimumX = newMinimumX;
        this.maximumY = newMaximumY;
        this.minimumY = newMinimumY;
    }

    private boolean outOfBounds(int x, int y) {
        return (minimumX <= x) && (x <= maximumX) && (minimumY <= y)
                && (y <= maximumY);
    }

}

