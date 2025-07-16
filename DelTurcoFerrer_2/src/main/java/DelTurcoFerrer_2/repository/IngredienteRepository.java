package DelTurcoFerrer_2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import DelTurcoFerrer_2.entities.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
	Optional<Ingrediente> findByNombreIgnoreCase(String nombre);

}