package com.back.view;

public class ValidationMessages {

    public static final String RESET = "\033[0m", RED = "\033[1;31m", GREEN = "\033[1;32m";

    // success
    public static String successDatabase(){
        return GREEN + "\nAction on database successfully done!" + RESET;
    }

    public static void successValidation(){
        System.out.print(GREEN + "\nSuccessfully passed the validation test!" + RESET);
    }

    public static void enoughStorage(){
        System.out.print(GREEN + "\nThere is in fact enough storage!" + RESET);
    }

    // errors
    public static void errorNotFound(){
        System.out.print(RED + "\nError! Could not find your requisition..." + RESET);
    }

    public static void errorConnecting(){
        System.out.print(RED + "\nError! Could not connectate to the Database, nothing was changed.\nThe error is the following:" + RESET);
    }

    public static void errorValidation(){
        System.out.print(RED + "\nError! Can't allow any null / empty entries on the database!" + RESET);
    }

    public static String errorDatabase(){
        return RED + "\nAction on database could not be completed. Contact us about the problem!" + RESET;
    }

    public static void errorLessThanZero(){
        System.out.print(RED + "\nError! Can't allow values that are below zero on one of the entries!" + RESET);
    }

    public static void notEnoughStorage(){
        System.out.print(RED + "\nNot enough storage! Aborting the operation...");
    }

}
