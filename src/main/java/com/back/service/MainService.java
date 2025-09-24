package com.back.service;

import com.back.dao.*;
import com.back.dto.PartOrderDTO;
import com.back.dto.RepairOrderDTO;
import com.back.model.*;
import com.back.util.Connectate;
import com.back.view.MainView;
import com.back.view.ValidationMessages;
import com.back.view.Inputs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainService {

    Inputs inputs = new Inputs();
    boolean validation, validStr, validDouble, validTech, validMach,
            validRepair, validPart;

    //int choiceMach, choiceTech;
    double validStorage;

    Machine machine;
    Technician technician;
    Part part;
    RepairOrder repairOrder;
    PartOrder partOrder;

    MachineDAO machineDAO = new MachineDAO();
    TechinicianDAO techinicianDAO = new TechinicianDAO();
    PartDAO partDAO = new PartDAO();
    RepairOrderDAO repairOrderDAO = new RepairOrderDAO();
    PartOrderDAO partOrderDAO = new PartOrderDAO();

    Connection conn = null;

    public void programMenu(int input){
        switch(input){
            case 1 -> {
                do {
                    machine = inputs.signMachine();
                    validation = ValidateInputsAuxilier.validateStr(machine.getName(), machine.getSection());

                }while(!validation);

                String save = machineDAO.signMachine(machine) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                MainView.printMessage(save);
            }
            case 2 -> {
                do{
                    technician = inputs.signTechnician();
                    validation = ValidateInputsAuxilier.validateStr(technician.getName(), technician.getSpecialty());

                }while(!validation);

                String save = techinicianDAO.signTechnician(technician) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                MainView.printMessage(save);
            }
            case 3 -> {
                do {
                    part = inputs.signPart();
                    validStr = ValidateInputsAuxilier.validateStr(part.getName());
                    validDouble = ValidateInputsAuxilier.validadeDouble(part.getStorage());

                }while(!validStr || !validDouble); // it was only the condition &&............

                String save = partDAO.signPart(part) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                MainView.printMessage(save);
            }
            case 4 -> {
                List<Machine> machines = machineDAO.operatingMachines();
                List<Technician> technicians = techinicianDAO.allTechnicians();
                int savedId = 0;

                do {
                    repairOrder = inputs.createRepairOrder(machines, technicians);
                    validMach = ValidateInputsAuxilier.validateMachineChoice(machines, repairOrder.getIdMachine());
                    validTech = ValidateInputsAuxilier.validateTechnicianChoice(technicians, repairOrder.getIdTechnician());

                    if(validMach && validTech){
                        savedId = repairOrder.getIdMachine();
                    }

                }while(!validMach || !validTech);

                try {
                    conn = Connectate.begin();
                    conn.setAutoCommit(false);

                    String save = repairOrderDAO.createRepairOrder(repairOrder) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                    machineDAO.updateStatusToInRepair(savedId);
                    System.out.println(savedId);
                    MainView.printMessage(save);

                    conn.commit();
                }catch(SQLException e){
                    try{
                        conn.rollback();
                        conn.close();
                    }catch(SQLException e2){
                        ValidationMessages.errorConnecting();
                    }
                }finally{
                    try{
                        conn.close();
                    }catch(SQLException e){
                        ValidationMessages.errorConnecting();
                    }
                }
            }
            case 5 -> { // maybe I could make it add 2 or more entries on one partOrder, but that violates db security :P
                List<RepairOrderDTO> reps = repairOrderDAO.pendentRepairOrders();
                List<Part> parts = partDAO.allParts();
                List<PartOrder> partOrders = new ArrayList<>();
                boolean moreParts;

                do{
                    partOrder = inputs.createPartOrder(reps, parts);
                    validRepair = ValidateInputsAuxilier.validateRepairChoice(reps, partOrder.getIdOrder());
                    validPart = ValidateInputsAuxilier.validatePartChoice(parts, partOrder.getIdPart());
                    validDouble = ValidateInputsAuxilier.validadeDouble(partOrder.getAmount());

                    if(validRepair && validPart && validDouble) {
                        partOrders.add(partOrder);
                    }

                    moreParts = ValidateInputsAuxilier.validateMoreParts(inputs.addAnotherPart());

                }while((!validRepair || !validPart || !validDouble) || moreParts);

                String save = partOrderDAO.signPartOrders(partOrders, partOrder) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                MainView.printMessage(save);
            }
            case 6 -> {
                List<PartOrderDTO> parts = partOrderDAO.pendentAndStorageParts();
                int choice = inputs.startRepair(parts);
                validStorage = ValidateInputsAuxilier.validateStorage(parts, choice);

                if(validStorage != 0){

                    try {
                        conn = Connectate.begin();
                        conn.setAutoCommit(false);

                        validPart = partDAO.updatePartStorage(validStorage, choice);
                        validRepair = repairOrderDAO.updateStatus(choice);
                        validMach = machineDAO.updateStatusToOperational(choice);

                        if (validPart && validRepair && validMach) {
                            String save = ValidationMessages.successDatabase();
                            MainView.printMessage(save);
                            conn.commit();
                        }
                    }catch(SQLException e){
                        try{
                            conn.rollback();
                            conn.close();
                        }catch(SQLException e2){
                            ValidationMessages.errorConnecting();
                        }
                    }finally{
                        try{
                            conn.close();
                        }catch(SQLException e){
                            ValidationMessages.errorConnecting();
                        }
                    }

                }else{
                    ValidationMessages.notEnoughStorage();
                }

            }
            case 0 -> System.exit(0);
            default -> ValidationMessages.errorNotFound();
        }
    }

}
