package com.back.service;

import com.back.dto.PartOrderDTO;
import com.back.dto.RepairOrderDTO;
import com.back.model.Machine;
import com.back.model.Part;
import com.back.model.Technician;

import java.util.List;

public class ForEachAuxilier {

    public static String forEachMachine(List<Machine> machines){
        for(Machine i : machines){
            return String.format("%-10d | %-20s | %-30s", i.getId(), i.getName(), i.getSection());
        }
        return null; // this is just so it doesn't give me an error, because won't alter any results actually!
    }

    public static String forEachTechnician(List<Technician> technicians){
        for(Technician i : technicians){
            return String.format("%-10d | %-20s | %-30s", i.getId(), i.getName(), i.getSpecialty());
        }
        return null;
    }

    public static String forEachPendentOrders(List<RepairOrderDTO> reps){
        for(RepairOrderDTO i : reps){
            return String.format("%-10d | %-20s | %-20s | %-20s | %-20s",
                                i.getId(), i.getMachineName(), i.getTechnicianName(), i.getDateSolicitation(), i.getStatus());
        }
        return null;
    }

    public static String forEachPart(List<Part> parts){
        for(Part i : parts){
            return String.format("%-10d | %-20s | %-10.2f", i.getId(), i.getName(), i.getStorage());
        }
        return null;
    }

    public static String forEachPendentAndStorageParts(List<PartOrderDTO> parts){
        for(PartOrderDTO i : parts){
            return String.format("%-10d | %-10d | %-20s | %-20.2f | %-20s | %-10.2f",
                                i.getRepId(), i.getPartId(), i.getName(), i.getStorage(), i.getStatus(), i.getAmount());
        }
        return null;
    }

}
