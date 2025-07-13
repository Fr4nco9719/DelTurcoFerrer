package DelTurcoFerrer.controller;

import DelTurcoFerrer.service.PreparacionService;
import DelTurcoFerrer.dto.PreparacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/preparaciones")
public class PreparacionController {

    private static final String FECHA2 = "fecha";
	@Autowired
    private PreparacionService preparacionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("preparaciones", preparacionService.listarTodas());
        return "preparaciones/listado";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("preparacion", new PreparacionDTO());
        return "preparaciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("preparacion") PreparacionDTO dto) {
        preparacionService.registrarPreparacion(dto);
        return "redirect:/preparaciones";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        preparacionService.eliminar(id);
        return "redirect:/preparaciones";
    }

    @PostMapping("/modificar-fecha/{id}")
    public String modificarFecha(@PathVariable Long id, @RequestParam(FECHA2) String fecha) {
        preparacionService.modificarFecha(id, fecha);
        return "redirect:/preparaciones";
    }
}
