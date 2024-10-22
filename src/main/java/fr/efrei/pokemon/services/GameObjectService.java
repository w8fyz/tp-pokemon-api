package fr.efrei.pokemon.services;

import fr.efrei.pokemon.models.GameObjectInstance;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.repositories.GameObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameObjectService {

    private final GameObjectRepository gameObjectRepository;

    @Autowired
    public GameObjectService(GameObjectRepository gameObjectRepository) {
        this.gameObjectRepository = gameObjectRepository;
    }

    public List<GameObjectInstance> findAll() {
        return gameObjectRepository.findAll();
    }

    public GameObjectInstance findById(String id) {
        return gameObjectRepository.findById(id).orElse(null);
    }

    public void save(GameObjectInstance gameObjectInstance) {
        gameObjectRepository.save(gameObjectInstance);
    }

    public void delete(String id) {
        gameObjectRepository.deleteById(id);
    }

    public void update(String id, GameObjectInstance gameObjectInstance) {
        GameObjectInstance editableObject = findById(id);
        editableObject.setGameObject(gameObjectInstance.getGameObject());
        gameObjectRepository.save(editableObject);
    }

    public void partialUpdate(String id, GameObjectInstance gameObjectInstance) {
        GameObjectInstance editableObject = findById(id);
        if(gameObjectInstance.getGameObject() != null) {
            editableObject.setGameObject(gameObjectInstance.getGameObject());
        }
        gameObjectRepository.save(editableObject);
    }
}
