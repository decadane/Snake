package Snake;

public class Coords {
    public int x;
    public int y;

    public Coords(int x, int y) {
        setCoords(x, y);
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coords copyCoords() {
        return new Coords(x, y);
    }
}
