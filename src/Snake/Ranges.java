package Snake;

import java.util.ArrayList;

public class Ranges {

    private static Coords size;
    private static ArrayList<Coords> allBoxes;

    public static void setSize(Coords _size) {
        size = _size;
        allBoxes = new ArrayList<Coords>();
        for (int y = 0; y < _size.y; y++) {
            for (int x = 0; x < _size.x; x++) {
                allBoxes.add(new Coords(x, y));
            }
        }
    }

    public static ArrayList<Coords> getAllCoords() {
        return allBoxes;
    }

    public static Coords getSize() {
        return size;
    }

    public static boolean inRange(Coords coords) {
        return coords.x >= 0 && coords.x < size.x && coords.y >= 0 && coords.y < size.y;
    }
}
