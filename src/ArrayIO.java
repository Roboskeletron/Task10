import java.io.FileInputStream;
import java.util.*;

public class ArrayIO {

    public static List<Triangle> getTriangles(FileInputStream stream, String delimiter){
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter(delimiter);

        ArrayList<Triangle> triangles = new ArrayList<>();

        while (scanner.hasNextLine()){
            triangles.add(Triangle.parse(scanner.nextLine()));
        }

        return triangles;
    }
}
