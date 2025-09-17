package com.back.service;

import com.back.model.Machine;
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
}
