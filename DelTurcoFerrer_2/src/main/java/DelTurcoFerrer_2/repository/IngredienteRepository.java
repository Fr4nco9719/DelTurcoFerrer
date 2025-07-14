package DelTurcoFerrer_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import DelTurcoFerrer_2.entities.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
	
}