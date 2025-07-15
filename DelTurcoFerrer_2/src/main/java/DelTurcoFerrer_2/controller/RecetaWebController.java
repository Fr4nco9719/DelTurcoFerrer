package DelTurcoFerrer_2.controller;

import DelTurcoFerrer_2.dto.RecetaDTO;
import DelTurcoFerrer_2.dto.RecetaListadoDTO;
import DelTurcoFerrer_2.dto.IngredienteDTO;
import DelTurcoFerrer_2.entities.Receta;
import DelTurcoFerrer_2.entities.ItemReceta;
import DelTurcoFerrer_2.repository.IngredienteRepository;
import DelTurcoFerrer_2.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recetas")
public class RecetaWebController {

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    private static final String VIEW_LISTADO = "recetas/listado";
    private static final String VIEW_FORMULARIO = "recetas/formulario";
    private static final String REDIRECT_LISTADO = "redirect:/recetas";

    @GetMapping
    public String listarRecetas(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer minCalorias,
            @RequestParam(required = false) Integer maxCalorias,
            Model model) {
        List<RecetaListadoDTO> recetas = recetaService.getReceta(nombre, minCalorias, maxCalorias);
        model.addAttribute("recetas", recetas);
        return VIEW_LISTADO;
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        RecetaDTO recetaDTO = new RecetaDTO();
        recetaDTO.setIngredientes(new ArrayList<>());  
        model.addAttribute("receta", recetaDTO);
        model.addAttribute("ingredientesDisponibles", ingredienteRepository.findAll());
        return VIEW_FORMULARIO;
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Receta receta = recetaService.listarRecetas()
                .stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        RecetaDTO dto = new RecetaDTO();
        dto.setNombre(receta.getNombre());
        dto.setDescripcion(receta.getDescripcion());
        dto.setRacionesPorPreparacion(receta.getRacionesPorPreparacion());
        if(dto.getIngredientes() == null) {
            dto.setIngredientes(new ArrayList<>());
        }

        List<IngredienteDTO> ingredientesDTO = receta.getIngredientes().stream()
                .filter(ItemReceta::isActive)
                .map(ir -> {
                    IngredienteDTO i = new IngredienteDTO();
                    i.setIngredienteId(ir.getIngrediente().getId());
                    i.setCantidadUtilizada(ir.getCantidad());
                    i.setCalorias(ir.getCalorias());
                    return i;
                })
                .toList();

        dto.setIngredientes(ingredientesDTO);

        model.addAttribute("receta", dto);
        model.addAttribute("ingredientesDisponibles", ingredienteRepository.findAll());
        return VIEW_FORMULARIO;
    }

    @PostMapping("/guardar")
    public String guardarReceta(@ModelAttribute("receta") RecetaDTO recetaDTO) {
        if (recetaDTO.getNombre() == null) {
            return VIEW_FORMULARIO;
        }

        if (recetaDTO.getIngredientes() == null) {
            recetaDTO.setIngredientes(new ArrayList<>());
        }

        recetaService.saveReceta(recetaDTO);
        return REDIRECT_LISTADO;
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarReceta(@PathVariable Long id) {
        recetaService.deleteReceta(id);
        return REDIRECT_LISTADO;
    }
}

