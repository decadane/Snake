package Snake;

import java.util.ArrayList;

public class Snake {
    private ArrayList<Coords> snake;
    public Direction dir;

    public Snake() {
        dir = Direction.RIGHT;
        snake = new ArrayList<Coords>();
        snake.add(new Coords(Ranges.getSize().x / 2 - 1, Ranges.getSize().y / 2));
        snake.add(new Coords(Ranges.getSize().x / 2, Ranges.getSize().y / 2));
        snake.add(new Coords(Ranges.getSize().x / 2 + 1, Ranges.getSize().y / 2));
    }

    public void printSnake(Field field) {
        for (Coords coords : snake) {
            field.setBox(coords, Box.SNAKE);
        }
    }

    public void move(Field field) {
        field.setBox(snake.get(0), Box.EMPTY);
        for (Coords coords : snake) {
            switch (dir) {
                case TOP:
                    coords.y -= 1;
                case DOWN:
                    coords.y += 1;
                case LEFT:
                    coords.x -= 1;
                case RIGHT:
                    coords.x += 1;
            }
        }
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}
