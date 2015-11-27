package org.ulco;


public class Triangle extends GraphicsObject {

    private Point m_center;
    private Point sommet1;
    private Point sommet2;
    private Point sommet3;


    public Triangle(){
        this.sommet1=new Point(0,0);
        this.sommet2=new Point(1,0);
        this.sommet3=new Point(0,1);
        this.m_center=new Point(1,1);
    }

    public Triangle(Point center, Point s1, Point s2, Point s3){
        this.sommet1=s1;
        this.sommet2=s2;
        this.sommet3=s3;
        this.m_center=center;
    }

    public Triangle(String json) {
        String str = json.replaceAll("\\s+", "");
        int centerIndex = str.indexOf("center");
        int sommet1Index = str.indexOf("sommet1");
        int sommet2Index = str.indexOf("sommet2");
        int sommet3Index = str.indexOf("sommet3");
        int endIndex = str.lastIndexOf("}");

        m_center= new Point(str.substring(centerIndex+7,sommet1Index-1));
        sommet1= new Point(str.substring(centerIndex+8,sommet2Index-1));
        sommet2= new Point(str.substring(centerIndex+8,sommet3Index-1));
        sommet3= new Point(str.substring(centerIndex+8,endIndex-1));
    }

    @Override
    public GraphicsObject copy() {
        return new Triangle(m_center.copy(),sommet1.copy(),sommet2.copy(),sommet3.copy());
    }

    @Override
    boolean isClosed(Point pt, double distance) {
        return Search.check(pt,distance,m_center);
    }

    @Override
    void move(Point delta) {
        m_center.move(delta);
    }

    @Override
    public String toJson() {
        return "{ type: triangle, center: " + m_center.toJson() + ", sommet1: " + this.sommet1 + ", sommet2: " + this.sommet2 + ", sommet3: " + this.sommet3 + " }";
    }

    @Override
    public String toString() {
        return "triangle[" + m_center.toString() + "," + sommet1 + "," + sommet2 + "," + sommet3 + "]";
    }

    @Override
    public GraphicsObjects returnElement() {
        GraphicsObjects go = new GraphicsObjects();
        go.add(this);
        return go;
    }

    @Override
    public int size() {
        return 1;
    }
}
