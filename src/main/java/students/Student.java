package students;

import java.util.List;

public class Student {
  private String name;
  private int grade;
  private List<String> courses;  // should this be a set?

  // don't "expose" constructors, they do not behave well in the
  // face of change
  private Student(String name, int grade, List<String> courses) {
    // missing validation!!
    this.name = name; // what if this is null?
    this.grade = grade; // what if grade is < 0 or > 100
    this.courses = List.copyOf(courses); // only valid courses
  }

  // "Factory" (also, builder)
  public static Student of(String name, int grade, String ... courses) {
    // check our "pool" return if already in place
    // if our student has high grade, maybe we return a VIPStudent
    // (with special privileges) rather than a regular Student
    return new Student(name, grade, List.of(courses));
  }

  public String getName() {
    return name;
  }

  public int getGrade() {
    return grade;
  }

  public List<String> getCourses() {
    return courses;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", grade=" + grade +
        ", courses=" + courses +
        '}';
  }
}
