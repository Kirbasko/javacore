package lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Info {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        students.add(new Student("Ivan", Arrays.asList(new Course("paint"), new Course("music"),
                new Course("math"))));

        students.add(new Student("Ibragim", Arrays.asList(new Course("paint"), new Course("music"))));

        students.add(new Student("Alex", Arrays.asList(new Course("paint"), new Course("music"))));

        students.add(new Student("Kirill", Arrays.asList(new Course("paint"))));

        System.out.println(students.stream()
                .map(s -> s.getCourses())
                .flatMap(f -> f.stream())
                .collect(Collectors.toSet()));


        System.out.println(students.stream()
                .sorted((s1, s2) -> s2.getCourses().size() - s1.getCourses().size())
                .limit(3)
                .collect(Collectors.toList()));


        Course course11 = new Course("music");
        System.out.println(students.stream()
                .filter(s -> s.getCourses().contains(course11))
                .collect(Collectors.toList()));
    }
}
