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

import com.gft.palmirinha.entities.UnidadeMedida;
import com.gft.palmirinha.services.UnidadeMedidaService;

@Controller
@RequestMapping("um")
public class UnidadeMedidaController {
	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	@RequestMapping(path = "editar")
	public ModelAndView editarUnidadeDeMedida(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("um/form.html");
		UnidadeMedida unidadeMedida;
		if (id == null)
			unidadeMedida = new UnidadeMedida();
		else {
			try {
				unidadeMedida = unidadeMedidaService.obterUnidadeMedida(id);

			} catch (Exception e) {
				unidadeMedida = new UnidadeMedida();
				mv.addObject("mensagem", "Unidade de medida nao encontrada" + e.getMessage());
			}
		}
		mv.addObject("unidadeMedida", unidadeMedida);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarUnidadeMedida(@Valid UnidadeMedida unidadeMedida, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("um/form.html");
		boolean novo = true;
		if (unidadeMedida.getId() != null)
			novo = false;

		if (bindingResult.hasErrors()) {
			mv.addObject("unidadeMedida", unidadeMedida);
		}

		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida);

		if (novo) {
			mv.addObject("unidadeMedida", new UnidadeMedida());
		} else {
			mv.addObject("unidadeMedida", unidadeMedida);
		}

		mv.addObject("mensagem", "Unidade de medida salva com sucesso!");

		return mv;
	}

	@RequestMapping()
	public ModelAndView listarLinguagens(String nome) {
		ModelAndView mv = new ModelAndView("um/listar.html");
		mv.addObject("lista", unidadeMedidaService.listarUnidadesMedida(nome));
		mv.addObject("nome", nome);
		return mv;
	}
	
	/*
	 * @RequestMapping() public ModelAndView listarLinguagens() { ModelAndView mv =
	 * new ModelAndView("um/listar.html"); mv.addObject("lista",
	 * unidadeMedidaService.listarUnidadesMedida()); return mv; }
	 */

	@RequestMapping("/excluir")
	public ModelAndView excluirLinguagem(Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/um");
		try {
			unidadeMedidaService.excluirUnidadeDeMedida(id);
			redirectAttributes.addFlashAttribute("mensagem", "Unidade de medida excluida com sucesso");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir unidade de medida");
		}
		return mv;
	}
	
	@RequestMapping(path = "populate")
	public ModelAndView popularBanco() {
		ModelAndView mv = new ModelAndView("um/populate.html");
		return mv;
	}

}
