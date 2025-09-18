package com.back.model;

public class PartOrder {

    private int idOrder;
    private int idPart;
    private double amount;

    public PartOrder(int idOrder, int idPart, double amount) {
        this.idOrder = idOrder;
        this.idPart = idPart;
        this.amount = amount;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(Part idPart) {
        this.idPart = idPart.getId();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
