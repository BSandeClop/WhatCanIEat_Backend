package net.whatcanieat.FoodApi.plato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatoService {

    private final PlatoRepository platoRepository;

    @Autowired
    public PlatoService(PlatoRepository platoRepository) {
        this.platoRepository = platoRepository;
    }

    public Plato saveOrUpdate(Plato plato){
        return platoRepository.save(plato);
    }

    public List<Plato> saveOrUpdate(List<Plato> lst){
        return platoRepository.saveAll(lst);
    }

    public Plato getById(Long id){
        return platoRepository.findById(id).orElse(new Plato());
    }

    public List<Plato> getAllByNombre(String nombre){
        return platoRepository.findAllByNombreIgnoreCase(nombre);
    }

    public Plato getOne(){
        return platoRepository.findOneRandom().orElse(new Plato());
    }

    public void removeById(Long id){
        try {
            platoRepository.deleteById(id);
        } catch (Exception e){
            System.out.println("Error en PlatoService: removeById ");
            throw e;
        }

    }

    public List<Plato> findAll() {
        return platoRepository.findAll();
    }
}
