package fr.efrei.pokemon.controller;

import fr.efrei.pokemon.models.GameObjectInstance;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.services.GameObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gameObjects")
public class GameObjectController {

    /*

        Sorte de système d'inventaire basique, on peut ajouter des objets, les modifier, les supprimer, les afficher, etc.
        Les objets en question sont récupérés via l'Enum GameObjectEnum, car on veux hardcoder les objets disponibles.

     */

    private final GameObjectService service;

    @Autowired
    public GameObjectController(GameObjectService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<GameObjectInstance>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameObjectInstance> findById(@PathVariable String id) {
        GameObjectInstance gameObjectInstance = service.findById(id);
        if (gameObjectInstance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gameObjectInstance, HttpStatus.OK);
    }

    // POST
    @PostMapping
    public ResponseEntity<?> create(@RequestBody GameObjectInstance gameObjectInstance) {
        if(gameObjectInstance.getGameObject() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.save(gameObjectInstance);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody GameObjectInstance gameObjectInstance) {
        GameObjectInstance editableInstance = service.findById(id);
        if (editableInstance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.update(id, gameObjectInstance);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        GameObjectInstance editableInstance = service.findById(id);
        if (editableInstance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable String id, @RequestBody GameObjectInstance gameObjectInstance) {
        GameObjectInstance editableInstance = service.findById(id);
        if (editableInstance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.partialUpdate(id, gameObjectInstance);
        return new ResponseEntity< >(HttpStatus.NO_CONTENT);
    }


}
