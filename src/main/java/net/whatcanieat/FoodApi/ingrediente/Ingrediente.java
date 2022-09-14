package net.whatcanieat.FoodApi.ingrediente;

import net.whatcanieat.FoodApi.plato.Plato;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NonNull
    String nombre;

    @NonNull
    String nombreReal;

    @NonNull
    String nombreEng;

    @ManyToMany(mappedBy = "ingredientes")
    Collection<Plato> platos;

}
