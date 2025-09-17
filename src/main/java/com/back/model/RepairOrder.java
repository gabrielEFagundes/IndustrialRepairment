package com.back.model;

import com.back.model.enums.RepairOrderStatus;

import java.sql.Date;

public class RepairOrder {

    private int id;
    private int idMachine;
    private int idTechnician;
    private Date dateSolicitation;
    private RepairOrderStatus status;

    public RepairOrder(int idMachine, int idTechnician, Date dateSolicitation, RepairOrderStatus status) {
        this.idMachine = idMachine;
        this.idTechnician = idTechnician;
        this.dateSolicitation = dateSolicitation;
        this.status = status;
    }

    public RepairOrder(int id, int idMachine, int idTechnician, Date dateSolicitation, RepairOrderStatus status) {
        this.id = id;
        this.idMachine = idMachine;
        this.idTechnician = idTechnician;
        this.dateSolicitation = dateSolicitation;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(int idMachine) {
        this.idMachine = idMachine;
    }

    public int getIdTechnician() {
        return idTechnician;
    }

    public void setIdTechnician(int idTechnician) {
        this.idTechnician = idTechnician;
    }

    public Date getDateSolicitation() {
        return dateSolicitation;
    }

    public void setDateSolicitation(Date dateSolicitation) {
        this.dateSolicitation = dateSolicitation;
    }

    public RepairOrderStatus getStatus() {
        return status;
    }

    public void setStatus(RepairOrderStatus status) {
        this.status = status;
    }
}
