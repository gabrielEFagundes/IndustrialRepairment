package com.back.model;

public class PartOrder {

    private RepairOrder idOrder;
    private Part idPart;
    private double amount;

    public PartOrder(RepairOrder idOrder, Part idPart, double amount) {
        this.idOrder = idOrder;
        this.idPart = idPart;
        this.amount = amount;
    }

    public RepairOrder getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(RepairOrder idOrder) {
        this.idOrder = idOrder;
    }

    public Part getIdPart() {
        return idPart;
    }

    public void setIdPart(Part idPart) {
        this.idPart = idPart;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
