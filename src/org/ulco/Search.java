package org.ulco;

import java.lang.reflect.Method;
import java.util.Vector;
import java.util.function.Function;

public class Search {

    public static int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    public static void parseObjects(String objectsStr,Vector<GraphicsObject> list) {
        while (!objectsStr.isEmpty()) {

            int separatorIndex = Search.searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }


            list.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

    public static boolean check(Point pt, double distance, Point m_center){
        return Math.sqrt((m_center.getX() - pt.getX()) * (m_center.getX() - pt.getX()) +
                ((m_center.getY() - pt.getY()) * (m_center.getY() - pt.getY()))) <= distance;
    }
}
