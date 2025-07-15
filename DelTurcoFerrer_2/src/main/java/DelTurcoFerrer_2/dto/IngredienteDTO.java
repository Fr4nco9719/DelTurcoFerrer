package DelTurcoFerrer_2.dto;

public class IngredienteDTO {

    private Long id;
    private Long preparacionId;
    private Long ingredienteId;
    private String nombreIngrediente;
    private double cantidadUtilizada;
    private int calorias;

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

    public double getCantidadUtilizada() {
        return cantidadUtilizada;
    }

    public void setCantidadUtilizada(double cantidadUtilizada) {
        this.cantidadUtilizada = cantidadUtilizada;
    }

	public int getCalorias() {
	    return calorias;
	}
	
	public void setCalorias(int calorias) {
	    this.calorias = calorias;
	}
}