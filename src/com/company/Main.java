package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, Set<String>> functionMap = new HashMap<>();
        Map<String, String> funcMap = new HashMap<>();
        Map<String, Integer> funcLenMap = new HashMap<>();

        // Read file
        String fileName = "test.go";
        List<String> content = Files.readAllLines(Paths.get(fileName));

        // Get individual functions
        List<String> functions = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (String s: content) {
            if (sb.toString().isEmpty() && s.split("\\s")[0].equals("func")) {
                sb.append(s).append("\n");
            } else if (s.equals("}")) {
                sb.append(s).append("\n");
                functions.add(sb.toString());
                sb.setLength(0);
            } else if (sb.length() > 0) {
                sb.append(s).append("\n");
            }
        }

        for (String f: functions) {
            String[] lines = f.split("\n");
            String funcSignature = findFunctionSignature(lines[0]);
            String func = findFunction(lines[0]);
            funcMap.put(func, funcSignature);
        }
//        System.out.println("Start funcMap");
//        for (Map.Entry<String, String> e: funcMap.entrySet()) {
//            System.out.println(e.getKey());
//                System.out.println("\t"+e.getValue());
//
//        }
//        System.out.println("End funcMap");


        for (String f: functions) {
            String[] lines = f.split("\n");
            String funcSignature = findFunctionSignature(lines[0]);
            funcLenMap.put(funcSignature, lines.length);
        }


        System.out.println("Function Lengths:");
        for (Map.Entry<String, Integer> e: funcLenMap.entrySet()) {
            System.out.println(e.getKey());
            System.out.println("\t"+e.getValue());
        }

        // func(n, m) -> [func(n), func(n, m, p)]
        // func(2) -> func(n, m)
        // findFunction -> func(0), func(1), func(2)
        // findFunctionSignature -> func(n, m)

        for (String f: functions) {
            String[] lines = f.split("\n");

            String caller = findFunctionSignature(lines[0]);

            for (int i=1; i<lines.length; i++) {
                String calleeFun = findFunction(lines[i]);
                if (calleeFun.isEmpty()) {
                    continue;
                }
                String callee = funcMap.get(calleeFun);

                Set<String> funcSet = functionMap.getOrDefault(callee, new HashSet<>());

                funcSet.add(caller);
                functionMap.put(callee, funcSet);
            }
        }

        System.out.println("Function Mappings:");
        for (Map.Entry<String, Set<String>> e: functionMap.entrySet()) {
            System.out.println(e.getKey());
            for (String s: e.getValue()) {
                System.out.println("\t"+s);
            }
        }

        converter converter = new converter(functionMap, funcMap, funcLenMap);
        converter.mapToDot();
    }

    private static String findFunction(String line) {
        int idx = line.indexOf('(');
//        System.out.println(idx);
        if (idx > 0) {
            int endx = line.indexOf(')');

            String[] lines = line.substring(idx+1, endx).split(",");

            for (int i=idx-1; i>0; i--) {
                if (!Character.isLetterOrDigit(line.charAt(i))) {
//                    System.out.println(i);
//                    System.out.println(line.substring(i, idx));
                    if (endx == idx+1) return line.substring(i, idx) + "(0)";
                    return line.substring(i, idx) + "(" + lines.length + ")";
                }
            }
        }
        return "";
    }

    private static String findFunctionSignature(String line) {
        int idx = line.indexOf('(');
//        System.out.println(idx);
        if (idx > 0) {
            int endx = line.indexOf(')');
            for (int i=idx-1; i>0; i--) {
                if (!Character.isLetterOrDigit(line.charAt(i))) {
                    return line.substring(i, endx+1);
                }
            }



        }
        return "";
    }
}
