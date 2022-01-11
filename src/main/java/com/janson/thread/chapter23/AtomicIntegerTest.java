package com.janson.thread.chapter23;

import com.janson.thread.chapter12.SynTest;
import com.janson.thread.chapter20.Person;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/1/5 6:53 下午
 */
public class AtomicIntegerTest {

    public static void main(String[] args) {
       /* AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println("1:   " + atomicInteger.get());
        atomicInteger.getAndIncrement();
        System.out.println("2:   " + atomicInteger.get());
        int andSet = atomicInteger.getAndSet(2);
        System.out.println("3:    " + andSet);
        System.out.println("3 1:  " + atomicInteger.get());
        int andIncrement = atomicInteger.getAndIncrement();
        System.out.println("andIncrement:" + andIncrement);
        System.out.println("last:" + atomicInteger.get());

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(2);
        atomicIntegerArray.getAndIncrement(1);
        System.out.println("atomicIntegerArray:" + atomicIntegerArray);*/
//        atomicReferenceTest();
        atomicStampedReferenceTest();


    }

    private static void atomicReferenceTest() {
        AtomicReference<Person> atomicReference = new AtomicReference<Person>(new Person());
        Person person = new Person();
        person.setAge(1);
        person.setName("xiaoyuer");
        Person andSet = atomicReference.getAndSet(person);
        System.out.println(andSet.toString());
        System.out.println("11" + atomicReference.get());
    }

    private static void atomicStampedReferenceTest() {
        int initialStamp = (int) System.currentTimeMillis();
        AtomicStampedReference<Person> atomicStampedReference = new AtomicStampedReference(new Person(), initialStamp);
        atomicStampedReference.set(new Person("xiaoyuer1", 12), (int) System.currentTimeMillis());
        System.out.println(atomicStampedReference.getReference() + ": " + atomicStampedReference.getStamp());
        AtomicMarkableReference atomicMarkableReference = new AtomicMarkableReference(new Person(),false);
        atomicMarkableReference.set(new Person("xiaoyuer2",13),true);
        boolean[] booleans = new boolean[1];
        Object reference = atomicMarkableReference.getReference();
        System.out.println(atomicMarkableReference.get(booleans) +":"+reference);
    }
}
