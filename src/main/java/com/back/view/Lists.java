package com.back.view;

import com.back.model.Machine;
import com.back.model.Technician;
import com.back.service.ForEachAuxilier;

import java.util.List;

public class Lists {

    public static void operatingMachines(List<Machine> machines){
        System.out.printf("\n%-10s | %-20s | %-30s\n", "ID", "Name", "Section");

        System.out.println(ForEachAuxilier.forEachMachine(machines));
        System.out.println();
    }

    public static void allTechnicians(List<Technician> technicians){
        System.out.printf("%-10s | %-20s | %-30s\n", "ID", "Name", "Specialty");

        System.out.println(ForEachAuxilier.forEachTechnician(technicians));
        System.out.println();
    }

}
