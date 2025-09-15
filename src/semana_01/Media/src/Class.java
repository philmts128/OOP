
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;


/*----------------------------------------------*/
public class Class
{
    /*----------------------------------------------*/
    private String name;
    private List<Double> grades = new ArrayList<Double>();

    /*----------------------------------------------*/
    public Class() {}

    /*----------------------------------------------*/
    public Class(String name) { this.name = name; }

    /*----------------------------------------------*/
    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    /*----------------------------------------------*/
    public void insertGrade(double gr)
    {
        grades.add(gr);
        Collections.sort(grades, Collections.reverseOrder());
    }

    /*----------------------------------------------*/
    public void insertGrades(ArrayList<Double> gr)
    {
        this.grades = gr;
        Collections.sort(grades, Collections.reverseOrder());
    }

    /*----------------------------------------------*/
    public int getNumGrades()     { return grades.size(); }
    public double getGrade(int i) { return grades.get(i); }
}
