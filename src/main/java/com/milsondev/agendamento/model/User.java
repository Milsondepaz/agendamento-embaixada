/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milsondev.agendamento.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;


/**
 *
 * @author Mein
 */
@Entity(name="Usuario")
public class User {     

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank(message = "Primeiro nome é obrigatória")
    private String primeiroNome;
    
    private String segundoNome;
    
    @NotBlank(message = "E-mail nome é obrigatória")
    private String email;
    
    @NotBlank(message = "Numero de Telefone é obrigatória")
    private String numeroTelefone;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "A Senha deve ter no minimo 8 caracteres")
    private String password;
   

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSegundoNome() {
        return segundoNome;
    }

    public void setSegundoNome(String segundoNome) {
        this.segundoNome = segundoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (int) (this.codigo ^ (this.codigo >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "codigo=" + codigo + ", primeiroNome=" + primeiroNome + ", segundoNome=" + segundoNome + ", email=" + email + ", numeroTelefone=" + numeroTelefone + ", senha=" + password + '}';
    }
    
    
    public void sendPassword(User user){
        System.out.println("a senha " + user.getPassword()+ " foi enviada para o "+ user.getEmail());
    }
    
       

}
