package DelTurcoFerrer_2.dto;

public class RecetaListadoDTO {
    private Long id;
    private String nombre;
    private int caloriasTotales;
    private String descripcion;

    public RecetaListadoDTO(Long id, String nombre, int caloriasTotales, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.caloriasTotales = caloriasTotales;
        this.descripcion = descripcion;
    }

    public RecetaListadoDTO(Long id, String nombre, int caloriasTotales) {
        this(id, nombre, caloriasTotales, null);
    }
    public RecetaListadoDTO(String nombre, int caloriasTotales) {
        this.nombre = nombre;
        this.caloriasTotales = caloriasTotales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCaloriasTotales() {
        return caloriasTotales;
    }

    public void setCaloriasTotales(int caloriasTotales) {
        this.caloriasTotales = caloriasTotales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
