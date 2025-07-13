package DelTurcoFerrer.dto;

public class IngredientePreparacionDTO {

    private Long id;
    private Long preparacionId;
    private Long ingredienteId;
    private String nombreIngrediente;
    private int cantidadUtilizada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPreparacionId() {
        return preparacionId;
    }

    public void setPreparacionId(Long preparacionId) {
        this.preparacionId = preparacionId;
    }

    public Long getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(Long ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public int getCantidadUtilizada() {
        return cantidadUtilizada;
    }

    public void setCantidadUtilizada(int cantidadUtilizada) {
        this.cantidadUtilizada = cantidadUtilizada;
    }
}