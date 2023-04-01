package com.gft.palmirinha.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.palmirinha.entities.Receita;
import com.gft.palmirinha.services.ReceitaService;

@Controller
@RequestMapping("receita")
public class ReceitaController {
	@Autowired
	ReceitaService receitaService;

	@RequestMapping(path = "editar")
	public ModelAndView editarReceita(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("receita/form.html");
		Receita receita;
		String itensString;
		if (id == null) {
			receita = new Receita();
			itensString = new String();
		} else {
			try {
				receita = receitaService.obterReceita(id);
				itensString = receitaService.criaStringItens(receita.getItens());
			} catch (Exception e) {
				receita = new Receita();
				itensString = new String();
				mv.addObject("mensagem", "Receita não encontrada" + e.getMessage());
			}
		}

		mv.addObject("receita", receita);
		mv.addObject("itensString", itensString);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarReceita(@Valid Receita receita, String itensString, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("receita/form.html");
		boolean novo = true;

		if (receita.getId() != null)
			novo = false;

		if (bindingResult.hasErrors())
			mv.addObject("receita", receita);

		/*
		 * if (itensString == null) { System.out.println("String 'Salvar'vazia "); }
		 * else { System.out.println("String 'Salvar': " + itensString); }
		 */
		receitaService.salvarReceita(receita);
		try {
			receitaService.criaRelacaoItem(receita, itensString);
		} catch (Exception e) {
			System.out.println("Erro ao criar relacao com item");
		}
		if (novo) {
			mv.addObject("receita", new Receita());
		} else {
			mv.addObject("receita", receita);
			
		}

		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarReceitas(String nome, String ingredienteNome) {
		ModelAndView mv = new ModelAndView("receita/listar.html");
		
		mv.addObject("lista", receitaService.listarReceitas(nome, ingredienteNome));
		mv.addObject("ingredienteNome", ingredienteNome);
		mv.addObject("nome",nome);
		return mv;
	}

	/*
	 * @RequestMapping public ModelAndView listarReceitas() { ModelAndView mv = new
	 * ModelAndView("receita/listar.html"); mv.addObject("lista",
	 * receitaService.listarReceitas()); return mv;
	 * 
	 * }
	 */
	@RequestMapping(path = "detalhes")
	public ModelAndView paginaReceita(Long id) {
		ModelAndView mv = new ModelAndView("receita/detalhes.html");
		Receita receita;
		try {
			receita = receitaService.obterReceita(id);
		} catch (Exception e) {
			receita = new Receita();
			mv.addObject("mensagem", "Receita não encontrada: " + e.getMessage());
		}
		
		mv.addObject("receita", receita);
		mv.addObject("itensString", receitaService.criaStringItens(receita.getItens()));
		return mv;
	}

	@RequestMapping(path = "excluir")
	public ModelAndView excluirReceita(Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/receita");
		try {
			receitaService.excluirReceita(id);
			redirectAttributes.addFlashAttribute("mensagem", "Receita excluída com sucesso");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir receita" + e.getMessage());
		}

		return mv;

	}

}
