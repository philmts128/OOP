
import java.util.ArrayList;
import java.util.List;


public class Database
{
    /*----------------------------------------------*/
    private List<Student> students = new ArrayList<Student>();

    /*----------------------------------------------*/
    public Database(){}

    /*----------------------------------------------*/
    public void addStudent(Student std)
    {
        students.add(std);
    }

    /*----------------------------------------------*/
    public Student getStudent(String name)
    {
        for (Student s : this.students) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    /*----------------------------------------------*/
    public ArrayList<String> getStudentNames()
    {
        var names = new ArrayList<String>();
        for (Student s : this.students)
            names.add(s.getName());
        return names;
    }

    /*----------------------------------------------*/
    public boolean studentExists(Student std)
    {
        for (Student s : this.students) {
            if (s.getName().equals(std.getName())) {
                return true;
            }
        }
        return false;
    }

    /*----------------------------------------------*/
    public boolean studentExists(String std)
    {
        for (Student s : this.students) {
            if (s.getName().equals(std)) {
                return true;
            }
        }
        return false;
    }

    /*----------------------------------------------*/
    public void addClass(String studentName, Class cl)
    {
        for (Student s : students) {
            if (studentName.equals(s.getName())) {
                s.addClass(cl);
            }
        }
    }

    /*----------------------------------------------*/
    public boolean classExists(String std, String className)
    {
        for (Student s : students) {
            if (s.getName().equals(std)) {
                for (Class cl : s.getClasses()) {
                    if (cl.getName().equals(className)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
