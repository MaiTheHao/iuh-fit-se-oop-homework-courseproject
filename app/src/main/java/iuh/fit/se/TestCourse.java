package iuh.fit.se;

import java.util.Scanner;

public class TestCourse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseList courseList = new CourseList(100);
        initData(courseList);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add course");
            System.out.println("2. Remove course");
            System.out.println("3. Search course by ID");
            System.out.println("4. Search course by title");
            System.out.println("5. Search course by department");
            System.out.println("6. Sort courses by title");
            System.out.println("7. Find courses with the most credits");
            System.out.println("8. Find department with most courses");
            System.out.println("9. Display course list");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter course ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter course title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter credits: ");
                    int credit = Integer.parseInt(sc.nextLine());
                    Course c = new Course(id, title, credit, dept);
                    courseList.addCourse(c);
                    break;
                case 2:
                    System.out.print("Enter course ID to remove: ");
                    String delId = sc.nextLine();
                    courseList.removeCourse(delId);
                    break;
                case 3:
                    System.out.print("Enter course ID to search: ");
                    String findId = sc.nextLine();
                    Course found = courseList.searchCourseById(findId);
                    if (found != null) {
                        printHeader();
                        printCourse(found);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter course title to search: ");
                    String findTitle = sc.nextLine();
                    Course[] foundByTitle = courseList.searchCourse(findTitle);
                    if (foundByTitle != null && foundByTitle.length > 0) {
                        printHeader();
                        for (Course course : foundByTitle) {
                            printCourse(course);
                        }
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter department to search: ");
                    String findDept = sc.nextLine();
                    Course[] foundByDept = courseList.searchCourseByDepartment(findDept);
                    if (foundByDept != null && foundByDept.length > 0) {
                        printHeader();
                        for (Course course : foundByDept) {
                            printCourse(course);
                        }
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 6:
                    Course[] sorted = courseList.sortCoursesByTitle();
                    printHeader();
                    for (Course course : sorted) {
                        printCourse(course);
                    }
                    break;
                case 7:
                    Course[] maxCreditCourses = courseList.findMaxCreditCourse();
                    if (maxCreditCourses != null && maxCreditCourses.length > 0) {
                        printHeader();
                        for (Course course : maxCreditCourses) {
                            printCourse(course);
                        }
                    } else {
                        System.out.println("No courses found.");
                    }
                    break;
                case 8:
                    String maxDept = courseList.findDepartmentWithMostCourses();
                    if (maxDept != null) {
                        System.out.println("Department with most courses: " + maxDept);
                    } else {
                        System.out.println("No data available.");
                    }
                    break;
                case 9:
                    Course[] allCourses = courseList.getCourses();
                    if (allCourses.length > 0) {
                        printHeader();
                        for (Course course : allCourses) {
                            printCourse(course);
                        }
                    } else {
                        System.out.println("Course list is empty.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void printHeader() {
        System.out.printf("%-10s %-30s %-20s %-10s\n", "Course ID", "Title", "Department", "Credits");
        System.out.println("--------------------------------------------------------------------------");
    }

    private static void printCourse(Course c) {
        System.out.printf("%-10s %-30s %-20s %-10d\n", c.getId(), c.getTitle(), c.getDepartment(), c.getCredit());
    }

    private static void initData(CourseList courseList) {
        try {
            courseList.addCourse(new Course("CS101", "Introduction to Computer Science", 3, "Computer Science"));
            courseList.addCourse(new Course("CS102", "Data Structures and Algorithms", 4, "Computer Science"));
            courseList.addCourse(new Course("CS201", "Object-Oriented Programming", 3, "Computer Science"));
            courseList.addCourse(new Course("CS301", "Database Systems", 3, "Computer Science"));
            courseList.addCourse(new Course("MATH101", "Calculus I", 4, "Mathematics"));
            courseList.addCourse(new Course("MATH102", "Calculus II", 4, "Mathematics"));
            courseList.addCourse(new Course("MATH201", "Linear Algebra", 3, "Mathematics"));
            courseList.addCourse(new Course("PHYS101", "General Physics I", 4, "Physics"));
            courseList.addCourse(new Course("PHYS102", "General Physics II", 4, "Physics"));
            courseList.addCourse(new Course("ENG101", "English Composition", 3, "English"));
            courseList.addCourse(new Course("ENG201", "Literature Survey", 3, "English"));
            courseList.addCourse(new Course("HIST101", "World History", 3, "History"));
        } catch (Exception e) {
            System.err.println("Error initializing data: " + e.getMessage());
        }
    }

}
