/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.controller;

import io.fourfinanceit.homework.risk.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.fourfinanceit.homework.risk.LoanService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mazdarati
 */
@RestController
public class LoanController {

    @Autowired
    private LoanService service;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Map<String, Object> createLoan(@RequestBody Loan loan) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (service.checkLoanNumberPerDay(loan) && service.checkLoanPossibleAmount(loan)) {
                if (service.saveLoan(loan)) {
                    result.put("message", "OK");
                    result.put("success", true);
                } else {
                    result.put("message", "Error on saving loan");
                    result.put("success", false);
                }
            } else {
                result.put("message", "Rejection");
                result.put("success", false);
            }
        } catch (Exception e) {
            result.put("message", "Internal error");
            result.put("success", false);
        }
        return result;
    }
}
