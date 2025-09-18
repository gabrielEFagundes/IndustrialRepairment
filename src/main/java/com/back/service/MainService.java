package com.back.service;

import com.back.dao.*;
import com.back.dto.PartOrderDTO;
import com.back.dto.RepairOrderDTO;
import com.back.model.*;
import com.back.view.Lists;
import com.back.view.MainView;
import com.back.view.ValidationMessages;
import com.back.view.Inputs;

import java.util.ArrayList;
import java.util.List;

public class MainService {

    Inputs inputs = new Inputs();
    boolean validation, validStr, validDouble, validTech, validMach,
            validRepair, validPart, validStorage;

    int choiceMach, choiceTech;

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

    public void programMenu(int input){
        switch(input){
            case 1 -> {
                do {
                    machine = inputs.signMachine();
                    validation = ValidateInputs.validateStr(machine.getName(), machine.getSection());

                }while(!validation);

                String save = machineDAO.signMachine(machine) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                MainView.printMessage(save);
            }
            case 2 -> {
                do{
                    technician = inputs.signTechnician();
                    validation = ValidateInputs.validateStr(technician.getName(), technician.getSpecialty());

                }while(!validation);

                String save = techinicianDAO.signTechnician(technician) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                MainView.printMessage(save);
            }
            case 3 -> {
                do {
                    part = inputs.signPart();
                    validStr = ValidateInputs.validateStr(part.getName());
                    validDouble = ValidateInputs.validadeDouble(part.getStorage());

                }while(!validStr || !validDouble); // it was only the condition &&............

                String save = partDAO.signPart(part) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                MainView.printMessage(save);
            }
            case 4 -> {
                List<Machine> machines = machineDAO.operatingMachines();
                List<Technician> technicians = techinicianDAO.allTechnicians();

                do {
                    repairOrder = inputs.createRepairOrder(machines, technicians);
                    validMach = ValidateInputs.validateMachineChoice(machines, repairOrder.getIdMachine());
                    validTech = ValidateInputs.validateTechnicianChoice(technicians, repairOrder.getIdTechnician());

                }while(!validMach || !validTech);

                String save = repairOrderDAO.createRepairOrder(repairOrder) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                MainView.printMessage(save);
            }
            case 5 -> { // maybe I could make it add 2 or more entries on one partOrder, but that violates db security :P
                List<RepairOrderDTO> reps = repairOrderDAO.pendentRepairOrders();
                List<Part> parts = partDAO.allParts();
                List<PartOrder> partOrders = new ArrayList<>();
                boolean moreParts = false;

                do{
                    partOrder = inputs.createPartOrder(reps, parts);
                    validRepair = ValidateInputs.validateRepairChoice(reps, partOrder.getIdOrder());
                    validPart = ValidateInputs.validatePartChoice(parts, partOrder.getIdPart());
                    validDouble = ValidateInputs.validadeDouble(partOrder.getAmount());

                    if(validRepair && validPart && validDouble) {
                        partOrders.add(partOrder);
                    }

                    moreParts = ValidateInputs.validateMoreParts(inputs.addAnotherPart());

                }while((!validRepair || !validPart || !validDouble) || moreParts);

                String save = partOrderDAO.signPartOrders(partOrders, partOrder) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                MainView.printMessage(save);
            }
            case 6 -> {
                List<PartOrderDTO> parts = partOrderDAO.pendentAndStorageParts();
                int choice = inputs.startRepair(parts);
                validStr = ValidateInputs.validateStorage(parts, choice);

                if(validStorage){
                    partDAO.updatePartStorage();
                    // logic here (God dayum how do I'm so done DX)
                }else{
                    ValidationMessages.notEnoughStorage();
                }

            }
            case 0 -> System.exit(0);
            default -> ValidationMessages.errorNotFound();
        }
    }

}
