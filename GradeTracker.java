import java.util.ArrayList;

public class GradeTracker {
    private final ArrayList<Student> studentList; 

    public GradeTracker() {
        this.studentList = new ArrayList<>();
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public int getStudentCount() {
        return studentList.size();
    }

    public ArrayList<Student> getStudents() {
        return studentList;
    }

    public double calculateAverage() {
        if (studentList.isEmpty()) {
            return 0.0;
        }
        double totalSum = 0;
        for (Student s : studentList) {
            totalSum += s.getGrade();
        }
        return totalSum / studentList.size();
    }

    public double getHighestGrade() {
        if (studentList.isEmpty()) {
            return 0.0;
        }
        double highest = studentList.get(0).getGrade();
        for (Student s : studentList) {
            if (s.getGrade() > highest) {
                highest = s.getGrade();
            }
        }
        return highest;
    }

    public double getLowestGrade() {
        if (studentList.isEmpty()) {
            return 0.0;
        }
        double lowest = studentList.get(0).getGrade();
        for (Student s : studentList) {
            if (s.getGrade() < lowest) {
                lowest = s.getGrade();
            }
        }
        return lowest;
    }
}