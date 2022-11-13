package task10.console;

import task10.ArrayIO;
import task10.Triangle;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(args[0]);
        FileOutputStream outputStream = new FileOutputStream(args[1]);

        var triangles = ArrayIO.getTriangles(inputStream, ";");

        var table = Triangle.getTable(triangles);

        System.out.println(ArrayIO.saveTable(table, outputStream, true));
    }
}