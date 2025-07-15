package DelTurcoFerrer_2.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import DelTurcoFerrer_2.dto.RecetaDTO;
import DelTurcoFerrer_2.dto.RecetaListadoDTO;
import DelTurcoFerrer_2.entities.Receta;
import DelTurcoFerrer_2.service.RecetaService;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @PostMapping
    public Receta crearReceta(@RequestBody RecetaDTO recetaDTO) {
        return recetaService.saveReceta(recetaDTO);
    }

    @GetMapping
    public List<RecetaListadoDTO> listarRecetas(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer minCalorias,
            @RequestParam(required = false) Integer maxCalorias
    ) {
        return recetaService.getReceta(nombre, minCalorias, maxCalorias);
    }

    @DeleteMapping("/{id}")
    public Receta eliminarReceta(@PathVariable Long id) {
        return recetaService.deleteReceta(id);
    }

    @PutMapping("/{id}")
    public Receta modificarReceta(@PathVariable Long id, @RequestBody RecetaDTO recetaDTO) {
        return recetaService.updateReceta(recetaDTO, id);
    }
}