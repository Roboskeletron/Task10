package task10;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Triangle {

    public final Point[] vertices;
    public final double[] angles;

    public boolean isInGroup = false;

    public Triangle(Point[] vertices){
        if (vertices.length != 3)
            throw new InvalidParameterException("Triangle must have 3 vertices");

        this.vertices = vertices;

        angles = calculateAngles();
    }

    private double[] calculateAngles() {
        Vector a = new Vector(vertices[0], vertices[1]);
        Vector b = new Vector(vertices[1], vertices[2]);
        Vector c = new Vector(vertices[2], vertices[0]);
        
        double[] angs = new double[3];
        
        angs[0] = Vector.getAngle(a, b);
        angs[1] = Vector.getAngle(b, c);
        angs[2] = Vector.getAngle(c, a);
        return angs;
    }

    public boolean isSimilar(Triangle triangle){
        double e = 10e-5;

        for (int i = 0; i < angles.length; i++){
            if (Math.abs(angles[i] - triangle.angles[i]) > e){
                return false;
            }
        }

        return true;
    }

    public static List<List<Triangle>> getTable(List<Triangle> triangles){
        List<List<Triangle>> list = new ArrayList<>();

        for (int i = 0; i < triangles.size(); i++){
            var triangle1 = triangles.get(i);

            if (triangle1.isInGroup)
                continue;

            ArrayList<Triangle> similarTriangles = new ArrayList<>();
            similarTriangles.add(triangle1);

            for (int j = i + 1; j < triangles.size(); j++){
                var triangle2 = triangles.get(j);

                if (triangle2.isInGroup || !triangle1.isSimilar(triangle2))
                    continue;

                similarTriangles.add(triangle2);
                triangle2.isInGroup = true;
            }

            list.add(similarTriangles);
        }
        return list;
    }

    public static Triangle parse(String text) {
        List<Point> points = new ArrayList<>();

        for (var point_string : text.split(";")){
            points.add(Point.parse(point_string));
        }

        Point[] trianglePoints = new Point[points.size()];

        for (int i = 0; i < points.size(); i++){
            trianglePoints[i] = points.get(i);
        }
        return  new Triangle(trianglePoints);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Triangle [ ");
        for (Point point : vertices){
            builder.append("(");
            builder.append(point);
            builder.append(") ");
        }

        builder.append("] ");

        return builder.toString();
    }
}
