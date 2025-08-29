package iuh.fit.se;

/**
 * @description: Course with id, title, credit, and department.
 * @author: Mai The Hao
 * @version: 1.0
 * @created: 30-Aug-2025 00:35:00 AM
 */
public class Course {

    private int credit;
    private String department, id, title;

    public Course() {
    }

    public Course(String id, String title, int credit, String department) {
        setId(id);
        setTitle(title);
        setCredit(credit);
        setDepartment(department);
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        if (credit <= 0) {
            throw new IllegalArgumentException("Course credit: Credit must be greater than 0");
        }
        this.credit = credit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.length() < 3) {
            throw new IllegalArgumentException("Course id: ID must have at least 3 characters");
        }
        if (!id.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Course id: ID must contain only letters or digits");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Course title: Title must not be empty");
        }
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + ", credit=" + credit + ", department=" + department + "]";
    }
}
