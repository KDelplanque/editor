package org.ulco;

/**
 * Created by kdelplan on 24/11/15.
 */
public class Select {

    public static GraphicsObjects select(Point pt, double distance,Layer l) {
        GraphicsObjects list = new GraphicsObjects();

        for (GraphicsObject object : l.getM_list()) {
            if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }
        return list;
    }

    public static GraphicsObjects select(Point pt, double distance, Document d) {
        GraphicsObjects list = new GraphicsObjects();

        for (Layer layer : d.getM_layers()) {
            list.addAll(layer.select(pt, distance));
        }
        return list;
    }


}
