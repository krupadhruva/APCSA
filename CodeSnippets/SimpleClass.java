/**
 * Implement a simple class explaining members variables/attributes/fields and member
 * methods/functions
 *
 * <p>The members can be static, allowing class level access only OR non-static, allowing access
 * from objects (instance of a class)
 */
public class SimpleClass {
    /**
     * Attributes/member variables/fields defined in a class store the state
     *
     * <p>State is just multiple data that is part of a class which defines the property.
     *
     * <p>Example: Let us model a Student class
     */
    static class StudentInformation {
        private String name;
        private int grade;

        @Override
        public String toString() {
            return String.format("Student \"%s\", is studying in grade %d", name, grade);
        }
    }

    /**
     * A more complete Student class with a method or member method/function
     *
     * <p>This class has both state and methods that does some computing based on the state
     *
     * <p>Note: The importance of member function/method:
     *
     * <p>- When you invoke a method, it operates of state that is part of the object
     *
     * <p>- Invoking method on a different object (instance) will not affect the state of a
     * completely different instance
     *
     * <p>- The scope of any change is an object, hence Java is Object Oriented Programming language
     */
    static class Student {
        private static final int MAX_NAME_LENGTH = 10;

        private String name;
        private int grade;

        public boolean isStudentNameLong() {
            return (name.length() > MAX_NAME_LENGTH);
        }

        /**
         * Converts the name to CAPITAL and will do it only of the member variable that is part of
         * the object on which the method is invoked
         *
         * <p>Note: This is a non-static method and hence can be invoked ONLY on an object instance
         */
        public void Capitalize() {
            name = name.toUpperCase();
        }

        /**
         * Since MAX_NAME_LENGTH is a static member, it's value is same across all object instances.
         * Java does not even make a copy of this member/field when new objects are instantiated.
         *
         * <p>Similarly, static methods are again accessed using the class and NOT object Therefore,
         * a static method can ONLY access static members. In our example, <code>getMaxNameLength()
         * </code> can only access <code>MAX_NAME_LENGTH</code> and NOT <code>name</code> and <code>
         * grade</code> fields (since they are not static and need an object)
         *
         * @return Maximum name length
         */
        public static int getMaxNameLength() {
            return MAX_NAME_LENGTH;
        }

        @Override
        public String toString() {
            return String.format(
                    "Student \"%s[long=%s]\", is studying in grade %d",
                    name, isStudentNameLong(), grade);
        }
    }

    public static void main(String[] args) {
        {
            System.out.println("Basic student information:");

            StudentInformation student_1 = new StudentInformation();

            // NOTE: You can access 'private' member variables here since the class
            // Student is at the same level (inside class SimpleClass) as this function
            student_1.name = "krupa";
            student_1.grade = 11;

            StudentInformation student_2 = new StudentInformation();
            student_2.name = "jack_with_long_name";
            student_2.grade = 10;

            /*
             * student_1 and student_2 are 2 instances of the same blueprint 'Student'
             * They have same fields 'name' and 'age'. However, there are not the same.
             * The values stored in those fields are different. Hence, student_1 and
             * student_2 have different states.
             *
             * Even if both 'student_1' and 'student_2' had the same values in their
             * fields, they still 2 independent instances of same blueprint 'Student' class
             * When you modify the value of an attribute in 'student_1', it will NOT
             * modify the value in 'student_2'.
             */

            System.out.printf("\tStudent instance 1: %s%n", student_1);
            System.out.printf("\tStudent instance 2: %s%n", student_2);
        }

        {
            System.out.println("Student details:");

            Student student_1 = new Student();
            student_1.name = "krupa";
            student_1.grade = 11;

            Student student_2 = new Student();
            student_2.name = "jack_with_long_name";
            student_2.grade = 10;

            System.out.printf("\tStudent instance 1: %s%n", student_1);
            System.out.printf("\tStudent instance 2: %s%n", student_2);

            System.out.println("Capitalize student 1:");
            student_1.Capitalize();

            System.out.printf("\tStudent instance 1: %s%n", student_1);
            System.out.printf("\tStudent instance 2: %s%n", student_2);

            System.out.println("Invoke static method on class:");
            System.out.printf("\tMaximum name length: %d%n", Student.getMaxNameLength());
        }
    }
}
