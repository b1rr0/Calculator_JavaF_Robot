package com.eknm;

import java.util.HashMap;
import java.util.Map;

public class JavaTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "q1");
        map.put(2, "q2");
        map.put(3, "q3");
        map.put(4, "q4");

        map.values().forEach(e -> System.out.println(e));
        System.out.println(map.values().contains("q1"));
        System.out.println("---");
        System.out.println(map.entrySet().contains("q1"));
        map.entrySet().forEach(e-> System.out.println(e));

    }
}
