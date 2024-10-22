package fr.efrei.pokemon.repositories;

import fr.efrei.pokemon.models.GameObjectInstance;
import fr.efrei.pokemon.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameObjectRepository extends JpaRepository<GameObjectInstance, String> { }
