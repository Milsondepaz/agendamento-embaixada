/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.milsondev.agendamento.model;

/**
 *
 * @author Milson
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
//import sun.jvm.hotspot.debugger.AddressException;

public class Correio {

    @Email(regexp = "^(.+)@(.+)$", message = "O e-mail que voce inseriu nao é valido.")
    @NotEmpty(message = "Insira o seu e-mail para recuperar a senha.")
    private String email;

    public Correio() {
    }

    public Correio(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
