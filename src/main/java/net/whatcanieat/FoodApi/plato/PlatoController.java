package net.whatcanieat.FoodApi.plato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PlatoController {


    private final PlatoService platoService;

    @Autowired
    public PlatoController(PlatoService platoService) {
        this.platoService = platoService;
    }

    @GetMapping(params = "/api/platos")
    public ResponseEntity<Plato> getPlato(){
        return new ResponseEntity<>(platoService.getOne(), HttpStatus.OK);
    }

    @GetMapping(path = "/api/platos/{id}")
    public ResponseEntity<Plato> getById(@PathVariable Long id){
        return new ResponseEntity<>(platoService.getById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/api/platos")
    public ResponseEntity<List<Plato>> addOne(List<Plato> plato){
        return new ResponseEntity<>(platoService.saveOrUpdate(plato), HttpStatus.OK);
    }

}
