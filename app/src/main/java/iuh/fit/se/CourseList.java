package iuh.fit.se;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Course manager.
 * 
 * @author Mai The Hao
 * @version 1.0
 * @since 30-Aug-2025
 */
public class CourseList {
    private int count;
    private Course[] courses;

    /**
     * Constructs a CourseList with the specified capacity.
     * 
     * @param capacity the maximum number of courses that can be stored
     * @throws IllegalArgumentException if capacity is less than or equal to 0
     */
    public CourseList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Length of the array must be greater than 0");
        }

        courses = new Course[capacity];
        count = 0;
    }

    /**
     * Adds a course to the list if the course ID doesn't already exist.
     * 
     * @param course the course to add
     * @return true if course was added successfully, false otherwise
     * @throws IllegalArgumentException if course is null
     */
    public boolean addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }

        if (exists(course)) {
            System.err.println("Course with id " + course.getId() + " already exists.");
            return false;
        }

        if (count >= courses.length) {
            System.err.println("Course list is full.");
            return false;
        }

        courses[count++] = course;
        return true;
    }

    /**
     * Checks if a course with the same ID already exists in the list.
     * 
     * @param course the course to check
     * @return true if course exists, false otherwise
     */
    private boolean exists(Course course) {
        if (course != null) {
            for (int i = 0; i < count; i++) {
                if (courses[i].getId().equalsIgnoreCase(course.getId())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Finds the department that has the most courses.
     * 
     * @return the department name with most courses, or null if no courses exist
     */
    public String findDepartmentWithMostCourses() {
        if (count != 0) {
            Map<String, Integer> counter = new HashMap<>();

            for (int i = 0; i < count; i++) {
                String dept = courses[i].getDepartment();
                counter.put(dept, counter.getOrDefault(dept, 0) + 1);
            }

            int maxCount = 0;
            String maxDept = null;
            for (Map.Entry<String, Integer> entry : counter.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    maxDept = entry.getKey();
                }
            }
            return maxDept;
        }

        return null;
    }

    /**
     * Finds all courses with the maximum credit hours.
     * 
     * @return array of courses with maximum credits, empty array if no courses
     *         exist
     */
    public Course[] findMaxCreditCourse() {
        int maxCredit = 0;
        Map<Integer, List<Course>> stored = new HashMap<Integer, List<Course>>();
        for (int i = 0; i < count; i++) {
            Course course = courses[i];

            maxCredit = Math.max(maxCredit, course.getCredit());

            List<Course> list = stored.get(course.getCredit());

            if (list == null) {
                list = new java.util.ArrayList<>();
                stored.put(course.getCredit(), list);
            }

            list.add(course);
        }

        List<Course> resultList = stored.get(maxCredit);
        if (resultList != null) {
            return resultList.toArray(new Course[0]);
        }

        return new Course[0];
    }

    /**
     * Removes a course from the list by ID, displays error if not found.
     * 
     * @param id the ID of the course to remove
     * @return true if course was removed, false if not found
     * @throws IllegalArgumentException if id is null or empty
     */
    public boolean removeCourse(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Course id cannot be null or empty.");
        }
        for (int i = 0; i < count; i++) {
            if (courses[i].getId().equalsIgnoreCase(id)) {
                System.arraycopy(courses, i + 1, courses, i, count - i - 1);
                courses[--count] = null;
                return true;
            }
        }
        System.err.println("Course with id " + id + " does not exist.");
        return false;
    }

    /**
     * Searches for courses by title using partial matching (case-insensitive).
     * 
     * @param title the title to search for
     * @return array of matching courses, empty array if none found
     */
    public Course[] searchCourse(String title) {
        int matchCount = 0;
        int[] matchedIdx = new int[count];

        String lowerTitle = title.toLowerCase();
        for (int i = 0; i < count; i++) {
            if (courses[i].getTitle().toLowerCase().contains(lowerTitle)) {
                matchedIdx[matchCount++] = i;
            }
        }

        Course[] result = new Course[matchCount];
        for (int i = 0; i < matchCount; i++) {
            result[i] = courses[matchedIdx[i]];
        }
        return result;
    }

    /**
     * Searches for courses by department (case-insensitive).
     * 
     * @param department the department to search for
     * @return array of matching courses, empty array if none found
     */
    public Course[] searchCourseByDepartment(String department) {
        int matchCount = 0;
        int[] matchedIdx = new int[count];
        for (int i = 0; i < count; i++) {
            if (courses[i].getDepartment().equalsIgnoreCase(department)) {
                matchedIdx[matchCount++] = i;
            }
        }

        Course[] result = new Course[matchCount];
        for (int i = 0; i < matchCount; i++) {
            result[i] = courses[matchedIdx[i]];
        }
        return result;
    }

    /**
     * Searches for a course by its ID.
     * 
     * @param id the course ID to search for
     * @return the course if found, null otherwise
     */
    public Course searchCourseById(String id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId().equalsIgnoreCase(id)) {
                return courses[i];
            }
        }
        return null;
    }

    /**
     * Returns courses sorted by title without modifying the original list.
     * 
     * @return array of courses sorted by title
     */
    public Course[] sortCoursesByTitle() {
        Course[] sorted = new Course[count];
        for (int i = 0; i < count; i++) {
            sorted[i] = courses[i];
        }

        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (sorted[j].getTitle().compareToIgnoreCase(sorted[j + 1].getTitle()) > 0) {
                    Course temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    /**
     * Returns a copy of all courses in the list.
     * 
     * @return array containing all courses
     */
    public Course[] getCourses() {
        Course[] result = new Course[count];
        for (int i = 0; i < count; i++) {
            result[i] = courses[i];
        }
        return result;
    }
}
