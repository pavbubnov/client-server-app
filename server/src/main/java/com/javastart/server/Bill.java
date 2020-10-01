package com.javastart.server;

public class Bill {

    private Integer bill;

    public Bill(Integer bill) {
        this.bill = bill;
    }

    public Bill() {
    }

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "amount=" + bill +
                '}';
    }
}
