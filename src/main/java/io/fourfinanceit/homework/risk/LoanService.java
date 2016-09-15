/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.risk;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mazdarati
 */
@Service
public class LoanService {

    private static final int MAX_PER_DAY = 3;
    private static final int MAX_AMOUNT = 100;

    public LoanService() {
    }

    @Autowired
    private LoanRepository loanRepository;

    public boolean checkLoanNumberPerDay(Loan loan) {
        LocalDateTime startDay = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0, 0));
        LocalDateTime endDay = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59, 999));
        Long count = loanRepository.countByIpAddressAndDateCreateBetween(
                loan.getIp_address(), startDay, endDay);
        return count < MAX_PER_DAY;
    }

    public boolean checkLoanPossibleAmount(Loan loan) {
        LocalDateTime expTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(6, 0, 0, 0));
        if (expTime.compareTo(loan.getDateCreate()) >= 0) {
            if (loan.getAmount() >= MAX_AMOUNT) {
                return false;
            }
        }
        return true;
    }

    public boolean saveLoan(Loan loan) {
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.convertLoanToEntity(loan);
        try {
            loanRepository.save(loanEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
