package model;

public class Task {
    private String description;
    private String expectedOutput;

    public Task(String description, String expectedOutput){
        this.description = description;
        this.expectedOutput = expectedOutput;
    }

    // Getter und Setter

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getExpectedOutput(){
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput){
        this.expectedOutput = expectedOutput;
    }

}
