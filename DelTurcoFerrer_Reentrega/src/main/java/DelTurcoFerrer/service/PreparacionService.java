package DelTurcoFerrer.service;

import DelTurcoFerrer.dto.PreparacionDTO;
import DelTurcoFerrer.dto.PreparacionListadoDTO;

import java.util.List;

public interface PreparacionService {
    void registrarPreparacion(PreparacionDTO dto);
    void modificarFecha(Long id, String nuevaFecha);
    void eliminar(Long id);
    List<PreparacionListadoDTO> listarTodas();
}
