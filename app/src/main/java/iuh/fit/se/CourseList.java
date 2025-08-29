package iuh.fit.se;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class CourseList {
    private int count;
    private Course[] courses;

    public CourseList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Length of the array must be greater than 0");
        }

        courses = new Course[capacity];
        count = 0;
    }

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

    public Course searchCourseById(String id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId().equalsIgnoreCase(id)) {
                return courses[i];
            }
        }
        return null;
    }

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

    public Course[] getCourses() {
        Course[] result = new Course[count];
        for (int i = 0; i < count; i++) {
            result[i] = courses[i];
        }
        return result;
    }
}
