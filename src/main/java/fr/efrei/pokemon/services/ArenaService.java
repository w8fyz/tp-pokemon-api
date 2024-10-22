package fr.efrei.pokemon.services;

import fr.efrei.pokemon.models.Arena;
import fr.efrei.pokemon.models.GameObjectInstance;
import fr.efrei.pokemon.repositories.ArenaRepository;
import fr.efrei.pokemon.repositories.GameObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArenaService {

    private final ArenaRepository arenaRepository;

    @Autowired
    public ArenaService(ArenaRepository arenaRepository) {
        this.arenaRepository = arenaRepository;
    }

    public List<Arena> findAll() {
        return arenaRepository.findAll();
    }

    public Arena findById(String id) {
        return arenaRepository.findById(id).orElse(null);
    }

    public void save(Arena arena) {
        arenaRepository.save(arena);
    }

    public void delete(String id) {
        arenaRepository.deleteById(id);
    }

    public void update(String id, Arena arena) {
        Arena editableObject = findById(id);
        editableObject.setName(arena.getName());
        arenaRepository.save(editableObject);
    }

    public void partialUpdate(String id, Arena arena) {
        Arena editableObject = findById(id);
        if(arena.getName() != null) {
            editableObject.setName(arena.getName());
        }
        arenaRepository.save(editableObject);
    }
}
