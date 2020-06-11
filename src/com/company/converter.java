package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class converter {

    Map<String, Set<String>> functionMap;
    Map<String, String> funcMap = new HashMap<>();


    converter( Map<String, Set<String>> functionMap, Map<String, String> funcMap){
        this.functionMap = functionMap;
        this.funcMap = funcMap;
    }
// John -> node and edge text conversion

    public void mapToDot () throws IOException{

        Path fileName = Path.of("dot.txt");
        String beginSyntax = "digraph functionAnalysis {";
        String bodyContent ="";
        String endSyntax = "}";

        String writeToFiles ="";

//        "\"" used to insert quotations marks, if dot.txt doesn't have the quotation marsk around
//        now I need to do the following, implement stand alone functions to be recognized (ie, has no other nodes calling it or it calling other)
        for (Map.Entry<String, Set<String>> e: functionMap.entrySet()) {
            for (String s: e.getValue()) {
                bodyContent +=
                                "\"" + s + "\""
                            + "->" +
                            "\"" + e.getKey() +  "\"" + "\n";
            }
        }
// Lone node calculator
        for (Map.Entry<String, String> e: funcMap.entrySet()) {
            if(functionMap.get(e.getValue()) == null){
                bodyContent +=
                        "\"" + e.getValue() + "\"" + "\n";
            }

        }

        writeToFiles = beginSyntax + "\n" + bodyContent + "\n" + endSyntax;

        Files.writeString(fileName, writeToFiles);

    }




}
