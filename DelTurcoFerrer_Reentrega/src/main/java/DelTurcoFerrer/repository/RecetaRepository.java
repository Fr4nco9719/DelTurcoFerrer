package DelTurcoFerrer.repository;

import DelTurcoFerrer.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
   Optional<Receta> findByNombre(String nombre);
   boolean existsByNombre(String nombre);
   Optional<Receta> findByIdAndActiveTrue(Long id);

}
