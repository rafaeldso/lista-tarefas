package br.com.rafaeldso.tarefas.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rafaeldso.tarefas.dao.JdbcTarefaDao;
import br.com.rafaeldso.tarefas.modelo.Tarefa;

@Controller		
public class TarefasController {
	
	@RequestMapping("/novaTarefa")
	public ModelAndView form(Model model) {
		//Tarefa tarefa = new Tarefa();
		//model.addAttribute("tarefa", tarefa);
		return new ModelAndView("formulario", "tarefa", new Tarefa());
	}
	@RequestMapping(value = "/adicionaTarefa", method = RequestMethod.POST)
	public String adiciona(@ModelAttribute("tarefa") Tarefa tarefa) {
		JdbcTarefaDao dao = new JdbcTarefaDao();
		if(tarefa==null) System.out.println("Tarefa é igual a null");
		System.out.println("Tarefa: "+tarefa.getDescricao());
		tarefa.setDataFinalizacao(Calendar.getInstance());
		dao.adiciona(tarefa);
		return "tarefa/adicionada";
	}
}
