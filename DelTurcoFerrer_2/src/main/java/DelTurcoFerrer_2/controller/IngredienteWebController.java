package DelTurcoFerrer_2.controller;


import DelTurcoFerrer_2.entities.Ingrediente;
import DelTurcoFerrer_2.repository.IngredienteRepository;
import DelTurcoFerrer_2.service.IngredienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteWebController {

    @Autowired
    private IngredienteRepository ingredienteRepository;
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public String listarIngredientes(Model model) {
        model.addAttribute("ingredientes", ingredienteRepository.findAll());
        return "ingredientes/listado"; 
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoIngrediente(Model model) {
        model.addAttribute("ingrediente", new Ingrediente());
        return "ingredientes/formulario"; 
    }


    @PostMapping("/guardar")
    public String guardarIngrediente(@ModelAttribute Ingrediente ingrediente, Model model) {
        try {
            ingredienteService.guardar(ingrediente);
            return "redirect:/ingredientes";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "ingredientes/formulario";
        }
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
        model.addAttribute("ingrediente", ingrediente);
        return "ingredientes/formulario";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarIngrediente(@PathVariable Long id) {
        ingredienteRepository.deleteById(id);
        return "redirect:/ingredientes";
    }
}
