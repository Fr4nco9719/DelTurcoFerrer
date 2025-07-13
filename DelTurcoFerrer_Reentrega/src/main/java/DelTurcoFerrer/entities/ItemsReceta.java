package DelTurcoFerrer.entities;

import jakarta.persistence.*;

@Entity
public class ItemsReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double cantidad;
    private int calorias;
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receta_id")
    private Receta receta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    public ItemsReceta() {}
    public boolean isActive() {
        return active;
    }
    public ItemsReceta(Receta receta, Ingrediente ingrediente, int cantidad) {
        this.receta = receta;
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
        this.calorias = cantidad * ingrediente.getCalorias();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double d) {
        this.cantidad = d;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public String toString() {
        return "ItemsReceta{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", calorias=" + calorias +
                ", receta=" + (receta != null ? receta.getNombre() : "null") +
                ", ingrediente=" + (ingrediente != null ? ingrediente.getNombre() : "null") +
                '}';
    }
	public void setActive(boolean active) {
		this.active = active;
	}
}
