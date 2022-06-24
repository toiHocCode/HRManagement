package be;

import be.Cadres;
import be.CadresManagement;
import be.CadresType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Utils {

    public static String readFileContent(String path) {
        String csvFile = path;
        List<Cadres> list = readCadresFromCSV(path);
        try (Reader reader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                System.out.println("Cadres [name= " + csvRecord.get(0)
                        + ", id= " + csvRecord.get(1)
                        + " , dob=" + csvRecord.get(2)
                        + ", type= " + csvRecord.get(3)
                        + ", papers= " + csvRecord.get(4)
                        + ", teach= " + csvRecord.get(5)
                        + ", serve= " + csvRecord.get(6)
                        + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Cadres> readCadresFromCSV(String path) {
        List<Cadres> list = new ArrayList<>();
        try (Reader reader = new FileReader(path);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                System.out.println("Cadres [name= " + csvRecord.get(0)
                        + ", id= " + csvRecord.get(1)
                        + " , dob=" + csvRecord.get(2)
                        + ", type= " + csvRecord.get(3)
                        + ", papers= " + csvRecord.get(4)
                        + ", teach= " + csvRecord.get(5)
                        + ", serve= " + csvRecord.get(6)
                        + "]");
                CadresManagement cadresManagement = CadresManagement.getInstance();
                String name = csvRecord.get(0);
                String id = csvRecord.get(1);
                String dob = csvRecord.get(2);
                String type = csvRecord.get(3);
                String papers = csvRecord.get(4);
                String teach = csvRecord.get(5);
                String serve = csvRecord.get(6);
                Cadres cadres = cadresManagement.createCadres(name, id, dob, type, papers, teach, serve);
                list.add(cadres);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(list);
    }

    public static String chooseDirectoryOfNewFile(String extension) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("File Chooser");

        String fileType = extension.replace(".", "");
        String desc = String.format("%s files (*%s)", fileType, extension);
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(desc, "*" + extension));

        Stage stage = new Stage();
        File saveFile = fileChooser.showSaveDialog(stage);

        if (saveFile == null) {
            return null;
        }

        String path = saveFile.getAbsolutePath();

        if (!path.endsWith(extension)) {
            path += extension;
        }

        return path;
    }

    public static void writeContentToFile(String content, String filePath) {
        try {
            new File(filePath).getParentFile().mkdirs();
            PrintWriter out = new PrintWriter(filePath);
            out.println(content);
            out.close();
        } catch (FileNotFoundException e) {
//            Logger logger = new Logger("abc", "xyz");
//            logger.error("Cant write content to " + filePath + " because " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveContentToNewFile(String content) {
        String filePath = chooseDirectoryOfNewFile(".csv");
        if (filePath != null) writeContentToFile(content, filePath);
    }

//    public static void main(String[] args) {
//        Utilities.readFileContent("D:\\Code\\TeacherManagementSystem\\src\\main\\resources\\data.csv");
//    }
}
