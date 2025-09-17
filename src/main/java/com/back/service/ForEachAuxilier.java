package com.back.service;

import com.back.model.Machine;
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

}
