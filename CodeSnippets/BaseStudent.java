public class BaseStudent {
    private String name;
    private int grade;

    public BaseStudent(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * Override <code>equals()</code> to perform case insensitive name comparison and the grade
     *
     * @param other Instance to compare with
     * @return <code>true</code> if equal
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof BaseStudent) {
            BaseStudent cast = (BaseStudent) other;
            return grade == cast.grade && name.compareToIgnoreCase(cast.name) == 0;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("BaseStudent: Student \"%s\", is studying in grade %d", name, grade);
    }
}
