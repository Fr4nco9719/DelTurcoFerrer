package DelTurcoFerrer_2.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DelTurcoFerrer_2.dto.IngredienteDTO;
import DelTurcoFerrer_2.dto.RecetaDTO;
import DelTurcoFerrer_2.dto.RecetaListadoDTO;
import DelTurcoFerrer_2.entities.Ingrediente;
import DelTurcoFerrer_2.entities.ItemReceta;
import DelTurcoFerrer_2.entities.Receta;
import DelTurcoFerrer_2.repository.IngredienteRepository;
import DelTurcoFerrer_2.repository.RecetaRepository;

@Service
public class RecetaServiceImpl implements RecetaService {
	@Autowired
	private RecetaRepository recetaRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Override
	public Receta updateReceta(RecetaDTO recetaDTO, Long id) {
		Receta receta = recetaRepository.findByIdAndActivaTrue(id)
				.orElseThrow(()->new RuntimeException("Receta no encontrada"));
		
		receta.setDescripcion(recetaDTO.getDescripcion());
		List<ItemReceta> existentes = receta.getIngredientes().stream()
				.filter(ItemReceta::isActive)
				.toList();
		for(ItemReceta ir : existentes) {
			ir.setActive(false);
		}
		
		List<ItemReceta> nuevos = new ArrayList<>();
		for (IngredienteDTO irDTO : recetaDTO.getIngredientes()) {
			Ingrediente ingrediente = ingredienteRepository.findById(irDTO.getId())
					.orElseThrow(()-> new RuntimeException("Ingrediente no encontrado: " + irDTO.getId()));
			ItemReceta nuevo = new ItemReceta();
			nuevo.setIngrediente(ingrediente);
			nuevo.setCantidad(irDTO.getCantidadUtilizada());
			nuevo.setCalorias(irDTO.getCalorias());
			nuevo.setActive(true);
			nuevo.setReceta(receta);
			nuevos.add(nuevo);
		}
		
		receta.getIngredientes().addAll(nuevos);
		return recetaRepository.save(receta);
	}

	@Override
	public Receta saveReceta(RecetaDTO recetaDTO) {
		if(recetaRepository.findByNombre(recetaDTO.getNombre()).isPresent()) {
			throw new IllegalArgumentException("Ya existe una receta con ese nombre");
		}
		
		Receta receta = new Receta();
		receta.setNombre(recetaDTO.getNombre());
		receta.setDescripcion(recetaDTO.getDescripcion());
	    receta.setRacionesPorPreparacion(recetaDTO.getRacionesPorPreparacion());

		Set<ItemReceta> ingredientes = new HashSet<>();
		
		for (IngredienteDTO irDTO : recetaDTO.getIngredientes()) {
			Ingrediente ingrediente = ingredienteRepository.findById(irDTO.getIngredienteId())
					.orElseThrow(()-> new RuntimeException("Ingrediente no encontrado: "+irDTO.getIngredienteId()));
			ItemReceta ir = new ItemReceta();
			ir.setIngrediente(ingrediente);
			ir.setCantidad(irDTO.getCantidadUtilizada());
			ir.setCalorias(irDTO.getCalorias());
			ir.setReceta(receta);
			
			ingredientes.add(ir);
		}
		receta.getIngredientes().addAll(ingredientes);

		return recetaRepository.save(receta);
	}

	@Override
	public List<RecetaListadoDTO> getReceta(String nombre, Integer minCalorias, Integer maxCalorias) {
		List<Receta> recetas = recetaRepository.findAll()
		        .stream()
		        .filter(r -> r.isActiva())
		        .filter(r -> nombre == null || r.getNombre().toLowerCase().contains(nombre.toLowerCase()))
		        .toList();

		    List<RecetaListadoDTO> resultado = new ArrayList<>();

		    for (Receta receta : recetas) {
		        int totalCalorias = receta.getIngredientes().stream()
		            .mapToInt(ItemReceta::getCalorias)
		            .sum();

		        if ((minCalorias == null || totalCalorias >= minCalorias) &&
		            (maxCalorias == null || totalCalorias <= maxCalorias)) {
		            
		            resultado.add(new RecetaListadoDTO(receta.getNombre(), totalCalorias, nombre)); //*******
		        }
		    }
		return resultado;
	}

	@Override
	public Receta deleteReceta(Long id) {
	    Receta receta = recetaRepository.findByIdAndActivaTrue(id)
	            .orElseThrow(() -> new RuntimeException("Receta no encontrada o ya eliminada"));

	    receta.setActiva(false);
	    return recetaRepository.save(receta);
	}

	@Override
	public List<Receta> listarRecetas() {
		return recetaRepository.findAll()
		        .stream()
		        .filter(r -> r.isActiva())
		        .toList();
	}

}