import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

class Line extends baseObj {
    private Point start;
    private Point end;

    Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    void translate(int dx, int dy) {
        System.out.println("dx:" + dx + " dy:" + dy);
        System.out.println("start.x:" + start.x + " start.y:" + start.y);
        System.out.println("end.x:" + end.x + " end.y:" + end.y);
        this.start.x += dx;
        this.start.y += dy;
        this.end.x += dx;
        this.end.y += dy;
    }

    baseObj copy() {
        // serialize the object and save to clip board
        return new Line(new Point(), new Point());
    }

    @Override
    void draw(Graphics g) {
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    public double calPerpendicularDistance(Point start, Point end, Point p) {
        // Line vector
        double lineVecX = end.x - start.x;
        double lineVecY = end.y - start.y;

        // Point vector from start to p
        double pointVecX = p.x - start.x;
        double pointVecY = p.y - start.y;

        // Length of the line vector
        double lineLength = Math.sqrt(lineVecX * lineVecX + lineVecY * lineVecY);

        // Cross product in 2D
        double crossProduct = (lineVecX * pointVecY) - (lineVecY * pointVecX);

        // Perpendicular distance from point p to the line
        double distance = Math.abs(crossProduct) / lineLength;

        return distance;
    }

    @Override
    boolean contains(Point p) {
        // calculate the perpendicular distance
        double distance = calPerpendicularDistance(start, end, p);

        // Define a small epsilon for floating point comparison
        double epsilon = 5.0;

        // Check if the area is very close to zero
        return distance < epsilon;
    }

    @Override
    void gradient(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gradientPaint = new GradientPaint(
                start.x, start.y, Color.CYAN,
                end.x, end.y, Color.MAGENTA);
        g2.setPaint(gradientPaint);
        g2.setStroke(new BasicStroke(2));
        g.drawLine(start.x, start.y, end.x, end.y);
    }
}
