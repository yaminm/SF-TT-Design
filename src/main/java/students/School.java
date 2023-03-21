package students;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class School {
  public static void showAll(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
  }

//  private static int threshold = 65;
  // maybe not public?
  // potentially any caller could change this
  // change is persistent...
  // and not threadsafe (maybe we care)
//  public static void setSmartThreshold(int threshold) {
//    // validate caller's authority
//    School.threshold = threshold;
//  }
//
  // have we broken a fundamental guideline?
  // what might change?
//  public static void showSmart(List<Student> ls) {
//    for (Student s : ls) {
//      if (s.getGrade() > threshold) {
//        System.out.println("> " + s);
//      }
//    }
//  }

  // Predicate is essentially
//  interface Predicate<E> {
//    boolean test(E e);
//  }

//  public static List<Student> showSmart(
//      List<Student> ls, int threshold) {
//  public static List<Student> getInteresting(
//      List<Student> ls, Predicate<Student> ps) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (ps.test(s)) {
//        res.add(s);
//      }
//    }
//    return res;
//  }

  // now generic... almost exactly "filter"
  // function argument types are a constraint on the CALLER -- bad
  // return type is a constraint on the IMPLEMENTATION -- perhaps good
  // this "pass an argument because of the behavior it contains"
  // is essentially the "Command" pattern. In FP it's simply
  // a "higher order function"
  // if we STORE the behavior then we have the "Strategy" pattern
  // If you want to have VARIABLE BEHAVIOR, you might use "first class
  // functions", or perhaps "pointer to function", but if you're "pure OO"
  // the a pointer/reference to an object is in fact a pointer to
  // the state AND THE BEHAVIOR that object contains
  public static <E> List<E> getInteresting(
      Iterable<E> ls, Predicate<E> ps) {
    List<E> res = new ArrayList<>();
    for (E s : ls) {
      if (ps.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

  public static void main(String[] args) {
//    Student s1 = new Student("Fred", 78, "Maths", "Physics");
//    Student s2 = new Student("Fred", 78, "Maths", "Physics");
    Student s1 = Student.of("Fred", 78, "Maths", "Physics");
//    Student s2 = Student.get("Fred", 78, "Maths", "Physics");

    List<Student> roster = List.of(
        s1,
        Student.of("Jim", 56, "Journalism"),
        Student.of("Sheila", 93,
            "Math", "Physics", "Quantum Mechanics", "Astrophysics")
    );
    // print all the students
    showAll(roster);

    // print all smart students
    System.out.println("smart------------");
//    showAll(showSmart(roster));

    // if we provide a "get threshold" method, that returns
    // a special type "Threshold" NOT an int, and make the
    // access to values of that type restricted (i.e.
    // private constructors) then we can have caller NOT
    // able to choose bad values, but also more of a "pure function"
//    showAll(showSmart(roster, 65));
    showAll(getInteresting(roster, s -> s.getGrade() > 65));

    // change threshold of smartness
//    School.setSmartThreshold(80); // assume this happens elsewhere...

    // caller is not responsible for choosing "smart"
//    showAll(showSmart(roster));

    // with parameter form, caller is a) responsible
    // and b) has power over, choosing the threshold
//    showAll(showSmart(roster, 80));
    showAll(getInteresting(roster, s -> s.getGrade() > 80));

    // "unenthusiastic students"
    showAll(getInteresting(roster, s -> s.getCourses().size() < 3));

  }
}
