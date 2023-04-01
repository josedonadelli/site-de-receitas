package com.gft.palmirinha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.palmirinha.services.PrincipalService;
import com.gft.palmirinha.services.ReceitaService;

@Controller
public class PrincipalController {
	@Autowired
	private PrincipalService principalService;
	@Autowired
	ReceitaService receitaService;
	
	@RequestMapping(method = RequestMethod.GET, path = "entrar")
	public ModelAndView entrar() {
		ModelAndView mv = new ModelAndView("entrar.html");
		return mv;
	}
	@RequestMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index.html");
		if (receitaService.verificaBancoVazio())
			mv.addObject("populado", "0");
		else
			mv.addObject("populado", "1");
			return mv;
	}

	@RequestMapping(path = "popular")
	public ModelAndView popularBanco(RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:");

		principalService.popular();
		redirectAttributes.addFlashAttribute("mensagem", "Banco populado com sucesso");
		//redirectAttributes.addAttribute("populado", "1");

		return mv;
	}
}
