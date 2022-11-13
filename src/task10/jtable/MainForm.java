package task10.jtable;

import task10.ArrayIO;
import task10.Triangle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Vector;

public class MainForm extends  JFrame {
    private JTable inputTable;
    private JPanel jpanel;
    private JButton addPointButton;
    private JButton deletePointButton;
    private JButton openFileButton;
    private JButton saveFileButton;
    private JButton closeFileButton;
    private JButton getResultButton;
    private JButton saveResultButton;
    private JLabel outputLabel;

    private List<List<Triangle>> result = null;
    private final Vector<String> columnIdentifiers = new Vector<>(List.of(new String[]{"x", "y", "x", "y", "x", "y"}));

    public MainForm(){
        setContentPane(jpanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        addPointButton.addActionListener(this::addPointButtonClicked);
        deletePointButton.addActionListener(this::deletePointButtonClicked);
        openFileButton.addActionListener(this::openFileButtonClicked);
        saveFileButton.addActionListener(this::saveFileButtonClicked);
        closeFileButton.addActionListener(this::closeFileButtonClicked);
        getResultButton.addActionListener(this::getResultButtonClicked);
        saveResultButton.addActionListener(this::saveResultButtonClicked);
    }

    private void saveResultButtonClicked(ActionEvent actionEvent) {
        try {
            var stream = getFileStream(FileOutputStream.class);

            ArrayIO.saveTable(result, stream, true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getResultButtonClicked(ActionEvent actionEvent) {
        var inputModel = (DefaultTableModel) inputTable.getModel();

        var data = ArrayConvertor.inputTableToString(inputModel);

        try {
            var triangles  = ArrayIO.getTriangles(
                    new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)), ";");
            result = Triangle.getTable(triangles);
            var table = ArrayIO.saveTable(result, null, false);

            table = "<html>" + table.replaceAll("\n", "<br>") + "</html>";

            outputLabel.setText(table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeFileButtonClicked(ActionEvent actionEvent) {
        var model = (DefaultTableModel) inputTable.getModel();

        model.getDataVector().clear();

        model.fireTableDataChanged();
    }

    private void saveFileButtonClicked(ActionEvent actionEvent) {
        try{
            var stream = getFileStream(FileOutputStream.class);

            var data  = ArrayConvertor.inputTableToString((DefaultTableModel) inputTable.getModel());

            stream.write(data.getBytes(StandardCharsets.UTF_8));

            stream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openFileButtonClicked(ActionEvent actionEvent) {
        try {
            var stream = getFileStream(FileInputStream.class);
            var triangles = ArrayIO.getTriangles(stream, ";");

            var model = (DefaultTableModel) inputTable.getModel();

            model.setDataVector(ArrayConvertor.trianglesToRow(triangles), columnIdentifiers);
            model.fireTableDataChanged();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void deletePointButtonClicked(ActionEvent actionEvent) {
        var model = (DefaultTableModel) inputTable.getModel();

        model.removeRow(model.getRowCount() - 1);

        model.fireTableDataChanged();
    }

    private void addPointButtonClicked(ActionEvent actionEvent) {
        Vector<Double> points = new Vector<>(9);
        Vector<Vector<Double>> rows = new Vector<>();
        rows.add(points);

        var model = (DefaultTableModel) inputTable.getModel();

        if (model.getDataVector().size() > 0)
            model.addRow(points);
        else {
            model.setDataVector(rows, columnIdentifiers);
        }

        model.fireTableDataChanged();
    }

    private <T> T getFileStream(Class<T> type) throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser("C:\\Users\\wwwrt\\source\\repos\\Task10");
        int dialogResult = JFileChooser.ERROR_OPTION;
        T stream;

        if (type == FileInputStream.class){
            dialogResult = fileChooser.showOpenDialog(this);

        } else if (type == FileOutputStream.class) {
            dialogResult = fileChooser.showSaveDialog(this);
        }

        if (dialogResult != JFileChooser.APPROVE_OPTION)
            return null;

        var file = fileChooser.getSelectedFile();

        if (type == FileInputStream.class)
            stream = (T) new FileInputStream(file);
        else stream = (T) new FileOutputStream(file);

        return stream;
    }
}
