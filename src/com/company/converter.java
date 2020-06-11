package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class converter {

    Map<String, Set<String>> functionMap;

    converter( Map<String, Set<String>> functionMap){
        this.functionMap = functionMap;
    }
// John -> node and edge text conversion

    public void mapToDot () throws IOException{

        Path fileName = Path.of("dot.txt");
        String beginSyntax = "digraph functionAnalysis {";
        String bodyContent ="";
        String endSyntax = "}";

        String writeToFiles ="";

        for (Map.Entry<String, Set<String>> e: functionMap.entrySet()) {
            for (String s: e.getValue()) {
                bodyContent += s + "->" + e.getKey() + "\n";
            }
        }

        writeToFiles = beginSyntax + "\n" + bodyContent + "\n" + endSyntax;

        Files.writeString(fileName, writeToFiles);

    }




}
