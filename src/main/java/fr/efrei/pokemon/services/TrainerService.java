package fr.efrei.pokemon.services;

import fr.efrei.pokemon.dto.CreateTrainer;
import fr.efrei.pokemon.dto.UpdateTrainer;
import fr.efrei.pokemon.models.Arena;
import fr.efrei.pokemon.models.GameObjectInstance;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.repositories.TrainerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerService {

	private final TrainerRepository repository;
	private final PokemonService pokemonService;
	private final GameObjectService gameObjectService;
	private final ArenaService arenaService;

	@Autowired
	public TrainerService(TrainerRepository repository, PokemonService pokemonService, GameObjectService gameObjectService, ArenaService arenaService) {
		this.repository = repository;
		this.pokemonService = pokemonService;
		this.gameObjectService = gameObjectService;
		this.arenaService = arenaService;
	}

	public List<Trainer> findAll() {
		return repository.findAll();
	}

	public Trainer findById(String id) {
		return repository.findById(id).orElse(null);
	}

	public void save(CreateTrainer trainerBody) {
		Trainer trainer = new Trainer();
		trainer.setName(trainerBody.getName());
		// On récupère la liste des ids des pokemon du body postman
		List<String> pokemonIds = trainerBody.getTeam();
		// On déclare une nouvelle liste de pokemon
		List<Pokemon> pokemonAAjouter = new ArrayList<>();
		// pour chaque id de pokemon dans ma liste d'id
		for (String idPokemon: pokemonIds) {
			// je récupere le pokemon avec l'id courant
			Pokemon pokemon = pokemonService.findById(idPokemon);
			// si le pokemon existe
			if(pokemon != null) {
				// je l'ajoute a ma liste de pokemon
				pokemonAAjouter.add(pokemon);
			}
		}
		// j'applique la liste de pokemon au trainer que je créé
		trainer.setTeam(pokemonAAjouter);
		// pokemonIds.forEach(id -> pokemonService.findById(id));
		List<GameObjectInstance> gameObjectInstances = new ArrayList<>();
		List<String> gameObjectIDs = trainerBody.getGameObjects();
		for(String idGameObject : gameObjectIDs) {
			GameObjectInstance gameObjectInstance = gameObjectService.findById(idGameObject);
			if(gameObjectInstance != null) {
				gameObjectInstances.add(gameObjectInstance);
			}
		}
		trainer.setGameObjects(gameObjectInstances);

		List<Arena> visitedArenas = new ArrayList<>();
		List<String> arenasID = trainerBody.getVisitedArenas();
		for(String idArenas : arenasID) {
			Arena arenas = arenaService.findById(idArenas);
			if(arenas != null) {
				visitedArenas.add(arenas);
			}
		}
		trainer.setVisitedArena(visitedArenas);
		repository.save(trainer);
	}

	@Transactional
	public void update(String id, UpdateTrainer trainerBody) {
		Trainer trainer = findById(id);
		if (trainerBody.getName() != null) {
			trainer.setName(trainerBody.getName());
		}
		if(trainerBody.getTeam() != null && !trainerBody.getTeam().isEmpty()) {
			List<Pokemon> pokemonList = new ArrayList<>();
			List<String> pokemonIds = trainerBody.getTeam();
			for(String idPokemon: pokemonIds) {
				Pokemon pokemon = pokemonService.findById(idPokemon);
				if(pokemon != null) {
					pokemonList.add(pokemon);
				}
			}
			pokemonList.addAll(trainer.getTeam());
			trainer.setTeam(pokemonList);
		}
		if(trainerBody.getGameObjects() != null && !trainerBody.getGameObjects().isEmpty()) {
			List<GameObjectInstance> gameObjectInstances = new ArrayList<>();
			List<String> gameObjectIDs = trainerBody.getGameObjects();
			for(String idGameObject : gameObjectIDs) {
				GameObjectInstance gameObjectInstance = gameObjectService.findById(idGameObject);
				if(gameObjectInstance != null) {
					gameObjectInstances.add(gameObjectInstance);
				}
			}
			gameObjectInstances.addAll(trainer.getGameObjects());
			trainer.setGameObjects(gameObjectInstances);
		}
		if(trainerBody.getVisitedArenas() != null && !trainer.getVisitedArena().isEmpty()) {
			List<Arena> visitedArenas = new ArrayList<>();
			List<String> arenasID = trainerBody.getVisitedArenas();
			for(String idArenas : arenasID) {
				Arena arenas = arenaService.findById(idArenas);
				if(arenas != null) {
					visitedArenas.add(arenas);
				}
			}
			trainer.setVisitedArena(visitedArenas);
		}
		repository.save(trainer);
	}

	public void delete(String id) {
		repository.deleteById(id);
	}
}
