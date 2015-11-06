package org.ulco;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class JSON {
    static public GraphicsObject parse(String json) {
        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));

        type= "org.ulco." + type.substring(0,1).toUpperCase() + type.substring(1);


        try{
            Class cl = Class.forName(type);
            Class[] types = new Class[]{String.class};
            Constructor ct = cl.getConstructor(types);
            Object obj2 = ct.newInstance(str);
            o = (GraphicsObject)obj2;

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return o;
    }


    static public Layer parseLayer(String json) {
        return new Layer(json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }
}
