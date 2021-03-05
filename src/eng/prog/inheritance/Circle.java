package eng.prog.inheritance;

public class Circle extends Point {
    private double r; // its radius

    // null constructor
    public Circle ()
    {
        super();
        r = 0;
    }

    public Circle (int x, int y, double r)
    {
        super(x, y);
        setRadius(r);
    }

    // get and set methods for the radius
    public double getRadius ()
    {
        return r;
    }

    public void setRadius (double rval)
    {
        r = rval < 0 ? 0 : rval; // ensures that radius is valid/greater than 0
    }

    @Override // overrides Shape getArea
    public double getArea ()
    {
        return Math.PI * r * r;
    }

    @Override // overrides Point getname
    public String getName ()
    {
        return "Circle";
    }

    @Override // overrides and uses the Point toString implementation
    public String toString ()
    {
        return "C = " + super.toString() + "; R = " + r;
    }
} // end class Circle
