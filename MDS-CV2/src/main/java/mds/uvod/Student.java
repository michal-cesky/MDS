package mds.uvod;

import java.util.ArrayList;

public class Student {
    String surname;
    String name;
    int id;
    int year;

    public static ArrayList<Student> students = new ArrayList<>();

    public Student(String surname, String name, int id, int year) {
        this.surname = surname;
        this.name = name;
        this.id = id;
        this.year = year;
    }

    public int getId(){
        return id;
    }

    public String toString() {
        return String.format("Student: " + name + " " + surname + ", narozen: " + year + " má ID: " + id);
    }
    public static void addStudents() {
        students.add(new Student("Meshores", "Lev", 187396, 1996));
        students.add(new Student("Berg", "František", 211132, 1998));
        students.add(new Student("Fanta", "Tomáš", 217135, 2000));
        students.add(new Student("Vojáčková", "Veronika", 221584, 2000));
        students.add(new Student("Macho", "Radim", 221666, 1999));
        students.add(new Student("Gura", "Maxim", 222062, 2000));
        students.add(new Student("Hadwiger", "Tomáš", 230252, 2001));
        students.add(new Student("Horský", "Milan", 230258, 2001));
        students.add(new Student("Beránek", "Karel", 230533, 2000));
        students.add(new Student("Cristovao", "Cristovao Matias Pedro", 230539, 1999));
        students.add(new Student("Hnátek", "Michal", 230555, 2001));
        students.add(new Student("Hrabálek", "Matěj", 230559, 2000));
        students.add(new Student("Ioani", "Leida", 230569, 2001));
        students.add(new Student("Ostrý", "Pavel", 230624, 2001));
        students.add(new Student("Seč", "Filip", 230656, 2000));
        students.add(new Student("Smetana", "Martin", 230668, 2000));
        students.add(new Student("Český", "Michal", 230789, 2000));
        students.add(new Student("Žernovič", "Michal", 230923, 2000));
        students.add(new Student("Hostášek", "Josef", 231050, 1999));
        students.add(new Student("Bielik", "Oliver", 231229, 2000));
        students.add(new Student("Drdulová", "Ester", 231233, 2001));
        students.add(new Student("Takács", "Peter", 231290, 2001));
        students.add(new Student("Vidlařová", "Pavla", 231300, 2001));
        students.add(new Student("Kohout", "David", 195823, 1996));
        students.add(new Student("Číka", "Petr", 10, 1982));
        students.add(new Student("Masaryk", "Tomáš", 123456, 1850));
    }

}
