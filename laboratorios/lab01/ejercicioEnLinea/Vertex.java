public class Vertex {

    private Vertex brother;
    private Color color;

    public Vertex(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vertex getBrother() {
        return brother;
    }

    public void setBrother(Vertex brother) {
        this.brother = brother;
    }
}