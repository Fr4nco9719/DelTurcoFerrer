package DelTurcoFerrer_2.dto;

import java.util.ArrayList;
import java.util.List;

public class RecetaDTO {
	private Long id;
    private String nombre;
    private String descripcion;
    private List<IngredienteDTO> ingredientes = new ArrayList<>();
    private int racionesPorPreparacion;

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

    public List<IngredienteDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

	public int getRacionesPorPreparacion() {
		return racionesPorPreparacion;
	}

	public void setRacionesPorPreparacion(int racionesPorPreparacion) {
		this.racionesPorPreparacion = racionesPorPreparacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
