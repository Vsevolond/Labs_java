import javax.swing.*;
import java.awt.*;


public class CanvasPanel extends JPanel {
    private int a = 60, b = 80;
    private int deltaX = getWidth() / 2 - a - b / 2;
    private int deltaY = getHeight() / 2 - b - a / 2;
    private Color[] colors = new Color[] {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.CYAN, Color.BLUE, Color.MAGENTA};
    private Rectangle rectA = new Rectangle(deltaX, b + deltaY, a, a);
    private Rectangle rectB = new Rectangle(a + deltaX, a + b + deltaY, b, b);
    int[] xPoints = new int[] {a + deltaX, 2 * a + deltaX, 2 * a + b + deltaX, a + b + deltaX};
    int[] yPoints = new int[] {b + deltaY, deltaY, a + deltaY, a + b + deltaY};
    private Polygon rectC = new Polygon(xPoints, yPoints, 4);
    private int colorA= 0, colorB = 1, colorC = 2;
    public void setA(int inA) {
        a = inA;
        repaint();
    }
    public void setB(int inB) {
        b = inB;
        repaint();
    }

    public void setColorA(int colA) {
        colorA = colA;
        repaint();
    }
    public void setColorB(int colB) {
        colorB = colB;
        repaint();
    }
    public void setColorC(int colC) {
        colorC = colC;
        repaint();
    }

    public void resetRect() {
        deltaX = getWidth() / 2 - a - b / 2;
        deltaY = getHeight() / 2 - b - a / 2;
        rectA.x = deltaX; rectA.y =  b + deltaY; rectA.width = a; rectA.height = a;
        rectB.x = a + deltaX; rectB.y = a + b + deltaY; rectB.width = b; rectB.height = b;
        int[] xPoints = new int[] {a + deltaX, 2 * a + deltaX, 2 * a + b + deltaX, a + b + deltaX};
        int[] yPoints = new int[] {b + deltaY, deltaY, a + deltaY, a + b + deltaY};
        rectC.xpoints = xPoints; rectC.ypoints = yPoints; rectC.npoints = 4;
    }
    public void click(int x, int y) {
        if (rectA.contains(x, y)) {
            int colA = (colorA + 1) % 7;
            setColorA(colA);
        } else if (rectB.contains(x, y)) {
            int colB = (colorB + 1) % 7;
            setColorB(colB);
        } else if (rectC.contains(x, y)) {
            int colC = (colorC + 1) % 7;
            setColorC(colC);
        }
    }
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        resetRect();
        g.setColor(colors[colorA]);
        g.fillRect(rectA.x, rectA.y, rectA.width, rectA.height);
        g.setColor(colors[colorB]);
        g.fillRect(rectB.x, rectB.y, rectB.width, rectB.height);
        g.setColor(colors[colorC]);
        g.fillPolygon(rectC.xpoints, rectC.ypoints, 4);
    }
}

