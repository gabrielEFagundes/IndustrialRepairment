package com.back.view;

import com.back.dto.PartOrderDTO;
import com.back.dto.RepairOrderDTO;
import com.back.model.Machine;
import com.back.model.Part;
import com.back.model.Technician;
import com.back.service.ForEachAuxilier;

import java.nio.Buffer;
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

    public static void pendentRepairOrders(List<RepairOrderDTO> reps){
        System.out.printf("%-10s | %-20s | %-20s | %-20s | %-20s\n", "ID", "Machine Name", "Technician Name", "Date", "Status");

        System.out.println(ForEachAuxilier.forEachPendentOrders(reps));
        System.out.println();
    }

    public static void allParts(List<Part> parts){
        System.out.printf("%-10s | %-20s | %-10s\n", "ID", "Name", "Storage");

        System.out.println(ForEachAuxilier.forEachPart(parts));
        System.out.println();
    }

    public static void pendentAndStorageParts(List<PartOrderDTO> parts){
        System.out.printf("%-10s | %-10s | %-20s | %-20s | %-20s | %-10s\n", "RepairID", "PartID", "Part Name", "Part Storage", "Status", "Amount");

        System.out.println(ForEachAuxilier.forEachPendentAndStorageParts(parts));
        System.out.println();
    }

}
