package fr.efrei.pokemon.interfaces;

import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.models.Trainer;

public interface IGameObjectEffect {

    void apply(Trainer trainer, Pokemon pokemon);

}
