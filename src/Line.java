import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

class Line extends baseObj {
    private Line2D line;

    Line(Line2D l) {
        line = l;
    }

    @Override
    void translate(double dx, double dy) {
        line = new Line2D.Double(
                line.getX1() + dx,
                line.getY1() + dy,
                line.getX2() + dx,
                line.getY2() + dy);

    }

    baseObj copy() {
        // serialize the object and save to clip board
        Line2D temp = new Line2D.Double(new Point(), new Point());
        return new Line(temp);
    }

    @Override
    void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getColor());
        g2.draw(line);
        g2.fill(line);
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
        // Define a small epsilon for floating point comparison
        double epsilon = 5.0;

        // Check if the area is very close to zero
        return line.ptSegDist(p) <= epsilon;
    }

    @Override
    void gradient(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle bounds = line.getBounds();
        GradientPaint gradientPaint = new GradientPaint(bounds.x, bounds.y, Color.CYAN, bounds.x + bounds.width,
                bounds.y + bounds.height, Color.MAGENTA);
        g2.setPaint(gradientPaint);
        g2.setStroke(new BasicStroke(2));
        g2.draw(line);
    }
}
