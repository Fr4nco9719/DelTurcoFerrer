package DelTurcoFerrer_2.repository;

import DelTurcoFerrer_2.entities.Preparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PreparacionRepository extends JpaRepository<Preparacion, Long> {
    
    // Verifica si existe una preparación con la misma fecha y receta
    boolean existsByFechaCoccionAndRecetaId(LocalDate fechaCoccion, Long recetaId);
    
    // Verifica si existe otra preparación (excluyendo una con ID específico) con misma fecha y receta
    boolean existsByFechaCoccionAndRecetaIdAndIdNot(LocalDate fechaCoccion, Long recetaId, Long id);
    
    // Obtiene todas las preparaciones ordenadas por fecha descendente
    List<Preparacion> findAllByOrderByFechaCoccionDesc();
    
    // Busca preparaciones por fecha específica
    List<Preparacion> findByFechaCoccion(LocalDate fecha);
    
    // Consulta solo el stock disponible de una preparación
    @Query("SELECT p.stockRacionesRestantes FROM Preparacion p WHERE p.id = :id")
    Integer findStockById(@Param("id") Long id);
    
    // Obtiene preparaciones disponibles (con stock y fecha válida)
    @Query("SELECT p FROM Preparacion p WHERE p.stockRacionesRestantes > 0 AND p.fechaCoccion >= CURRENT_DATE ORDER BY p.fechaCoccion ASC")
    List<Preparacion> findPreparacionesDisponibles();
}