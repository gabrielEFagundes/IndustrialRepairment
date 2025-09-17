package com.back.view;

import java.util.Scanner;

public class MainView {

    static Scanner scan = new Scanner(System.in);

    public String mainMenu(){
        System.out.print("\nINDUSTRIAL REPAIRMENT SYSTEM" +
                "\n1- Sign Machine" +
                "\n2- Sign Technician" +
                "\n3- Sign Part" +
                "\n4- Create Repairment Order" +
                "\n5- Associate Parts to a Order" +
                "\n6- Execute Repairment" +
                "\n\n0- Exit" +
                "\n-> ");

        return scan.nextLine();
    }

    public static void printMessage(String message){
        System.out.print(message);
    }

}
