package fr.efrei.pokemon.constants;

import fr.efrei.pokemon.interfaces.IGameObjectEffect;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.models.Trainer;

public enum GameObjectEnum {

    HEALTH_POTION_25("Simple Health Potion", "A potion that restores 25 health points", new IGameObjectEffect() {
        @Override
        public void apply(Trainer trainer, Pokemon pokemon) {
            System.out.println("Applying simple health potion effect on "+pokemon.getName()+" of "+trainer.getName());
        }
    }),

    MANA_POTION_25("Simple Mana Potion", "A potion that restores 25 mana points", new IGameObjectEffect() {
        @Override
        public void apply(Trainer trainer, Pokemon pokemon) {
            System.out.println("Applying simple mana potion effect on "+pokemon.getName()+" of "+trainer.getName());
        }
    });

    private final String name,description;
    private final IGameObjectEffect effect;

    GameObjectEnum(String name, String description, IGameObjectEffect effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public IGameObjectEffect getEffect() {
        return effect;
    }

}