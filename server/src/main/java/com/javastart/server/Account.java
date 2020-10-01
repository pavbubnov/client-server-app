package com.javastart.server;

public class Account {


    private Integer id;
    private String name;
    private Bill amount;

    public Account(Integer id, String name, Bill amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bill getAmount() {
        return amount;
    }

    public void setAmount(Bill amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
