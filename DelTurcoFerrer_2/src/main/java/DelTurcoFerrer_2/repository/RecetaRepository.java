package DelTurcoFerrer_2.repository;

import DelTurcoFerrer_2.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    
	   Optional<Receta> findByNombre(String nombre);
	   boolean existsByNombre(String nombre);
	   Optional<Receta> findByIdAndActivaTrue(Long id);
    
    @Query("SELECT r FROM Receta r WHERE LOWER(r.nombre) LIKE LOWER(concat('%', :nombre, '%'))")
    List<Receta> buscarPorNombreConteniendo(@Param("nombre") String nombre);
    
    @Query("SELECT r FROM Receta r WHERE r.racionesPorPreparacion >= :minRaciones")
    List<Receta> buscarPorMinimoRaciones(@Param("minRaciones") int minRaciones);
    
    @Query("SELECT r FROM Receta r JOIN r.preparaciones p WHERE p.fechaCoccion = CURRENT_DATE")
    List<Receta> buscarRecetasPreparadasHoy();
}