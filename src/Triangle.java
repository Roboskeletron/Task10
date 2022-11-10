import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
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

        return null;
    }

    public boolean isSimilar(Triangle triangle){
        return Arrays.equals(angles, triangle.angles);
    }

    public static List<List<Triangle>> getTable(List<Triangle> triangles){
        List<List<Triangle>> list = new ArrayList<>();

        for (int i = 0; i < triangles.size(); i++){
            var triangle1 = triangles.get(i);

            if (triangle1.isInGroup)
                continue;

            ArrayList<Triangle> similarTriangles = new ArrayList<>();

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

        return  new Triangle((Point[]) points.toArray());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Point point : vertices){
            builder.append(point);
            builder.append(";");
        }

        return builder.toString();
    }
}
