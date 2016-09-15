/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.fourfinanceit.homework.risk;

import java.time.LocalDateTime;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Mazdarati
 */
@Transactional
public interface LoanRepository extends CrudRepository<LoanEntity, Long> {

    public Long countByIpAddressAndDateCreateBetween(String ip_address, LocalDateTime startDay, LocalDateTime endDay);
}
