package DelTurcoFerrer_2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "preparaciones")
public class Preparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de cocción no puede ser nula")
    @FutureOrPresent(message = "La fecha de cocción debe ser hoy o en el futuro")
    @Column(name = "fecha_coccion", nullable = false)
    private LocalDate fechaCoccion;

    @Min(value = 1, message = "Debe prepararse al menos 1 ración")
    @Column(name = "total_raciones", nullable = false)
    private int totalRacionesPreparadas;

    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name = "stock_restante", nullable = false)
    private int stockRacionesRestantes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receta_id", nullable = false, foreignKey = @ForeignKey(name = "fk_preparacion_receta"))
    private Receta receta;

    // Constructores
    public Preparacion() {
        // Constructor vacío necesario para JPA
    }

    public Preparacion(LocalDate fechaCoccion, int totalRacionesPreparadas, Receta receta) {
        this.fechaCoccion = fechaCoccion;
        this.totalRacionesPreparadas = totalRacionesPreparadas;
        this.stockRacionesRestantes = totalRacionesPreparadas; // Inicialmente el stock es igual al total
        this.receta = receta;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCoccion() {
        return fechaCoccion;
    }

    public void setFechaCoccion(LocalDate fechaCoccion) {
        this.fechaCoccion = fechaCoccion;
    }

    public int getTotalRacionesPreparadas() {
        return totalRacionesPreparadas;
    }

    public void setTotalRacionesPreparadas(int totalRacionesPreparadas) {
        this.totalRacionesPreparadas = totalRacionesPreparadas;
    }

    public int getStockRacionesRestantes() {
        return stockRacionesRestantes;
    }

    public void setStockRacionesRestantes(int stockRacionesRestantes) {
        this.stockRacionesRestantes = stockRacionesRestantes;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    // Métodos de negocio
    /**
     * Verifica si la preparación está disponible para consumo
     * @return true si hay stock y la fecha es válida
     */
    public boolean isDisponible() {
        return stockRacionesRestantes > 0 && !fechaCoccion.isBefore(LocalDate.now());
    }

    /**
     * Consume una cantidad específica de raciones
     * @param cantidad cantidad a consumir
     * @throws IllegalArgumentException si la cantidad no es positiva
     * @throws IllegalStateException si no hay suficiente stock
     */
    public void consumirRaciones(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a consumir debe ser positiva");
        }
        if (this.stockRacionesRestantes < cantidad) {
            throw new IllegalStateException("No hay suficiente stock disponible");
        }
        this.stockRacionesRestantes -= cantidad;
    }

    // Métodos sobreescritos
    @Override
    public String toString() {
        return "Preparacion{" +
                "id=" + id +
                ", fechaCoccion=" + fechaCoccion +
                ", totalRacionesPreparadas=" + totalRacionesPreparadas +
                ", stockRacionesRestantes=" + stockRacionesRestantes +
                ", receta=" + (receta != null ? receta.getId() : "null") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Preparacion)) return false;
        Preparacion that = (Preparacion) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}