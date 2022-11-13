package task10.jtable;

import task10.Triangle;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class ArrayConvertor {
    public static Vector<Vector<Double>> trianglesToRow(List<Triangle> triangles){
        Vector<Vector<Double>> rows = new Vector<>();

        for (var triangle : triangles){
            Vector<Double> row = new Vector<>();
            for (var point : triangle.vertices){
                row.add(point.x());
                row.add(point.y());
            }
            rows.add(row);
        }

        return rows;
    }

    public static String inputTableToString(DefaultTableModel model){
        StringBuilder builder = new StringBuilder();
        int count = 0;

        for (var row : model.getDataVector()){
            for (var coordinate : row){
                Double val = (Double) coordinate;
                builder.append(val);
                count++;

                if (count >= 2) {
                    builder.append("; ");
                    count = 0;
                }
                else
                    builder.append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append("\n");
        }

        return builder.toString();
    }
}
