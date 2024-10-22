package fr.efrei.pokemon.controller;

import fr.efrei.pokemon.models.Arena;
import fr.efrei.pokemon.models.GameObjectInstance;
import fr.efrei.pokemon.services.ArenaService;
import fr.efrei.pokemon.services.GameObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arenas")
public class ArenaController {


    /*

        Système d'arène basique, permet de savoir quelles arènes un trainer a déjà visité, et de les afficher, voir en
        rajouter. Pour un système plus complexe, on pourrait imaginer un ajout de système de badges lié à l'arène par
        exemple.

     */

    private final ArenaService service;

    @Autowired
    public ArenaController(ArenaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Arena>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arena> findById(@PathVariable String id) {
        Arena arena = service.findById(id);
        if (arena == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(arena, HttpStatus.OK);
    }

    // POST
    @PostMapping
    public ResponseEntity<Arena> create(@RequestBody Arena arena) {
        if(arena.getName()== null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.save(arena);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Arena> update(@PathVariable String id, @RequestBody Arena arena) {
        Arena editableInstance = service.findById(id);
        if (editableInstance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.update(id, arena);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Arena editableInstance = service.findById(id);
        if (editableInstance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable String id, @RequestBody Arena arena) {
        Arena editableInstance = service.findById(id);
        if (editableInstance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.partialUpdate(id, arena);
        return new ResponseEntity< >(HttpStatus.NO_CONTENT);
    }


}
