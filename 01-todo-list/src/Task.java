import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {
    private String title;
    private String project;
    private String dueDate;
    public LocalDate date;
    private boolean isCompleted;
    private static final long serialVersionUID = 1L;

    Task(String title, String project, LocalDate dueDate){
        this.setTitle(title);
        this.setProject(project);
        this.setDueDate(dueDate);
        this.isCompleted = false;
        this.date = dueDate;
    }

    public void setTitle(String title) throws NullPointerException {
        if (title.trim().isEmpty()) {
            throw new NullPointerException("Title cannot be empty!");
        }
        this.title = title.trim();
    }

    public String getTitle(){
        return this.title;
    }

    public void setProject(String project){
        this.project = project.trim();
    }

    public String getProject(){
        return this.project;
    }

    public void setDueDate(LocalDate dueDate) throws DateTimeException, NullPointerException {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new DateTimeException("Past date not allowed");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dueDate = dueDate.format(formatter);
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public boolean status() {
        return this.isCompleted;
    }

    public void setStatus(boolean status) {
        this.isCompleted = status;
    }
}
