import java.util.*;

class Student {
    String name;
    List<University> preferences;
    University partner;

    Student(String name) {
        this.name = name;
        this.preferences = new ArrayList<>();
        this.partner = null;
    }

    void addPreference(University university) {
        preferences.add(university);
    }

    boolean prefers(University university) {
        return preferences.indexOf(university) < preferences.indexOf(partner);
    }

    @Override
    public String toString() {
        return name;
    }
}

class University {
    String name;
    List<Student> preferences;
    Student partner;

    University(String name) {
        this.name = name;
        this.preferences = new ArrayList<>();
        this.partner = null;
    }

    void addPreference(Student student) {
        preferences.add(student);
    }

    boolean prefers(Student student) {
        return preferences.indexOf(student) < preferences.indexOf(partner);
    }

    @Override
    public String toString() {
        return name;
    }
}

class GaleShapley {
    public static Map<Student, University> stableMarriage(List<Student> students, List<University> universities) {
        Map<Student, University> matches = new HashMap<>();
        Queue<Student> freeStudents = new LinkedList<>(students);

        while (!freeStudents.isEmpty()) {
            Student student = freeStudents.poll();
            for (University university : student.preferences) {
                if (university.partner == null) {
                    matches.put(student, university);
                    university.partner = student;
                    break;
                } else if (university.prefers(student)) {
                    freeStudents.add(university.partner);
                    matches.remove(university.partner);
                    matches.put(student, university);
                    university.partner = student;
                    break;
                }
            }
        }

        return matches;
    }
}

public class UniversityAdmissionsExample {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        List<University> universities = new ArrayList<>();

        Student s1 = new Student("S1");
        Student s2 = new Student("S2");
        Student s3 = new Student("S3");

        University u1 = new University("U1");
        University u2 = new University("U2");
        University u3 = new University("U3");

        students.add(s1);
        students.add(s2);
        students.add(s3);

        universities.add(u1);
        universities.add(u2);
        universities.add(u3);

        s1.addPreference(u1);
        s1.addPreference(u2);
        s1.addPreference(u3);

        s2.addPreference(u2);
        s2.addPreference(u1);
        s2.addPreference(u3);

        s3.addPreference(u1);
        s3.addPreference(u2);
        s3.addPreference(u3);

        u1.addPreference(s1);
        u1.addPreference(s2);
        u1.addPreference(s3);

        u2.addPreference(s2);
        u2.addPreference(s1);
        u2.addPreference(s3);

        u3.addPreference(s1);
        u3.addPreference(s2);
        u3.addPreference(s3);

        Map<Student, University> matches = GaleShapley.stableMarriage(students, universities);

        System.out.println("Распределение студентов по университетам:");
        for (Map.Entry<Student, University> entry : matches.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}