
import java.util.ArrayList;
import java.util.List;


/*----------------------------------------------*/
public class Student
{
    /*----------------------------------------------*/
    private String name;
    private List<Class> classes = new ArrayList<Class>();

    /*----------------------------------------------*/
    public Student() {}
    public Student(String name) { this.name = name; }

    /*----------------------------------------------*/
    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    /*----------------------------------------------*/
    public void addClass(Class cl)
    {
        for (Class c : this.classes) {
            if (c.getName().equals(cl.getName())) {
                c = cl;
                return;
            }
        }

        classes.add(cl);
    }

    /*----------------------------------------------*/
    public Class getClass(String name)
    {
        for (Class c : this.classes) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /*----------------------------------------------*/
    public ArrayList<Class> getClasses() { return (ArrayList<Class>)this.classes; }
}
