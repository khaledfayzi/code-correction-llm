package model;

public class Submission {

    private String studentCode;
    private String studentName;
    private int  taskId;


    public Submission(String studentCode, String studentName, int taskId){
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.taskId = taskId;

    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getTaskId() {
        return taskId;
    }



}