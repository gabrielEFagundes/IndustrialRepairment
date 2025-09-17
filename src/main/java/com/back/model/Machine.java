package com.back.model;

import com.back.model.enums.MachineStatus;

public class Machine {

    private int id;
    private String name;
    private String section;
    private MachineStatus status;

    public Machine(String name, String section, MachineStatus status) {
        this.name = name;
        this.section = section;
        this.status = status;
    }

    public Machine(int id, String name, String section) {
        this.id = id;
        this.name = name;
        this.section = section;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public MachineStatus getStatus() {
        return status;
    }

    public void setStatus(MachineStatus status) {
        this.status = status;
    }
}
