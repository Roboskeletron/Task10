package task10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ArrayIO {

    public static List<Triangle> getTriangles(FileInputStream stream, String delimiter) throws IOException {
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter(delimiter);

        ArrayList<Triangle> triangles = new ArrayList<>();

        while (scanner.hasNextLine()){
            triangles.add(Triangle.parse(scanner.nextLine()));
        }

        stream.close();

        return triangles;
    }

    public static String saveTable(List<List<Triangle>> table, FileOutputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();

        for (var row : table){
            for (var triangle : row){
                builder.append(triangle.toString()).append(" ");
            }
            builder.append("\n");
        }

        String text = builder.toString();
        stream.write(text.getBytes());

        stream.close();

        return text;
    }
}
