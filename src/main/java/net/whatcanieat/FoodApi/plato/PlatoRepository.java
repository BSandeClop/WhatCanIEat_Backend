package net.whatcanieat.FoodApi.plato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Long> {

    List<Plato> findAllByNombreIgnoreCase(String nombre);

    @Query(value = " SELECT * FROM plato ORDER BY RAND() LIMIT 1 ", nativeQuery = true)
    Optional<Plato> findOneRandom();


}
