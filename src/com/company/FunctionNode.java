package com.company;

import java.util.Set;

public class FunctionNode {
    String name, shape, borderColor, style, fillColor;
    Set<String> neighbors; // only include functions that call this function
    int numOfLines, numOfReferences;
    static final int BORDER_WIDTH = 3;

    public FunctionNode(String functionName) {
        this.name = functionName;
        this.shape = "oval";
        this.borderColor = "black";
        this.style = "filled";
        this.fillColor = "lightgray";
        this.numOfLines = 0;
        this.numOfReferences = 0;
        this.neighbors = null;
    }

    public void setNumOfLines(Integer numOfLines){
        this.numOfLines = numOfLines;
        //setFillColor();
    }

    public void setNumOfRef(Integer numOfRef){
        this.numOfReferences = numOfRef;
        //setBorderColor();
    }

    private String setFillColor(){
        if (this.numOfLines <= 20){
            this.fillColor = "palegreen3";
        } else if (this.numOfLines <= 50){
            this.fillColor = "khaki1";
        } else if (this.numOfLines <= 100){
            this.fillColor = "orange";
        } else {
            this.fillColor = "orangered1";
        }
        return "none";
    }

    private String setBorderColor(){
        return "";
    }



}
