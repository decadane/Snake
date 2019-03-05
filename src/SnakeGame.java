import Snake.*;
import Snake.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends JFrame {

    private JPanel panel;
    private JLabel label;
    private Field field;
    private Snake snake;
    private GameState gs;

    private final int ROWS = 20;
    private final int COLS = 20;
    private final int IMAGE_SIZE = 20;

    public static void main(String[] args) {
        new SnakeGame();
    }

    SnakeGame() {
        start();
        setImages();
        gs = GameState.GAME;
        initLabel();
        initPanel();
        initFrame();
        game();
    }

    private void initFrame() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w':
                        if (snake.getDir() != Direction.DOWN) snake.setDir(Direction.TOP);
                        break;
                    case 's':
                        if (snake.getDir() != Direction.TOP) snake.setDir(Direction.DOWN);
                        break;
                    case 'd':
                        if (snake.getDir() != Direction.LEFT) snake.setDir(Direction.RIGHT);
                        break;
                    case 'a':
                        if (snake.getDir() != Direction.RIGHT) snake.setDir(Direction.LEFT);
                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
         setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         setTitle("Snake");
         setIconImage(getImage("icon"));
         setVisible(true);
         setResizable(false);
         pack();
         setLocationRelativeTo(null);
    }

    private void initLabel() {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coords coords : Ranges.getAllCoords()) {
                    g.drawImage((Image)field.getBox(coords).image, coords.x * IMAGE_SIZE,
                            coords.y * IMAGE_SIZE, this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name());
        }
    }

    private Image getImage(String name) {
        String fullName = name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fullName));
        return icon.getImage();
    }

    private void start() {
        Ranges.setSize(new Coords(ROWS, COLS));
        field = new Field(ROWS, COLS);
        snake = new Snake();
    }

    private void game() {
        while (gs == GameState.GAME) {
            label.setText(setMessage());
            field.createFood();
            if (!snake.move(field)) {
                gs = GameState.END;
                break;
            }
            snake.printSnake(field);
            panel.repaint();
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException t) {
                t = null;
            }
        }
        label.setText(setMessage());
    }

    private String setMessage() {
        switch (gs) {
            case GAME: return("Your score: " + (snake.getLength() - 3));
            case END: return ("The game is end! Your score: " + (snake.getLength() - 3));
        }
        return null;
    }
}
