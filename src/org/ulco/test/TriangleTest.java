package org.ulco.test;


import junit.framework.TestCase;
import org.ulco.Circle;
import org.ulco.GraphicsObject;
import org.ulco.Point;
import org.ulco.Triangle;

public class TriangleTest extends TestCase {

    public void testType() throws Exception {
        Triangle s = new Triangle(new Point(0, 0),new Point(2, 0),new Point(0, 2),new Point(1, 1));

        assertTrue(s instanceof Triangle);
        assertTrue(s instanceof GraphicsObject);
    }

    public void testJson() throws Exception {
        Triangle s = new Triangle(new Point(0, 0),new Point(2, 0),new Point(0, 2),new Point(1, 1));

        assertEquals(s.toJson(), "{ type: triangle, center: { type: point, x: 0.0, y: 0.0 }, sommet1: { type: point, x: 2.0, y: 0.0 }, sommet2: { type: point, x: 0.0, y: 0.2 }, sommet3: { type: point, x: 1.0, y: 1.0 } }");
    }

    public void testCopy() throws Exception {
        Triangle s = new Triangle(new Point(0, 0),new Point(2, 0),new Point(0, 2),new Point(1, 1));
        assertEquals(s.toJson(), s.copy().toJson());
    }
}
