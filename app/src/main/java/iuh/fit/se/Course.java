package iuh.fit.se;

/**
 * Course with id, title, credit, and department.
 * 
 * @author Mai The Hao
 * @version 1.0
 * @since 30-Aug-2025
 */
public class Course {

    private int credit;
    private String department, id, title;

    /**
     * Default constructor.
     */
    public Course() {
    }

    /**
     * Constructs a Course with specified id, title, credit, and department.
     * 
     * @param id         course id
     * @param title      course title
     * @param credit     course credit
     * @param department course department
     */
    public Course(String id, String title, int credit, String department) {
        setId(id);
        setTitle(title);
        setCredit(credit);
        setDepartment(department);
    }

    /**
     * Gets course credit.
     * 
     * @return credit
     */
    public int getCredit() {
        return credit;
    }

    /**
     * Sets course credit.
     * 
     * @param credit course credit
     * @throws IllegalArgumentException if credit &lt;= 0
     */
    public void setCredit(int credit) {
        if (credit <= 0) {
            throw new IllegalArgumentException("Course credit: Credit must be greater than 0");
        }
        this.credit = credit;
    }

    /**
     * Gets course department.
     * 
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets course department.
     * 
     * @param department course department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Gets course id.
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets course id.
     * 
     * @param id course id
     * @throws IllegalArgumentException if id is invalid
     */
    public void setId(String id) {
        if (id == null || id.length() < 3) {
            throw new IllegalArgumentException("Course id: ID must have at least 3 characters");
        }
        if (!id.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Course id: ID must contain only letters or digits");
        }
        this.id = id;
    }

    /**
     * Gets course title.
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets course title.
     * 
     * @param title course title
     * @throws IllegalArgumentException if title is empty
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Course title: Title must not be empty");
        }
        this.title = title;
    }

    /**
     * Returns string representation of course.
     */
    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + ", credit=" + credit + ", department=" + department + "]";
    }
}
