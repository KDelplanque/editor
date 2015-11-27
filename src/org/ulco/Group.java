package org.ulco;

import java.util.Iterator;
import java.util.Vector;

public class Group extends GraphicsObject{

    private Vector<GraphicsObject> m_objectList;
    private int m_ID;


    public Group() {

        m_objectList = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().newId();

    }

    public Group(String json) {

        m_objectList = new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        Search.parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2),m_objectList);
        Search.parseObjects(str.substring(groupsIndex + 8, endIndex - 1),m_objectList);
    }

    public void add(Object object) {

        m_objectList.add((GraphicsObject) object);

    }


    public Group copy() {
        Group g = new Group();

        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);

            g.add(element.copy());
        }

        return g;
    }

    public int getID() {
        return m_ID;
    }

    @Override
    boolean isClosed(Point pt, double distance) {

        if(m_objectList.size()==0) return false;

        for (GraphicsObject object :  m_objectList) {
            if(!(object.isClosed(pt,distance))){
                return false;
            }
        }
        return true;
    }


    public void move(Point delta) {
        Group g = new Group();

        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);

            element.move(delta);
        }

    }

    public GraphicsObjects returnElement(){
        GraphicsObjects go = new GraphicsObjects();
        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);
            go.addAll(element.returnElement());
        }
        return go;
    }


    public int size() {
        int size = 0;
        for (int i = 0; i < m_objectList.size(); ++i) {
            size+=m_objectList.elementAt(i).size();
        }
        return size;
    }

     public String toJson() {
         String str = "{ type: group, objects : { ";

         for (int i = 0; i < m_objectList.size(); ++i) {
             GraphicsObject element = m_objectList.elementAt(i);
                if(!(element instanceof Group)) {
                    str += element.toJson();
                    if (i < m_objectList.size() - 1) {
                        str += ", ";
                    }
                }
         }
         str += " }, groups : { ";

         for (int i = 0; i < m_objectList.size(); ++i) {
             GraphicsObject element = m_objectList.elementAt(i);
                if(element instanceof Group)
             str += element.toJson();
         }
         return str + " } }";
     }


    public String toString() {

        String str = "group[[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if(!(element instanceof Group)) {
                if (!str.equals("group[[")) {
                    str += ", ";
                }
                str += element.toString();

            }
        }

        str += "],[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if(element instanceof Group)
                str += element.toString();
        }
        return str + "]]";


    }


}
