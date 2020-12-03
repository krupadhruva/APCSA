/*
 * Name: Krupa Dhruva
 * Date: December 2, 2020
 * Period: 7
 * Time Taken: 20 minutes
 *
 * Lab Reflection:
 * This was a fairly easy lab to do but it was beneficial for understanding the
 * concept of inheritance. I understand where and why this would come in handy
 * as this would avoid multiple implementations/duplications of the same code.
 * Overall, I enjoyed doing this lab.
 */
public class P7_Dhruva_Krupa_BackToSchool {
    public static void main(String[] args) {
        Person bob = new Person("Coach Bob", 27, "M");
        System.out.println(bob);

        Student lynne = new Student("Lynne Brooke", 16, "F", "HS95129", 3.5);
        System.out.println(lynne);

        Teacher mrJava = new Teacher("Duke Java", 34, "M", "Computer Science", 50000);
        System.out.println(mrJava);

        CollegeStudent ima = new CollegeStudent("Ima Frosh", 18, "F", "UCB123", 4.0, 1, "English");
        System.out.println(ima);
    }
}

class Person {
    private String myName; // name of the person
    private int myAge; // person's age
    private String myGender; // "M" for male, "F" for female

    // constructor
    public Person(String name, int age, String gender) {
        myName = name;
        myAge = age;
        myGender = gender;
    }

    public String getName() {
        return myName;
    }

    public int getAge() {
        return myAge;
    }

    public String getGender() {
        return myGender;
    }

    public void setName(String name) {
        myName = name;
    }

    public void setAge(int age) {
        myAge = age;
    }

    public void setGender(String gender) {
        myGender = gender;
    }

    public String toString() {
        return myName + ", age: " + myAge + ", gender: " + myGender;
    }
}

class Student extends Person {
    private String myIdNum; // Student Id Number
    private double myGPA; // grade point average

    // constructor
    public Student(String name, int age, String gender, String idNum, double gpa) {
        // use the super class' constructor
        super(name, age, gender);

        // initialize what's new to Student
        myIdNum = idNum;
        myGPA = gpa;
    }

    public String getIdNum() {
        return myIdNum;
    }

    public double getGPA() {
        return myGPA;
    }

    public void setIdNum(String idNum) {
        myIdNum = idNum;
    }

    public void setGPA(double gpa) {
        myGPA = gpa;
    }

    // overrides the toString method in the parent class
    public String toString() {
        return super.toString() + ", student id: " + myIdNum + ", gpa: " + myGPA;
    }
}

class Teacher extends Person {
    private String subject;
    private double salary;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Teacher(String name, int age, String gender, String subject, double salary) {
        super(name, age, gender);
        this.subject = subject;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format(
                "%s, subject: %s, salary: %.1f", super.toString(), getSubject(), getSalary());
    }
}

class CollegeStudent extends Student {
    private String major;
    private int year;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public CollegeStudent(
            String name, int age, String gender, String idNum, double gpa, int year, String major) {
        super(name, age, gender, idNum, gpa);
        this.year = year;
        this.major = major;
    }

    @Override
    public String toString() {
        return String.format("%s, year: %d, major: %s", super.toString(), getYear(), getMajor());
    }
}
