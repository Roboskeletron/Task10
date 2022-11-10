public class Vector {
    public final double x, y;

    public Vector(Point start, Point end){
        x = start.x() - end.x();
        y = start.y() - end.y();
    }

    public double length(){
        return Math.sqrt(x*x + y*y);
    }

    public static double getAngle(Vector a, Vector b){
        double multi = a.x * b.x + a.y * b.y;
        double cos = multi / a.length() / b.length();

        return Math.acos(cos);
    }
}
