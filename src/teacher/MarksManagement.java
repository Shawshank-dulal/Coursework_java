package teacher;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import student.EnrollStudent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MarksManagement {

    CSVReader file;
    String filename;
    MarksManagement(){

        filename="/home/sashank/Downloads/coursework/src/teacher/marks.csv";
    }

    public List<Marks> getAllMarks() throws IOException{
        setCsvReader();
        List<Marks> marksList=new ArrayList<>();
        String[] nextLine= file.readNext();
        while ((nextLine=file.readNext()) != null){
            if(nextLine.length>1) {
                Marks mark=new Marks();
                mark.setStudent_Id(nextLine[0]);
                mark.setCourse_Id(nextLine[1]);
                mark.setMarks(nextLine[2]);
                marksList.add(mark);
            }
        }
        return  marksList;
    }

    public void setMarks(String studentId, String courseId, String grade) throws IOException { //here
        Marks mark =new Marks();
        mark.setMarks(grade);
        mark.setStudent_Id(studentId);
        mark.setCourse_Id(courseId);
        setCsvReader();
        List<Marks> marksList=getAllMarks();
        marksList.add(mark);
        saveToCsv(marksList);
    }
    public String getMarks(String studentId, String courseID){ //and here
    return " ";
    }
    public void saveToCsv(List<Marks> marksList){
        setCsvReader();
        try {
            CSVWriter writer=new CSVWriter(new FileWriter(new File(filename)));
            writer.writeNext(new String[]{"student_Id", "course_Id", "Marks"});
            for (Marks mark:marksList) {
                writer.writeNext(new String[]{
                        mark.getStudent_Id(),
                        mark.getCourse_Id(),
                        mark.getMarks()
                });

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setCsvReader(){
        try {
            file=new CSVReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
