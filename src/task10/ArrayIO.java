package task10;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayIO {

    public static List<Triangle> getTriangles(InputStream stream, String delimiter) throws IOException {
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter(delimiter);

        ArrayList<Triangle> triangles = new ArrayList<>();

        while (scanner.hasNextLine()){
            triangles.add(Triangle.parse(scanner.nextLine()));
        }

        stream.close();

        return triangles;
    }

    public static String saveTable(List<List<Triangle>> table, OutputStream stream, boolean saveFile) throws IOException {
        StringBuilder builder = new StringBuilder();

        for (var row : table){
            for (var triangle : row){
                builder.append(triangle.toString()).append(" ");
            }
            builder.append("\n");
        }

        String text = builder.toString();
        if (saveFile) {
            stream.write(text.getBytes());

            stream.close();
        }

        return text;
    }
}
