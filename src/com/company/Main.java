package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // Read file
        String fileName = "test.go";
        List<String> content = Files.readAllLines(Paths.get(fileName));
//        System.out.println(content);

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

//        System.out.println(functions);

        Map<String, Set<String>> functionMap = new HashMap<>();

        for (String f: functions) {
            String[] lines = f.split("\n");

            String caller = findFunction(lines[0]);

//            System.out.println(Arrays.toString(lines));
            for (int i=1; i<lines.length; i++) {
                String callee = findFunction(lines[i]);

                if (callee.isEmpty()) {
                    continue;
                }
                Set<String> funcSet = functionMap.getOrDefault(callee, new HashSet<>());

                funcSet.add(caller);
//                System.out.println(func);
                functionMap.put(callee, funcSet);
            }
        }
        System.out.println("mappings");
        for (Map.Entry<String, Set<String>> e: functionMap.entrySet()) {
            System.out.println(e.getKey());
            for (String s: e.getValue()) {
                System.out.println("\t"+s);
            }
        }


    }

    private static String findFunction(String line) {
        int idx = line.indexOf('(');
//        System.out.println(idx);
        if (idx > 0) {
            for (int i=idx-1; i>0; i--) {
                if (!Character.isLetterOrDigit(line.charAt(i))) {
//                    System.out.println(i);
//                    System.out.println(line.substring(i, idx));
                    return line.substring(i, idx);
                }
            }
        }
        return "";
    }
}
