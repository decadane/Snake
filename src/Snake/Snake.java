package Snake;

import java.util.ArrayList;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Coords> snake;
    private Direction newDir;
    private Direction trueDir;
    private int length;
    private boolean eatFlag;

    public Snake() {
        trueDir = Direction.RIGHT;
        newDir = Direction.RIGHT;
        snake = new LinkedList<Coords>();
        snake.add(new Coords(Ranges.getSize().x / 2 - 1, Ranges.getSize().y / 2));
        snake.add(new Coords(Ranges.getSize().x / 2, Ranges.getSize().y / 2));
        snake.add(new Coords(Ranges.getSize().x / 2 + 1, Ranges.getSize().y / 2));
        length = 3;
    }

    public void printSnake(Field field) {
        for (Coords coords : snake) {
            field.setBox(coords, Box.SNAKE);
        }
    }

    public boolean move(Field field) {
        Coords head = snake.get(snake.size() - 1);
        Coords tail = snake.get(0);

        trueDir = newDir;
        for (int i = 0; i < snake.size() - 1; i++) {
            snake.set(i, snake.get(i + 1).copyCoords());
        }
        switch (trueDir) {
            case TOP:
                head.y -= 1;
                break;
            case DOWN:
                head.y += 1;
                break;
            case LEFT:
                head.x -= 1;
                break;
            case RIGHT:
                head.x += 1;
                break;
        }
        if (eatFlag) {
            snake.addFirst(tail);
            eatFlag = false;
            length++;
        }
        else {
            field.setBox(tail, Box.EMPTY);
        }
        if (Ranges.inRange(head) && field.getBox(head) == Box.EAT) {
            eatFlag = true;
            field.setPlaceFood(false);
        };
        return Ranges.inRange(head) && field.getBox(head) != Box.SNAKE;
    }

    public void setDir(Direction dir) {
        newDir = dir;
    }

    public Direction getDir() {
        return trueDir;
    }

    public int getLength() {
        return length;
    }
}
