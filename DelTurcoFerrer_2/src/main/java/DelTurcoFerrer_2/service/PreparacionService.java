package DelTurcoFerrer_2.service;

import DelTurcoFerrer_2.dto.PreparacionDTO;
import DelTurcoFerrer_2.dto.PreparacionListadoDTO;
import DelTurcoFerrer_2.entities.Preparacion;
import java.time.LocalDate;
import java.util.List;

public interface PreparacionService {
    Preparacion crearPreparacion(PreparacionDTO dto);
    Preparacion actualizarPreparacion(Long id, PreparacionDTO dto);
    void eliminarPreparacion(Long id);
    List<PreparacionListadoDTO> listarTodasPreparaciones();
    List<PreparacionListadoDTO> buscarPreparacionesPorFecha(LocalDate fecha);
    PreparacionDTO obtenerPreparacionDTOPorId(Long id);
    int consultarStockDisponible(Long id);
    List<PreparacionListadoDTO> listarPreparacionesDisponibles();
}