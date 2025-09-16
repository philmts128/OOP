
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


/*----------------------------------------------*/
public class Menu
{
    /*----------------------------------------------*/
    private static final Scanner SCANNER = new Scanner(System.in);
    private Database database = new Database();

    /*----------------------------------------------*/
    public void run()
    {
        while (true) {
            this.mainMenu();
        }
    }

    /*----------------------------------------------*/
    private void mainMenu()
    {
        this.clearScreen();

        System.out.println("| Escolha uma opcao abaixo: \n");
        System.out.println("1. Inserir aluno");
        System.out.println("2. Inserir disciplina e notas");
        System.out.println("3. Visualizar todos os alunos");
        System.out.println("4. Visualizar disciplinas de aluno");
        System.out.println("5. Visualizar notas e media");
        System.out.println("6. Sair");

        int opt = 0;
        try {
            System.out.print("\n> ");
            opt = Integer.parseInt(SCANNER.nextLine());
        } catch (Exception e) {
            System.out.print(">> Entrada invalida!");
            this.waitKey();
            this.mainMenu();
        }

        switch (opt)
        {
            case 1: this.addStudent();   break;
            case 2: this.addClass();     break;
            case 3: this.viewStudents(); break;
            case 4: this.viewClasses();  break;
            case 5: this.viewGrades();   break;
            case 6: System.exit(0);
            default: error(">> Opcao invalida!\n"); break;
        }
    }

    /*----------------------------------------------*/
    private void addStudent()
    {
        while (true)
        {
            this.clearScreen();

            System.out.print("| digite o nome completo do estudante: ");
            String name = SCANNER.nextLine();

            var std = new Student(name);

            if (database.studentExists(std)) {
                System.out.print(">> Estudante ja existe!");
                waitKey();
                continue;
            }

            database.addStudent(std);

            System.out.printf("| %s adicionado com sucesso!", std.getName());
            waitKey();
            break;
        }
    }

    /*----------------------------------------------*/
    private void viewStudents()
    {
        this.clearScreen();
        System.out.println("-- Todos os Alunos --\n");
        int count = 0;
        for (String name : this.database.getStudentNames()) {
            System.out.printf("%02d - %s\n", count++, name);
        }
        this.waitKey();
    }

    /*----------------------------------------------*/
    private void viewClasses()
    {
        Student std = null;

        while (true)
        {
            this.clearScreen();

            System.out.print("| digite o nome do estudante: ");
            String name = SCANNER.nextLine();

            if (!this.database.studentExists(name)) {
                System.out.println(">> estudante nao esta cadastrado!");
                waitKey();
                continue;
            }

            std = database.getStudent(name);
            break;
        }

        System.out.print("\n");
        for (Class c : std.getClasses()) {
            System.out.printf("- %s\n", c.getName());
        }

        this.waitKey();
    }

    /*----------------------------------------------*/
    private void viewGrades()
    {
        Student std = null;

        while (true)
        {
            this.clearScreen();

            System.out.print("| digite o nome do estudante: ");
            String name = SCANNER.nextLine();

            if (!this.database.studentExists(name)) {
                System.out.println(">> estudante nao esta cadastrado!");
                waitKey();
                continue;
            } else {
                std = database.getStudent(name);
                break;
            }
        }

        Class cl = null;
        while (true)
        {
            System.out.print("| digite o nome da disciplina: ");
            String name = SCANNER.nextLine();

            if (!this.database.classExists(std.getName(), name)) {
                System.out.println(">> disciplina nao esta cadastrada!");
                waitKey();
                continue;
            } else {
                cl = std.getClass(name);
                break;
            }
        }

        double sum = 0;
        System.out.println("\nNotas:");
        for (int i = 0; i < cl.getNumGrades(); ++i) {
            double g = cl.getGrade(i);
            sum += g;
            System.out.printf("Nota %d: %.2f\n", (i+1), g);
        }

        double mean = sum / (double)cl.getNumGrades();
        System.out.printf("Media: %.2f", mean);
        this.waitKey();
    }

    /*----------------------------------------------*/
    private void addClass()
    {
        Student std = null;

        while (true)
        {
            this.clearScreen();

            System.out.print("| digite o nome do estudante: ");
            String name = SCANNER.nextLine();

            if (!this.database.studentExists(name)) {
                System.out.println(">> estudante nao esta cadastrado!");
                waitKey();
                continue;
            }

            std = database.getStudent(name);
            break;
        }

        System.out.print("| digite o nome da materia: ");
        String className = SCANNER.nextLine();

        Class cl = std.getClass(std.getName());
        if (std == null) {
            System.out.print(">> materia ja existe. Notas serao atualizadas!\n");
        } else {
            cl = new Class(className);
        }

        ArrayList<Double> grades = null;
        while (true)
        {
            try {
                grades = this.readGrades();
            } catch (Exception e) {
                System.out.printf("%s\n", e);
                waitKey();
                continue;
            }

            break;
        }

        cl.insertGrades(grades);
        std.addClass(cl);
        database.addClass(std.getName(), cl);

        System.out.print(">> As notas foram inseridas!\n");
        waitKey();
    }

    /*----------------------------------------------*/
    private ArrayList<Double> readGrades() throws Exception
    {
        var grades = new ArrayList<Double>();

        try
        {
            System.out.print("| digite as notas separadas por espaco: ");
            String str = SCANNER.nextLine();

            for (String g : str.split("\\s+")) {
                grades.add(Double.parseDouble(g));
            }
        }
        catch (Exception e)
        {
            throw new Exception(">> entrada invalida!");
        }

        return grades;
    }

    /*----------------------------------------------*/
    private void error(String msg)
    {
        System.out.println(msg);
    }

    /*----------------------------------------------*/
    private void waitKey()
    {
        SCANNER.nextLine();
    }

    /*----------------------------------------------*/
    private void clearScreen()
    {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
