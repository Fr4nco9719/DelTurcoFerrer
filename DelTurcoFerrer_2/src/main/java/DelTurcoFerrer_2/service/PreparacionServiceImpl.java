package DelTurcoFerrer_2.service;

import DelTurcoFerrer_2.entities.Preparacion;
import DelTurcoFerrer_2.entities.Receta;
import DelTurcoFerrer_2.repository.PreparacionRepository;
import DelTurcoFerrer_2.repository.RecetaRepository;
import DelTurcoFerrer_2.dto.PreparacionDTO;
import DelTurcoFerrer_2.dto.PreparacionListadoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PreparacionServiceImpl implements PreparacionService {

    private final PreparacionRepository preparacionRepository;
    private final RecetaRepository recetaRepository;

    public PreparacionServiceImpl(PreparacionRepository preparacionRepository, 
                                RecetaRepository recetaRepository) {
        this.preparacionRepository = preparacionRepository;
        this.recetaRepository = recetaRepository;
    }

    @Override
    public Preparacion crearPreparacion(PreparacionDTO dto) {
        validarPreparacion(dto);
        
        Receta receta = recetaRepository.findById(dto.getRecetaId())
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada con ID: " + dto.getRecetaId()));
        
        Preparacion preparacion = new Preparacion();
        preparacion.setFechaCoccion(dto.getFechaCoccion());
        preparacion.setTotalRacionesPreparadas(dto.getTotalRacionesPreparadas());
        preparacion.setStockRacionesRestantes(dto.getTotalRacionesPreparadas());
        preparacion.setReceta(receta);
        
        return preparacionRepository.save(preparacion);
    }

    @Override
    public Preparacion actualizarPreparacion(Long id, PreparacionDTO dto) {
        Preparacion preparacion = preparacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Preparación no encontrada con ID: " + id));
        
        validarPreparacion(dto, id);
        
        preparacion.setFechaCoccion(dto.getFechaCoccion());
        preparacion.setTotalRacionesPreparadas(dto.getTotalRacionesPreparadas());
        
        if (!preparacion.getReceta().getId().equals(dto.getRecetaId())) {
            Receta receta = recetaRepository.findById(dto.getRecetaId())
                    .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada con ID: " + dto.getRecetaId()));
            preparacion.setReceta(receta);
        }
        
        return preparacionRepository.save(preparacion);
    }

    @Override
    public void eliminarPreparacion(Long id) {
        Preparacion preparacion = preparacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Preparación no encontrada con ID: " + id));
        preparacionRepository.delete(preparacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PreparacionListadoDTO> listarTodasPreparaciones() {
        return preparacionRepository.findAllByOrderByFechaCoccionDesc().stream()
                .map(this::convertirAListadoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PreparacionListadoDTO> buscarPreparacionesPorFecha(LocalDate fecha) {
        return preparacionRepository.findByFechaCoccion(fecha).stream()
                .map(this::convertirAListadoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PreparacionDTO obtenerPreparacionDTOPorId(Long id) {
        return preparacionRepository.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(() -> new IllegalArgumentException("Preparación no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public int consultarStockDisponible(Long id) {
        return preparacionRepository.findById(id)
                .map(Preparacion::getStockRacionesRestantes)
                .orElseThrow(() -> new IllegalArgumentException("Preparación no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PreparacionListadoDTO> listarPreparacionesDisponibles() {
        return preparacionRepository.findPreparacionesDisponibles().stream()
                .map(this::convertirAListadoDTO)
                .collect(Collectors.toList());
    }

    // Métodos auxiliares privados
    private void validarPreparacion(PreparacionDTO dto) {
        if (preparacionRepository.existsByFechaCoccionAndRecetaId(dto.getFechaCoccion(), dto.getRecetaId())) {
            throw new IllegalStateException("Ya existe una preparación para esta receta en la fecha especificada");
        }
    }

    private void validarPreparacion(PreparacionDTO dto, Long idExcluir) {
        if (preparacionRepository.existsByFechaCoccionAndRecetaIdAndIdNot(dto.getFechaCoccion(), dto.getRecetaId(), idExcluir)) {
            throw new IllegalStateException("Ya existe otra preparación para esta receta en la fecha especificada");
        }
    }

    private PreparacionDTO convertirADTO(Preparacion preparacion) {
        PreparacionDTO dto = new PreparacionDTO();
        dto.setFechaCoccion(preparacion.getFechaCoccion());
        dto.setTotalRacionesPreparadas(preparacion.getTotalRacionesPreparadas());
        dto.setRecetaId(preparacion.getReceta().getId());
        return dto;
    }

    private PreparacionListadoDTO convertirAListadoDTO(Preparacion preparacion) {
        PreparacionListadoDTO dto = new PreparacionListadoDTO();
        dto.setId(preparacion.getId());
        dto.setFechaCoccion(preparacion.getFechaCoccion());
        dto.setTotalRacionesPreparadas(preparacion.getTotalRacionesPreparadas());
        dto.setStockRacionesRestantes(preparacion.getStockRacionesRestantes());
        dto.setNombreReceta(preparacion.getReceta().getNombre());
        dto.setDisponible(preparacion.isDisponible());
        return dto;
    }
}