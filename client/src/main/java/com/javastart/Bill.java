package com.javastart;

public class Bill {

    private Integer bill;

    public Bill() {
    }

    public Bill(Integer bill) {
        this.bill = bill;
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
