/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.risk;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Mazdarati
 */
public class Loan implements Serializable {

    private long loan_sid;
    private String firstname;
    private String surname;
    private double amount;
    private int term;
    private LocalDateTime dateCreate;
    private String ip_address;

    public Loan() {
    }

    public Loan(String firstname, String surname, double amount, int term, LocalDateTime dateCreate, String ip_address) {
        this.firstname = firstname;
        this.surname = surname;
        this.amount = amount;
        this.term = term;
        this.dateCreate = dateCreate;
        this.ip_address = ip_address;
    }

    public long getLoan_sid() {
        return loan_sid;
    }

    public void setLoan_sid(long loan_sid) {
        this.loan_sid = loan_sid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

}
