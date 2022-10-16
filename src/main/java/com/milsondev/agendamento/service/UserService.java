/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milsondev.agendamento.service;

import com.milsondev.agendamento.model.User;
import com.milsondev.agendamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mein
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void salvar(User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Formato de data inválido");
        }
    }

    public void excluir(Long codigo) {
        userRepository.deleteById(codigo);
    }
    
    public User getUserByEmailAndPassword (String email, String password) {         
        return userRepository.getUserByEmailAndPassword(email, password);
    }
    
    public User getUserByEmail (String email) {         
        return userRepository.getUserByEmail(email);
    }

}
