package DelTurcoFerrer_2.service;

import java.util.List;

import DelTurcoFerrer_2.dto.RecetaDTO;
import DelTurcoFerrer_2.dto.RecetaListadoDTO;
import DelTurcoFerrer_2.entities.Receta;


public interface RecetaService {
	Receta updateReceta(RecetaDTO recetaDTO, Long id);
	Receta saveReceta(RecetaDTO recetaDTO);
	List<RecetaListadoDTO> getReceta(String nombre, Integer minCalorias,Integer maxCalorias);
	List<Receta> listarRecetas();
	Receta deleteReceta(Long id);
}
