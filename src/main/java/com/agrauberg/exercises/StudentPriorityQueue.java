package com.agrauberg.exercises;

import java.util.*;

public class StudentPriorityQueue {

    private final static Scanner scan = new Scanner("12\n" +
            "ENTER John 3.75 50\n" +
            "ENTER Mark 3.8 24\n" +
            "ENTER Shafaet 3.7 35\n" +
            "SERVED\n" +
            "SERVED\n" +
            "ENTER Samiha 3.85 36\n" +
            "SERVED\n" +
            "ENTER Ashley 3.9 42\n" +
            "ENTER Maria 3.6 46\n" +
            "ENTER Anik 3.95 49\n" +
            "ENTER Dan 3.95 50\n" +
            "SERVED");
    private static final Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            students.
                    stream().
                    map(Student::getName).
                    forEach(System.out::println);
        }
    }
}

class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getCgpa() != o2.getCgpa()) {
            return o1.getCgpa() < o2.getCgpa() ? 1 : -1;
        }
        if (!o1.getName().equals(o2.getName())) {
            return o1.getName().compareTo(o2.getName());
        }
        return o1.getId() > o2.getId() ? 1 : -1;
    }
}

class Student {
    private final int id;
    private final String name;
    private final double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }
}

class Priorities {
    List<Student> getStudents(List<String> events) {
        PriorityQueue<Student> studentsQ = new PriorityQueue<>(events.size(), new StudentComparator());

        events.forEach(s -> {
            String[] line = s.split(" ");
            switch(line[0]) {
                case "SERVED":
                    studentsQ.poll();
                    break;
                case "ENTER":
                    studentsQ.add(new Student(Integer.parseInt(line[3]), line[1], Double.parseDouble(line[2])));
                    break;
                default:
                    System.out.println("INVALID LINE");
                    break;
            }
        });

        List<Student> sorted = new ArrayList<>();
        while(!studentsQ.isEmpty()) {
            sorted.add(studentsQ.poll());
        }
        return sorted;
    }
}