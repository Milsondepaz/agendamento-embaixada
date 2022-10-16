/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milsondev.agendamento.controller;

import com.milsondev.agendamento.model.Agenda;
import com.milsondev.agendamento.model.Correio;
import com.milsondev.agendamento.model.Servico;
import com.milsondev.agendamento.model.User;
import com.milsondev.agendamento.service.UserService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Mein
 */
@Controller
@RequestMapping
public class MainController {

    @Autowired
    private UserService userService;

    private final User user = new User();

    boolean userLogado = false;

    String userName = "";

    ArrayList<Servico> servicos = new ArrayList();

    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(ArrayList<Servico> servicos) {
        this.servicos = servicos;
    }

    @RequestMapping("/")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("index");
        //mv.addObject(new Titulo());

        if (userLogado) {
            mv.addObject("userName", userName);
            return mv;

        }

        return new ModelAndView("login");
    }

    @RequestMapping("/registrar")
    public ModelAndView registrar() {
        ModelAndView mv = new ModelAndView("registrar");
        return mv;
    }

    @RequestMapping(value = "/send-agendamento", method = RequestMethod.POST)
    public String sendAgendamento(@ModelAttribute("agenda") Agenda agenda) {
        // verificar se ainda nao exiti um usuario com mesmo e-mail e senha, antes de salva no Banco

        System.out.println("ola passou aqi");

        System.out.println(agenda.getDataDiaMesAno());

        ///userService.salvar(user);
        return "agendar";
    }

    // area restrista se n estiver logado
    @RequestMapping("/agendar")
    public ModelAndView agendar() {
        ModelAndView mv = new ModelAndView("agendar");  //agendar     --- testeee
        Agenda agenda = new Agenda();
        mv.addObject("agenda", agenda);
        mv.addObject("userName", userName);
        mv.addObject("servicos", servicos);

        return mv;
    }

    @RequestMapping(value = "/teste", method = RequestMethod.POST)
    public String save(Model model) {
        System.out.println("ola mundo");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("user") User user) {
        User newUser = userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
        ModelAndView mv = new ModelAndView("index");
        if (newUser != null) {
            userName = newUser.getPrimeiroNome();
            userLogado = true;
            mv.addObject("userName", userName);
            // mv.addObject("fruitList", fruitList);
            return mv;
        }
        user = null;
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String registrar(@ModelAttribute("user") User user) {
        // verificar se ainda nao exiti um usuario com mesmo e-mail e senha, antes de salva no Banco
        userService.salvar(user);
        return "login";
    }

    @RequestMapping("/sair")
    public ModelAndView sair() {
        ModelAndView mv = new ModelAndView("login");
        userLogado = false;
        return mv;
    }

    @RequestMapping("/agenda")
    public ModelAndView verAgenda() {
        ModelAndView mv = new ModelAndView("agenda");

        mv.addObject("userName", userName);
        

        return mv;
    }

    ////////////////////////////////------------------email----------------------------------///////////////////////////////
    @RequestMapping("/recuperarsenha")
    public ModelAndView recuperar() {
        ModelAndView mv = new ModelAndView("recuperarsenha");
        mv.addObject("correio", new Correio());
        return mv;
    }

    @PostMapping(value = "/sendpassword")
    public ModelAndView sendPassword(@Valid Correio correio, Errors errors, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("recuperarsenha");
        if (errors.hasErrors()) {
            return mv;
        }

        User user = userService.getUserByEmail(correio.getEmail());
        Optional<User> opt = Optional.ofNullable(user);
        if (opt.isPresent()) {
            user.sendPassword(user);
            mv.addObject("mensagem", "Enviamos a sua senha para o seu e-mail, clique aqui para");
            return mv;
        }

        mv.addObject("mensagememailnaoencontrado", "Seu e-mail nao foi encontrado na base de dados. ");
        return mv;
    }

    /*
    @PostMapping
	public String register(@Valid Person person, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Registration not successful...");
			return "register";
		} else {
			model.addAttribute("message", "Registration sucessfully...");
			return "register";
		}
	}
     */
    //////////////////////////////////-------------- perfil ------------------------ ///////////////////////////
    @RequestMapping(value = "/perfil", method = RequestMethod.GET)
    public ModelAndView verPerfil() {
        ModelAndView mv = new ModelAndView("perfil");

        mv.addObject("userName", userName);

        return mv;
    }

}
