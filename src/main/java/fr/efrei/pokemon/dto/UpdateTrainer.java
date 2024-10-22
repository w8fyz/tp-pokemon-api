package fr.efrei.pokemon.dto;

import java.util.List;

public class UpdateTrainer {

	private String name;

	private List<String> team;

	private List<String> gameObjects;
	private List<String> visitedArenas;

	public List<String> getVisitedArenas() {
		return visitedArenas;
	}

	public void setVisitedArenas(List<String> visitedArenas) {
		this.visitedArenas = visitedArenas;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(List<String> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public List<String> getTeam() {
		return team;
	}

	public void setTeam(List<String> team) {
		this.team = team;
	}
}
