package eng.prog.inheritance;

public class Cylinder extends Circle {
    private double h; // height

    // null constructor
    public Cylinder ()
    {
        super(); h = 0;
    }

    public Cylinder (int x, int y, double r, double h)
    {
        super(x, y, r); setHeight(h);
    }

    // get/set methods for the height
    public double getHeight ()
    {
        return h;
    }

    public void setHeight (double hval)
    {
        h = hval < 0 ? 0 : hval; // ensures that height is valid/greater than 0
    }

    @Override // overrides and uses Circle getArea
    public double getArea ()
    {
        return 2*super.getArea() + 2*Math.PI*super.getRadius()*h;
    }

//    @Override // overrides Shape getVolume
    public double getVolume ()
    {
        return super.getArea() * h;
    }

    @Override // overrides Circle getname
    public String getName ()
    {
        return "Cylinder";
    }

    @Override // overrides and uses the Circle toString implementation
    public String toString ()
    {
        return super.toString() + "; H = " + h;
    }
} // end class Cylinder