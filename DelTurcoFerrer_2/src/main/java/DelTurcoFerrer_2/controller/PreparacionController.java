package DelTurcoFerrer_2.controller;

import DelTurcoFerrer_2.dto.PreparacionDTO;
import DelTurcoFerrer_2.service.PreparacionService;
import DelTurcoFerrer_2.service.RecetaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/preparaciones")
public class PreparacionController {

    private static final String ATTRIBUTE_PREPARACIONES = "preparaciones";
    private static final String ATTRIBUTE_PREPARACION = "preparacion";
    private static final String REDIRECT_PREPARACIONES = "redirect:/preparaciones";
    private static final String VIEW_LISTADO = "preparaciones/listado";
    private static final String VIEW_FORMULARIO = "preparaciones/formulario";
    private static final String PARAM_FECHA = "fecha";

    private final PreparacionService preparacionService;

    private final RecetaService recetaService;

    public PreparacionController(PreparacionService preparacionService, RecetaService recetaService) {
        this.preparacionService = preparacionService;
        this.recetaService = recetaService;
    }


    @GetMapping
    public String listarPreparaciones(Model model) {
        model.addAttribute(ATTRIBUTE_PREPARACIONES, preparacionService.listarTodasPreparaciones());
        return VIEW_LISTADO;
    }

    @GetMapping("/disponibles")
    public String listarPreparacionesDisponibles(Model model) {
        model.addAttribute(ATTRIBUTE_PREPARACIONES, preparacionService.listarPreparacionesDisponibles());
        return VIEW_LISTADO;
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute(ATTRIBUTE_PREPARACION, new PreparacionDTO());
        model.addAttribute("recetas", recetaService.listarRecetas());
        return VIEW_FORMULARIO;
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        PreparacionDTO dto = preparacionService.obtenerPreparacionDTOPorId(id);
        model.addAttribute(ATTRIBUTE_PREPARACION, dto);
        model.addAttribute("recetas", recetaService.listarRecetas());
        return VIEW_FORMULARIO;
    }

    @PostMapping("/guardar")
    public String guardarPreparacion(@ModelAttribute(ATTRIBUTE_PREPARACION) PreparacionDTO dto,
                                    RedirectAttributes redirectAttributes) {
        try {
            if (dto.getId() == null) {
                preparacionService.crearPreparacion(dto);
                redirectAttributes.addFlashAttribute("success", "Preparación creada exitosamente");
            } else {
                preparacionService.actualizarPreparacion(dto.getId(), dto);
                redirectAttributes.addFlashAttribute("success", "Preparación actualizada exitosamente");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la preparación: " + e.getMessage());
            return dto.getId() == null ? "redirect:/preparaciones/nueva" : "redirect:/preparaciones/editar/" + dto.getId();
        }
        return REDIRECT_PREPARACIONES;
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPreparacion(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            preparacionService.eliminarPreparacion(id);
            redirectAttributes.addFlashAttribute("success", "Preparación eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la preparación: " + e.getMessage());
        }
        return REDIRECT_PREPARACIONES;
    }

    @GetMapping("/buscar")
    public String buscarPorFecha(@RequestParam(PARAM_FECHA) LocalDate fecha, Model model) {
        model.addAttribute(ATTRIBUTE_PREPARACIONES, preparacionService.buscarPreparacionesPorFecha(fecha));
        return VIEW_LISTADO;
    }

    @GetMapping("/stock/{id}")
    @ResponseBody
    public String consultarStock(@PathVariable Long id) {
        int stock = preparacionService.consultarStockDisponible(id);
        return String.format("Stock disponible: %d raciones", stock);
    }
}