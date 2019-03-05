package Snake;

import java.util.Random;

public class Field {

    private Box[][] field;
    private Random rand = new Random();
    private boolean placeFood;

    public Field(int rows, int cols) {
        field = new Box[rows][cols];
        for (Coords coords : Ranges.getAllCoords()) {
            setBox(coords, Box.EMPTY);
        }
    }

    public Box getBox(Coords coords) {
        return field[coords.x][coords.y];
    }

    public void setBox(Coords coords, Box box) {
        field[coords.x][coords.y] = box;
    }

    public void createFood() {
        if (!placeFood) {
            while (true) {
                Coords coords = new Coords(rand.nextInt(Ranges.getSize().x), rand.nextInt(Ranges.getSize().y));
                if (getBox(coords) == Box.EMPTY) {
                    setBox(coords, Box.EAT);
                    placeFood = true;
                    break;
                }
            }
        }
    }

    public void setPlaceFood(boolean b) {
        placeFood = b;
    }

    public boolean getPlaceFood() {
        return placeFood;
    }
}
