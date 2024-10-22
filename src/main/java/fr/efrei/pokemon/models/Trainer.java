package fr.efrei.pokemon.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Trainer {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String name;

	@OneToMany
	private List<Pokemon> team;

	@OneToMany
	private List<GameObjectInstance> gameObjects;

	@OneToMany
	private List<Arena> visitedArena;

	public List<Arena> getVisitedArena() {
		return visitedArena;
	}

	public void setVisitedArena(List<Arena> visitedArena) {
		this.visitedArena = visitedArena;
	}

	public List<GameObjectInstance> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(List<GameObjectInstance> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public void addGameObject(GameObjectInstance gameObjectInstance) {
		this.gameObjects.add(gameObjectInstance);
	}

	public void removeGameObject(GameObjectInstance gameObjectInstance) {
		this.gameObjects.remove(gameObjectInstance);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pokemon> getTeam() {
		return team;
	}

	public void setTeam(List<Pokemon> team) {
		this.team = team;
	}
}
