package com.back.view;

public class ValidationMessages {

    public static final String RESET = "\033[0m", RED = "\033[1;31m", GREEN = "\033[1;32m";

    // success
    public static String successDatabase(){
        return GREEN + "\nAction on database successfully done!\n" + RESET;
    }

    public static void successValidation(){
        System.out.println(GREEN + "\nSuccessfully passed the validation test!" + RESET);
    }

    // errors
    public static void errorNotFound(){
        System.out.println(RED + "\nError! Could not find your requisition...\n" + RESET);
    }

    public static void errorConnecting(){
        System.out.println(RED + "\nError! Could not connectate to the Database, nothing was changed.\n" + RESET);
    }

    public static void errorValidation(){
        System.out.println(RED + "\nError! Can't allow any null / empty entries on the database!\n" + RESET);
    }

    public static String errorDatabase(){
        return RED + "\nAction on database could not be completed. Contact us about the problem!" + RESET;
    }

    public static void errorLessThanZero(){
        System.out.println(RED + "\nError! Can't allow values that are below zero on one of the entries!" + RESET);
    }

}
