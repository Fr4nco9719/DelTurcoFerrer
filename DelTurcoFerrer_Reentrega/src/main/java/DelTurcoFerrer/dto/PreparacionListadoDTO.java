package DelTurcoFerrer.dto;
import java.time.LocalDate;

public class PreparacionListadoDTO {
    private Long id;
    private String nombreReceta;
    private LocalDate fechaCoccion;
    private int totalRacionesPreparadas;
    private int stockRacionesRestantes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
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
}
