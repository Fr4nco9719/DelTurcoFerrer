package DelTurcoFerrer_2.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DelTurcoFerrer_2.entities.Ingrediente;
import DelTurcoFerrer_2.repository.IngredienteRepository;


@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@PostMapping
	public Ingrediente crear(@RequestBody Ingrediente ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}
	
	@GetMapping
	public List<Ingrediente> listar(){
		return ingredienteRepository.findAll();
	}
}
