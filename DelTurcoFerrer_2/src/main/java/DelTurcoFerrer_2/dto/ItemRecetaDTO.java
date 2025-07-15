package DelTurcoFerrer_2.dto;

public class ItemRecetaDTO {
    private Long id;
    private int cantidad;
    private int calorias;
    private IngredienteDTO ingrediente; 
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public int getCalorias() { return calorias; }
    public void setCalorias(int calorias) { this.calorias = calorias; }
    
    public IngredienteDTO getIngrediente() { return ingrediente; }
    public void setIngrediente(IngredienteDTO ingrediente) { this.ingrediente = ingrediente; }
}
