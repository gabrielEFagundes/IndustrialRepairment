package com.back.service;

public class Parser {

    public static int parseInput(String input){
        try{
            return Integer.parseInt(input);

        }catch(NumberFormatException e){
            return -1;
        }
    }

}
