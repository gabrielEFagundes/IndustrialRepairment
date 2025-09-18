package com.back.service;

import com.back.dto.PartOrderDTO;
import com.back.dto.RepairOrderDTO;
import com.back.model.Machine;
import com.back.model.Part;
import com.back.model.Technician;
import com.back.view.ValidationMessages;

import java.util.List;

public class ValidateInputs {

    public static boolean validateStr(String... input){
        for(String i : input){
            if(i == null || i.isBlank()){
                ValidationMessages.errorValidation();
                return false;
            }
        }
        ValidationMessages.successValidation();
        return true;
    }

    public static boolean validadeDouble(Double input){
        if(input < 0.0 || input.isNaN()){
            ValidationMessages.errorLessThanZero();
            return false;
        }
        ValidationMessages.successValidation();
        return true;
    }

    public static boolean validateMachineChoice(List<Machine> machines, int choice){
        for(Machine i : machines){
            if(choice == i.getId()){
                return true;
            }
        }
        ValidationMessages.errorNotFound();
        return false;
    }

    public static boolean validateTechnicianChoice(List<Technician> technicians, int choice){
        for(Technician i : technicians){
            if(choice == i.getId()){
                return true;
            }
        }
        ValidationMessages.errorNotFound();
        return false;
    }

    public static boolean validateRepairChoice(List<RepairOrderDTO> reps, int choice){
        for(RepairOrderDTO i : reps){
            if(choice == i.getId()){
                return true;
            }
        }
        ValidationMessages.errorNotFound();
        return false;
    }

    public static boolean validatePartChoice(List<Part> parts, int choice){
        for(Part i : parts){
            if(choice == i.getId()){
                return true;
            }
        }
        ValidationMessages.errorNotFound();
        return false;
    }

    public static boolean validateMoreParts(Character choice){
        return choice.toString().equalsIgnoreCase("y");
    }

    public static double validateStorage(List<PartOrderDTO> parts, int choice){
        boolean found = false;
        for(PartOrderDTO i : parts) {
            if (choice == i.getRepId()) {
                found = true;
                if(i.getStorage() >= i.getAmount()){
                    ValidationMessages.enoughStorage();
                    return i.getAmount();
                }
            }
        }
        if(!found){
            ValidationMessages.errorNotFound();
        }
        return 0;
    }
}
