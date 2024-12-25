package com.janson.lab00.spring.collectiontype;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/12/25 23:33
 **/
public class Stu {
    // 1、 数组类型属性
    private String[] courses;


    // 2、 List集合类型属性
    private List<String> lists;

    // 3、 Map集合类型属性
    private Map<String, String> maps;

    // 4、 Set集合类型属性
    private Set<String> sets;

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }

    public void test(){
        System.out.println(Arrays.toString(courses));
        System.out.println(lists);
        System.out.println(maps);
        System.out.println(sets);
    }
}
