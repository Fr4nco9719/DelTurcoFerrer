package DelTurcoFerrer.repository;

import DelTurcoFerrer.entities.Preparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PreparacionRepository extends JpaRepository<Preparacion, Long> {
    List<Preparacion> findByFechaCoccion(LocalDate fechaCoccion);
    boolean existsByFechaCoccionAndRecetaId(LocalDate fechaCoccion, Long recetaId);
}
