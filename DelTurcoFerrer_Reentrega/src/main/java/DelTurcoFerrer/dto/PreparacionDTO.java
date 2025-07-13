package DelTurcoFerrer.dto;

import java.time.LocalDate;

public class PreparacionDTO {
    private LocalDate fechaCoccion;
    private int totalRacionesPreparadas;
    private Long recetaId;

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

    public Long getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Long recetaId) {
        this.recetaId = recetaId;
    }
}
