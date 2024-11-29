//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class ReaderClass {
//
//    public ReaderClass(String year, String id, String cgpa, String major, String grade){
//        String fileName = "StudentFile.txt";
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
//            writer.write(year);
//            writer.newLine();
//            writer.write(id);
//            writer.newLine();
//            writer.write(cgpa);
//            writer.newLine();
//            writer.write(major);
//            writer.newLine();
//            writer.write(grade);
//
//            System.out.println("Data appended to file successfully");
//        } catch (IOException e) {
//            System.err.println("An error occurred: " + e.getMessage());
//        }
//    }
//}