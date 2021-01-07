import java.util.ArrayList;
import java.util.Collection;

/**
 * Freshman is allowed to ONLY enroll in 2 electives unless the student is 'special'
 *
 * <p>We specialize HighSchoolStudent in this class and add those additional constraints and any
 * extra functionality for a freshman
 */
public class FreshmanStudent extends HighSchoolStudent {
    private static final int MAX_ELECTIVES = 2;

    private final boolean special;

    public FreshmanStudent(String name, int grade, boolean special) {
        super(name, grade);
        this.special = special;
    }

    @Override
    public int getMaximumElectives() {
        return special ? super.getMaximumElectives() : MAX_ELECTIVES;
    }

    /**
     * Implements <code>enroll()</code> from base class with additional constraints and provision to
     * support elective limit and 'special' students
     *
     * @param elective Elective to enroll
     * @return <code>true</code> on success
     */
    @Override
    public boolean enroll(String elective) {
        if (special || getElectives().size() < getMaximumElectives()) {
            super.enroll(elective);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("FreshmanStudent: %s and special=%s", super.toString(), special);
    }

    public static void main(String[] args) {
        BaseStudent elem_student = new BaseStudent("elem", 1);
        HighSchoolStudent high_student = new HighSchoolStudent("high", 10);
        FreshmanStudent regular_freshman = new FreshmanStudent("regular_fresh", 9, false);
        FreshmanStudent special_freshman = new FreshmanStudent("special_fresh", 9, true);

        System.out.println("Freshman student information:");
        System.out.printf(
                "\t%s can take %d electives%n",
                regular_freshman, regular_freshman.getMaximumElectives());
        System.out.printf(
                "\t%s can take %d electives%n",
                special_freshman, special_freshman.getMaximumElectives());

        // Instances of derived class 'FreshmanStudent' can be used where instances of base class
        // 'BaseStudent' are expected.
        // 'students' is a collection of all students in a school. We should be able to add students
        // of any type as long as they derive from base class 'BaseStudent'
        Collection<BaseStudent> students = new ArrayList<>();

        // Adding elem_student of type 'BaseStudent'
        students.add(elem_student);

        // Adding elem_student of type 'HighSchoolStudent'
        students.add(high_student);

        // Adding regular_freshman of type 'FreshmanStudent'
        students.add(regular_freshman);

        // Adding special_freshman of type 'FreshmanStudent'
        students.add(special_freshman);

        System.out.println("All students information:");
        for (var student : students) {
            System.out.printf("\t%s%n", student);
        }
    }
}
