import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Implements a high school student by extending the BaseStudent. High school students have
 * electives and this is a feature on top of common students (which can include elementary or middle
 * school students too)
 *
 * <p>Since we have not overloaded <code>toString()</code>, printing objects of HighSchoolStudent
 * should show as 'BaseStudent'. This is because, the base class implementation of <code>
 * toString()</code> will get invoked.
 */
public class HighSchoolStudent extends BaseStudent {
    private final Collection<String> electives;

    public HighSchoolStudent(String name, int grade) {
        super(name, grade);
        electives = new ArrayList<>();
    }

    public boolean isEnrolled(String elective) {
        return electives.contains(elective);
    }

    public boolean enroll(String elective) {
        if (!isEnrolled(elective)) {
            electives.add(elective);
        }

        return true;
    }

    /**
     * Get the maximun number of permitted electives
     *
     * <p>Derived classes with constraints on number of electives should override this method
     *
     * @return The total number of electives allowed
     */
    public int getMaximumElectives() {
        return Integer.MAX_VALUE;
    }

    /**
     * Get student's electives
     *
     * <p>We do NOT want to return the member variable 'electives' since the caller gets a reference
     * to the member and can empty it OR modify it
     *
     * @return Unmodifiable copy of electives
     */
    public Collection<String> getElectives() {
        return Collections.unmodifiableCollection(electives);
    }
}
