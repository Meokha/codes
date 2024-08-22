import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Student {
        String name;
        int age;
        String email;
        String phone;
        String code;
        int gender; // 0: Male, 1: Female
        float grade;

        public Student(String name, int age, String email, String phone, String code,
                       int gender, float grade)
        {
            this.name = name;
            this.age = age;
            this.email = email;
            this.phone = phone;
            this.code = code;
            this.gender = gender;
            this.grade = grade;
        }

        @Override
        public String toString() {
            String genderString = (gender == 0) ? "Male" : "Female";
            return "Student{" +
                    "Name='" + name + '\'' +
                    ", Age=" + age +
                    ", Email='" + email + '\'' +
                    ", Phone='" + phone + '\'' +
                    ", Code='" + code + '\'' +
                    ", Gender=" + genderString +
                    ", Grade=" + grade +
                    '}';
        }

    public static class StudentManagement {
        private static final ArrayList<Student> students = new ArrayList<>();

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nStudent Management System");
                System.out.println("1. Add new student");
                System.out.println("2. Print student list");
                System.out.println("3. Delete student by code");
                System.out.println("4. Sort students by grade (descending)");
                System.out.println("5. Search student by code or name");
                System.out.println("6. Search students with grade >= X");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        printStudents();
                        break;
                    case 3:
                        deleteStudentByCode(scanner);
                        break;
                    case 4:
                        sortStudentsByGrade();
                        break;
                    case 5:
                        searchStudent(scanner);
                        break;
                    case 6:
                        searchStudentsByGrade(scanner);
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private static void addStudent(Scanner scanner) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter phone: ");
            String phone = scanner.nextLine();
            System.out.print("Enter code: ");
            String code = scanner.nextLine();
            System.out.print("Enter gender (0 for Male, 1 for Female): ");
            int gender = scanner.nextInt();
            System.out.print("Enter grade: ");
            float grade = scanner.nextFloat();

            Student student = new Student(name, age, email, phone, code, gender, grade);
            students.add(student);
            System.out.println("Student added successfully.");
        }

        private static void printStudents() {
            if (students.isEmpty()) {
                System.out.println("No students to display.");
            } else {
                for (Student student : students) {
                    System.out.println(student);
                }
            }
        }

        private static void deleteStudentByCode(Scanner scanner) {
            System.out.print("Enter student code to delete: ");
            String code = scanner.nextLine();
            boolean removed = students.removeIf(student -> student.code.equals(code));
            if (removed) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student with code " + code + " not found.");
            }
        }

        private static void sortStudentsByGrade() {
            students.sort(Comparator.comparingDouble((Student student) -> student.grade).reversed());
            System.out.println("Students sorted by grade in descending order.");
        }

        private static void searchStudent(Scanner scanner) {
            System.out.print("Enter student code or name to search: ");
            String input = scanner.nextLine();
            boolean found = false;
            for (Student student : students) {
                if (student.code.equals(input) || student.name.equalsIgnoreCase(input)) {
                    System.out.println(student);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No student found with the provided code or name.");
            }
        }

        private static void searchStudentsByGrade(Scanner scanner) {
            System.out.print("Enter the minimum grade to search: ");
            float minGrade = scanner.nextFloat();
            boolean found = false;
            for (Student student : students) {
                if (student.grade >= minGrade) {
                    System.out.println(student);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No students found with grade >= " + minGrade);
            }
        }
    }
}
