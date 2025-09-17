package com.back.view;

import com.back.model.Machine;
import com.back.model.Part;
import com.back.model.RepairOrder;
import com.back.model.Technician;
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
        System.out.print("\nWhat is the name of the machine?\n-> ");
        String name = scan.nextLine();

        System.out.print("\nWhat is the section of the machine?\n-> ");
        String section = scan.nextLine();

        return new Machine(name, section, MachineStatus.OPERATIONAL);
    }

    public Technician signTechnician(){
        System.out.print("\nWhat is the name of the technician?\n-> ");
        String name = scan.nextLine();

        System.out.print("\nWhat is the specialty of the technician " + name + "?\n-> ");
        String specialty = scan.nextLine();

        return new Technician(name, specialty);
    }

    public Part signPart(){
        System.out.print("\nWhat is the name of the part?\n-> ");
        String name = scan.nextLine();

        System.out.print("\nWhat is the initial storage of the part " + name + "?\n-> ");
        double storage = scan.nextDouble();

        return new Part(name, storage);
    }

    public RepairOrder createRepairOrder(List<Machine> machines, List<Technician> technicians){
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

}
