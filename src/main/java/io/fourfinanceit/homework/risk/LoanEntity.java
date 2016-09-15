/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.risk;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Mazdarati
 */
@Entity
@Table(name = "LOAN")
public class LoanEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long loan_sid;
    @NotNull
    private String firstname;
    @NotNull
    private String surname;
    @NotNull
    private double amount;
    @NotNull
    private int term;
    @NotNull
    private LocalDateTime dateCreate;
    @NotNull
    private String ipAddress;

    public LoanEntity() {
    }

    public LoanEntity(long loan_sid, String firstname, String surname, double amount, int term, LocalDateTime dateCreate, String ip_address) {
        this.loan_sid = loan_sid;
        this.firstname = firstname;
        this.surname = surname;
        this.amount = amount;
        this.term = term;
        this.dateCreate = dateCreate;
        this.ipAddress = ip_address;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void convertLoanToEntity(Loan loan) {
        this.loan_sid = loan.getLoan_sid();
        this.firstname = loan.getFirstname();
        this.surname = loan.getSurname();
        this.amount = loan.getAmount();
        this.term = loan.getTerm();
        this.dateCreate = loan.getDateCreate();
        this.ipAddress = loan.getIp_address();
    }

}
