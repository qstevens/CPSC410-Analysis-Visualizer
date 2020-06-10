package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // Read file
        String fileName = "test.js";
        List<String> content = Files.readAllLines(Paths.get(fileName));
        System.out.println(content);
        List<String> functions = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String s: content) {
            if (sb.toString().isEmpty() && s.split("\\s")[0].equals("function")) {
                sb.append(s);
            } else if (s.equals("}")) {
                sb.append(s);
                functions.add(sb.toString());
                sb.setLength(0);
            } else if (sb.length() > 0) {
                sb.append(s);
            }
        }

        System.out.println(functions);
    }
}
