package fr.efrei.pokemon.models;

import fr.efrei.pokemon.constants.GameObjectEnum;
import jakarta.persistence.*;

@Entity
public class GameObjectInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private GameObjectEnum gameObject;

    public GameObjectEnum getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObjectEnum gameObject) {
        this.gameObject = gameObject;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
