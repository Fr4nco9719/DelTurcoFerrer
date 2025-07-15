package DelTurcoFerrer_2.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_ingrediente", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("INGREDIENTE_BASE") 

public class Ingrediente {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY) 
 private Long id;

 private String nombre;
 
 private int calorias;

 @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL, orphanRemoval = true)
 private Set<ItemReceta> itemsRecetas = new HashSet<>();
 
 public Ingrediente() {}

 public Ingrediente(String nombre, int calorias) {
     this.nombre = nombre;
     this.calorias = calorias;
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

 public int getCalorias() {
     return calorias;
 }

 public void setCalorias(int calorias) {
     this.calorias = calorias;
 }
 public Set<ItemReceta> getItemsRecetas() {
     return itemsRecetas;
 }

 public void setItemsRecetas(Set<ItemReceta> itemsRecetas) {
     this.itemsRecetas = itemsRecetas;
 }
}