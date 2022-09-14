package net.whatcanieat.FoodApi.plato;

import net.whatcanieat.FoodApi.enums.Continente;
import net.whatcanieat.FoodApi.enums.Sabor;
import net.whatcanieat.FoodApi.enums.Temperatura;
import net.whatcanieat.FoodApi.ingrediente.Ingrediente;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    String nombre;

    @NonNull
    String nombreReal;

    @NonNull
    String nombreEng;

    @NonNull
    Continente continente;

    @NonNull
    Sabor sabor;

    @NonNull
    Temperatura temperatura;

    @ManyToMany
    @JoinTable(
            name = "plato_ingrediente",
            joinColumns = @JoinColumn(name = "plato_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
    Collection<Ingrediente> ingredientes;

    public Plato() {
    }

    public Plato(@NonNull String nombre, @NonNull String nombreReal, @NonNull String nombreEng, @NonNull Continente continente, @NonNull Sabor sabor, @NonNull Temperatura temperatura, Collection<Ingrediente> ingredientes) {
        this.nombre = nombre;
        this.nombreReal = nombreReal;
        this.nombreEng = nombreEng;
        this.continente = continente;
        this.sabor = sabor;
        this.temperatura = temperatura;
        this.ingredientes = ingredientes;
    }

    public Plato(Long id, @NonNull String nombre, @NonNull String nombreReal, @NonNull String nombreEng, @NonNull Continente continente, @NonNull Sabor sabor, @NonNull Temperatura temperatura, Collection<Ingrediente> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.nombreReal = nombreReal;
        this.nombreEng = nombreEng;
        this.continente = continente;
        this.sabor = sabor;
        this.temperatura = temperatura;
        this.ingredientes = ingredientes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(@NonNull String nombreReal) {
        this.nombreReal = nombreReal;
    }

    @NonNull
    public String getNombreEng() {
        return nombreEng;
    }

    public void setNombreEng(@NonNull String nombreEng) {
        this.nombreEng = nombreEng;
    }

    @NonNull
    public Continente getContinente() {
        return continente;
    }

    public void setContinente(@NonNull Continente continente) {
        this.continente = continente;
    }

    @NonNull
    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(@NonNull Sabor sabor) {
        this.sabor = sabor;
    }

    @NonNull
    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(@NonNull Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public Collection<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Collection<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
