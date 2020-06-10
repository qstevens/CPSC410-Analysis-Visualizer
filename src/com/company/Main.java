package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // Read file
        String fileName = "test.js";
        List<String> content = Files.readAllLines(Paths.get(fileName));
        System.out.println(content);
    }
}
