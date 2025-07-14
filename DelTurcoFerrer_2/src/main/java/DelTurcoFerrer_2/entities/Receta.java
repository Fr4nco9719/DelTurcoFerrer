package DelTurcoFerrer_2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la receta es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;

    @Min(value = 1, message = "Debe tener al menos 1 ración")
    @Column(name = "raciones_por_preparacion", nullable = false)
    private int racionesPorPreparacion;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Preparacion> preparaciones = new ArrayList<>();

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getRacionesPorPreparacion() {
        return racionesPorPreparacion;
    }

    public void setRacionesPorPreparacion(int racionesPorPreparacion) {
        this.racionesPorPreparacion = racionesPorPreparacion;
    }

    public List<Preparacion> getPreparaciones() {
        return Collections.unmodifiableList(preparaciones);
    }

    public void addPreparacion(Preparacion preparacion) {
        preparaciones.add(preparacion);
        preparacion.setReceta(this);
    }

    public void removePreparacion(Preparacion preparacion) {
        preparaciones.remove(preparacion);
        preparacion.setReceta(null);
    }
}