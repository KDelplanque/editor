package org.ulco;

import java.util.Vector;

public class Layer {
    public Layer() {
        m_list = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().newId();
    }

    public Layer(String json) {
        m_list= new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int endIndex = str.lastIndexOf("}");

        Search.parseObjects(str.substring(objectsIndex + 9, endIndex - 1),m_list);
    }

    public void add(GraphicsObject o) {
        m_list.add(o);
    }

    public GraphicsObject get(int index) {
        return m_list.elementAt(index);
    }

    public int getObjectNumber() {
        int size = 0;
        for (int i = 0; i < m_list.size(); ++i) {

                size +=  m_list.elementAt(i).size();

        }
        return size;
    }

    public int getID() {
        return m_ID;
    }



    public GraphicsObjects select(Point pt, double distance) {
        return Select.select(pt,distance,this);
    }

    public String toJson() {
        String str = "{ type: layer, objects : { ";

        for (int i = 0; i < m_list.size(); ++i) {
            GraphicsObject element = m_list.elementAt(i);

            str += element.toJson();
            if (i < m_list.size() - 1) {
                str += ", ";
            }
        }
        return str + " } }";
    }

    public Vector<GraphicsObject> getM_list(){
        return m_list;
    }

    private Vector<GraphicsObject> m_list;
    private int m_ID;
}
