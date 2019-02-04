/**
 * @author Cesar Andres Garcia Posada and Daniel Felipe Gómez Martínez.
 */

public class Vertex {

    private Vertex brother;
    private Color color;

    /**
     * Constructor of the classes.
     *
     * @param color
     */
    public Vertex(Color color) {
        this.color = color;
    }

    /**
     * @return color .
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color (enum type).
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return Vertex (if it have a connection).
     */
    public Vertex getBrother() {
        return brother;
    }

    /**
     * @param brother .
     */
    public void setBrother(Vertex brother) {
        this.brother = brother;
    }
}