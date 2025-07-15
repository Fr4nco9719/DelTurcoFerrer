package DelTurcoFerrer_2.dto;
public class RecetaListadoDTO {
	private String nombre;
	private int caloriasTotales;
    private String descripcion;  

    public RecetaListadoDTO(String nombre, int caloriasTotales, String descripcion) {
        this.nombre = nombre;
        this.caloriasTotales = caloriasTotales;
        this.descripcion = descripcion;
    }

	
	public RecetaListadoDTO(String nombre, int caloriasTotales) {
		super();
		this.nombre = nombre;
		this.caloriasTotales = caloriasTotales;
	}


	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setCaloriasTotales(int caloriasTotales) {
		this.caloriasTotales = caloriasTotales;
	}


	public int getCaloriasTotales() {
		return caloriasTotales;
	}
}