package net.whatcanieat.FoodApi.plato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlatoController {


    private final PlatoService platoService;

    @Autowired
    public PlatoController(PlatoService platoService) {
        this.platoService = platoService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<String> status(){
        return new ResponseEntity<>("FoodApi is running", HttpStatus.OK);
    }

    @GetMapping(path = "/api/platos")
    public ResponseEntity<Plato> getPlato(){
        return new ResponseEntity<>(platoService.getOne(), HttpStatus.OK);
    }

    @GetMapping(path = "/api/platos/{id}")
    public ResponseEntity<Plato> getById(@PathVariable Long id){
        return new ResponseEntity<>(platoService.getById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/api/abm/platos")
    public ResponseEntity<List<Plato>> getAllPlatos(){
        return new ResponseEntity<>(platoService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/api/abm/platos")
    public ResponseEntity<List<Plato>> addPlatos(@RequestBody List<Plato> lst){
        return new ResponseEntity<>(platoService.saveOrUpdate(lst), HttpStatus.OK);
    }

    @DeleteMapping(path = "/api/abm/{id}")
    public ResponseEntity removePlatoById(@PathVariable Long id){
        try {
            platoService.removeById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
