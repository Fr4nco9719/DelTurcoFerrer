package DelTurcoFerrer.service;

import DelTurcoFerrer.entities.Preparacion;
import DelTurcoFerrer.entities.Receta;
import DelTurcoFerrer.repository.PreparacionRepository;
import DelTurcoFerrer.repository.RecetaRepository;
import DelTurcoFerrer.dto.PreparacionDTO;
import DelTurcoFerrer.dto.PreparacionListadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreparacionServiceImpl implements PreparacionService {

    @Autowired
    private PreparacionRepository preparacionRepo;

    @Autowired
    private RecetaRepository recetaRepo;

    @Override
    public void registrarPreparacion(PreparacionDTO dto) {
        if (preparacionRepo.existsByFechaCoccionAndRecetaId(dto.getFechaCoccion(), dto.getRecetaId())) {
            throw new RuntimeException("Ya existe una preparación con esa receta y fecha.");
        }

        Receta receta = recetaRepo.findById(dto.getRecetaId())
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        Preparacion p = new Preparacion();
        p.setFechaCoccion(dto.getFechaCoccion());
        p.setTotalRacionesPreparadas(dto.getTotalRacionesPreparadas());
        p.setStockRacionesRestantes(dto.getTotalRacionesPreparadas());
        p.setReceta(receta);

        preparacionRepo.save(p);
    }

    @Override
    public void modificarFecha(Long id, String nuevaFecha) {
        Preparacion p = preparacionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Preparación no encontrada"));
        p.setFechaCoccion(LocalDate.parse(nuevaFecha));
        preparacionRepo.save(p);
    }

    @Override
    public void eliminar(Long id) {
        preparacionRepo.deleteById(id);
    }

    @Override
    public List<PreparacionListadoDTO> listarTodas() {
        return preparacionRepo.findAll().stream().map(p -> {
            PreparacionListadoDTO dto = new PreparacionListadoDTO();
            dto.setId(p.getId());
            dto.setFechaCoccion(p.getFechaCoccion());
            dto.setTotalRacionesPreparadas(p.getTotalRacionesPreparadas());
            dto.setStockRacionesRestantes(p.getStockRacionesRestantes());
            dto.setNombreReceta(p.getReceta() != null ? p.getReceta().getNombre() : "Sin receta");
            return dto;
        }).collect(Collectors.toList());
    }
}
