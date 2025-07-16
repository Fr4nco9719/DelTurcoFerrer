package DelTurcoFerrer_2.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PreparacionDTO {
    private Long id; // Nuevo campo ID
    
    @NotNull(message = "La fecha de cocción no puede ser nula")
    @FutureOrPresent(message = "La fecha de cocción debe ser hoy o en el futuro")
    private LocalDate fechaCoccion;
    
    @Min(value = 1, message = "Debe prepararse al menos 1 ración")
    @Max(value = 1000, message = "No se pueden preparar más de 1000 raciones a la vez")
    private int totalRacionesPreparadas;
    
    @NotNull(message = "La receta es obligatoria")
    private Long recetaId;

    // Getters y Setters (incluyendo el nuevo campo)
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
    public String getFechaCoccionFormateada() {
        if (fechaCoccion == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaCoccion.format(formatter);
    }

    public int getTotalRacionesPreparadas() {
        return totalRacionesPreparadas;
    }

    public void setTotalRacionesPreparadas(int totalRacionesPreparadas) {
        this.totalRacionesPreparadas = totalRacionesPreparadas;
    }

    public Long getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Long recetaId) {
        this.recetaId = recetaId;
    }
}