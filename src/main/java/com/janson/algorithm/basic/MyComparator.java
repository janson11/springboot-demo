package com.janson.algorithm.basic;

import com.janson.algorithm.entity.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/9/6 9:56 上午
 */
public class MyComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }

    public static void main(String[] args) {
        Student student1 = new Student("A",1,23);
        Student student2 = new Student("B",2,21);
        Student student3 = new Student("C",3,22);
        Student[] students = new Student[]{student1,student2,student3};
        Student.printStudents(students);

        Arrays.sort(students,new Student.IdAscendingComparator());
        Student.printStudents(students);

        Arrays.sort(students,new Student.IdDescendingComparator());
        Student.printStudents(students);

        PriorityQueue<Student> heap = new PriorityQueue<>(new Student.IdAscendingComparator());
        heap.add(student3);
        heap.add(student2);
        heap.add(student1);
        while (!heap.isEmpty()) {
            Student student = heap.poll();
            System.out.println("Name:"+student.getName()+",Id:"+student.getId() +",Age:"+student.getAge());
        }

    }
}
