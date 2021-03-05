package eng.prog.inheritance;

public class Point implements Shape
{
    private int x, y;
    public Point ()
    {
        x = 0;
        y = 0;
    }

    public Point (int xval, int yval)
    {
        x = xval;
        y = yval;
    }

    public double getArea ()
    {
        return 0;
    }

    public double getVolume ()
    {
        return 0;
    }

    // getter and setters
    public int getX ()
    {
        return x;
    }

    public int getY ()
    {
        return y;
    }

    public void setX (int xval)
    {
        x = xval;
    }

    public void setY (int yval)
    {
        y = yval;
    }

    public String getName ()
    {
        return "Point";
    }

    @Override // overrides the Object method toString (Shape inherits Object)
    public String toString ()
    {
        return "[" + x + ", " + y + "]";
    }
} // end class Point
