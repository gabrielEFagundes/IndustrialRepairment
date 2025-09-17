package com.back.service;

import com.back.dao.MachineDAO;
import com.back.dao.PartDAO;
import com.back.dao.RepairOrderDAO;
import com.back.dao.TechinicianDAO;
import com.back.model.Machine;
import com.back.model.Part;
import com.back.model.RepairOrder;
import com.back.model.Technician;
import com.back.view.Lists;
import com.back.view.MainView;
import com.back.view.ValidationMessages;
import com.back.view.Inputs;

import java.util.List;

public class MainService {

    Inputs inputs = new Inputs();
    Lists lists = new Lists();
    boolean validation, validStr, validDouble;
    int choiceMach, choiceTech;

    Machine machine;
    Technician technician;
    Part part;
    RepairOrder repairOrder;

    MachineDAO machineDAO = new MachineDAO();
    TechinicianDAO techinicianDAO = new TechinicianDAO();
    PartDAO partDAO = new PartDAO();
    RepairOrderDAO repairOrderDAO = new RepairOrderDAO();

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

                }while(!validStr && !validDouble); // TODO: SOLVE THIS!! Not working for some reason?

                String save = partDAO.signPart(part) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                MainView.printMessage(save);
            }
            case 4 -> {
                List<Machine> machines = machineDAO.operatingMachines();
                List<Technician> technicians = techinicianDAO.allTechnicians();

                do {
                    repairOrder = inputs.createRepairOrder(machines, technicians);

                    // TODO: add verification
                }while();

                String save = repairOrderDAO.createRepairOrder(repairOrder) ? ValidationMessages.successDatabase() : ValidationMessages.errorDatabase();
                MainView.printMessage(save);
            }
            case 5 -> {

            }
            case 6 -> {

            }
            case 0 -> System.exit(0);
            default -> ValidationMessages.errorNotFound();
        }
    }

}
