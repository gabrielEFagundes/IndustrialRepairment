package com.back.dto;

import java.sql.Date;

public class RepairOrderDTO {

    private int id;
    private String machineName;
    private String technicianName;
    private Date dateSolicitation;
    private String status;

    public RepairOrderDTO(int id, String machineName, String technicianName, Date dateSolicitation, String status) {
        this.id = id;
        this.machineName = machineName;
        this.technicianName = technicianName;
        this.dateSolicitation = dateSolicitation;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public Date getDateSolicitation() {
        return dateSolicitation;
    }

    public void setDateSolicitation(Date dateSolicitation) {
        this.dateSolicitation = dateSolicitation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
