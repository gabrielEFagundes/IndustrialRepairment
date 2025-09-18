package com.back.view;

import com.back.dto.PartOrderDTO;
import com.back.dto.RepairOrderDTO;
import com.back.model.*;
import com.back.model.enums.MachineStatus;
import com.back.model.enums.RepairOrderStatus;
import com.back.service.Parser;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Inputs {

    Scanner scan = new Scanner(System.in);

    public Machine signMachine(){
        MainView.printCaseNotWorking();
        scan.nextLine();

        System.out.print("\nWhat is the name of the machine?\n-> ");
        String name = scan.nextLine();

        System.out.print("\nWhat is the section of the machine?\n-> ");
        String section = scan.nextLine();

        return new Machine(name, section, MachineStatus.OPERATIONAL);
    }

    public Technician signTechnician(){
        MainView.printCaseNotWorking();
        scan.nextLine();

        System.out.print("\nWhat is the name of the technician?\n-> ");
        String name = scan.nextLine();

        System.out.print("\nWhat is the specialty of the technician " + name + "?\n-> ");
        String specialty = scan.nextLine();

        return new Technician(name, specialty);
    }

    public Part signPart(){
        MainView.printCaseNotWorking();
        scan.nextLine();

        System.out.print("\nWhat is the name of the part?\n-> ");
        String name = scan.nextLine();

        System.out.print("\nWhat is the initial storage of the part " + name + "?\n-> ");
        double storage = scan.nextDouble();

        return new Part(name, storage);
    }

    public RepairOrder createRepairOrder(List<Machine> machines, List<Technician> technicians){
        MainView.printCaseNotWorking();
        scan.nextLine();

        Lists.operatingMachines(machines);

        System.out.print("\nWhat machine would you like to choose?\n-> ");
        String machChoice = scan.nextLine();

        Lists.allTechnicians(technicians);

        System.out.print("\nWhich technician would you like to choose?\n-> ");
        String techChoice = scan.nextLine();

        Date solicitationDate = Date.valueOf(LocalDate.now());

        return new RepairOrder(Parser.parseInput(machChoice),
                Parser.parseInput(techChoice),
                solicitationDate,
                RepairOrderStatus.PENDENT);
    }

    public PartOrder createPartOrder(List<RepairOrderDTO> reps, List<Part> parts){
        MainView.printCaseNotWorking();
        scan.nextLine();

        Lists.pendentRepairOrders(reps);
        System.out.print("\nWhich order ID would you like to choose?\n-> ");
        String orderChoice = scan.nextLine();

        Lists.allParts(parts);
        System.out.print("\nWhich part ID would you like to choose?\n-> ");
        String partChoice = scan.nextLine();

        System.out.print("\nHow many of those would you like?\n-> ");
        String amount = scan.nextLine();

        return new PartOrder(Parser.parseInput(orderChoice),
                Parser.parseInput(partChoice),
                Parser.parseDouble(amount));
    }

    public Character addAnotherPart(){
        MainView.printCaseNotWorking();
        scan.nextLine();

        System.out.print("\nWould you like to add another part?\nIf yes, do not place the same IDs that you had put before for the two respective entries\n(y/*) ->");
        return scan.next().charAt(0);
    }

    public int startRepair(List<PartOrderDTO> parts){
        MainView.printCaseNotWorking();
        scan.nextLine();

        Lists.pendentAndStorageParts(parts);
        System.out.print("\nWhich order would you like to execute?\n-> ");
        return scan.nextInt();
    }

}
