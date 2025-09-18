package com.back.dto;

public class PartOrderDTO {

    private int repId;
    private int partId;
    private String name;
    private double storage;
    private String status;
    private double amount;

    public PartOrderDTO(int repId, int partId, String name, double storage, String status, double amount) {
        this.repId = repId;
        this.partId = partId;
        this.name = name;
        this.storage = storage;
        this.status = status;
        this.amount = amount;
    }

    public int getRepId() {
        return repId;
    }

    public void setRepId(int repId) {
        this.repId = repId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStorage() {
        return storage;
    }

    public void setStorage(double storage) {
        this.storage = storage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
