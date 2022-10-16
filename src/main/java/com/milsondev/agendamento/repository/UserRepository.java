/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milsondev.agendamento.repository;

import com.milsondev.agendamento.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Mein
 */
public interface UserRepository extends JpaRepository<User, Long>{

    @Query(value = "select * from usuario where email = ?1 and password = ?2", nativeQuery = true)
    User getUserByEmailAndPassword(String email, String password);
    
    @Query(value = "select * from usuario where email = ?1", nativeQuery = true)
    User getUserByEmail(String email);
    
}
