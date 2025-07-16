package DelTurcoFerrer_2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DelTurcoFerrer_2.entities.Ingrediente;
import DelTurcoFerrer_2.repository.IngredienteRepository;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public Ingrediente guardar(Ingrediente ingrediente) {
        Optional<Ingrediente> existente = ingredienteRepository.findByNombreIgnoreCase(ingrediente.getNombre());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un ingrediente con ese nombre");
        }
        return ingredienteRepository.save(ingrediente);
    }

    public List<Ingrediente> listarTodos() {
        return ingredienteRepository.findAll();
    }
}
