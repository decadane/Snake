import Snake.*;
import Snake.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends JFrame {

    private JPanel panel;
    private Field field;
    private Snake snake;

    private final int ROWS = 20;
    private final int COLS = 20;
    private final int IMAGE_SIZE = 20;

    public static void main(String[] args) {
        new SnakeGame();
    }

    SnakeGame() {
        start();
        setImages();
        initPanel();
        initFrame();
        game();
    }

    private void initFrame() {
         setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         setTitle("Snake");
         setIconImage(getImage("icon"));
         setVisible(true);
         setResizable(false);
         pack();
         setLocationRelativeTo(null);
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
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w':
                        snake.setDir(Direction.TOP);
                    case 's':
                        snake.setDir(Direction.DOWN);
                    case 'd':
                        snake.setDir(Direction.RIGHT);
                    case 'a':
                        snake.setDir(Direction.LEFT);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
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
        while (true) {
            snake.move(field);
            snake.printSnake(field);
            panel.repaint();
            try {
                Thread.sleep(300);
            }
            catch (InterruptedException t) {
                t = null;
            }
        }
    }
}
