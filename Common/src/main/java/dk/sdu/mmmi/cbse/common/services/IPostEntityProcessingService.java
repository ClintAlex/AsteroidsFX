package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * IPostEntityProcessingService interfacet definerer en kontrakt for tjenester til efterbehandling af enheder,
 * som er ansvarlige for at håndtere spillogik, der skal udføres efter at al IEntityProcessingService
 * logik er blevet fuldført.
 */
public interface IPostEntityProcessingService {

    /**
     * Behandler spillogik, der forekommer efter at al IEntityProcessingService logik er udført.
     *
     * Implementeringer af denne metode kan omfatte logik til oprydning, løsning af tilstandsændringer,
     * eller andre endelige justeringer af spiltilstanden eller enhederne efter den primære enhedsbehandling har fundet sted.
     *
     * @param gameData indeholder data relateret til spillets nuværende tilstand,
     *                 såsom spilindstillinger og kontroller.
     * @param world indeholder den nuværende tilstand af spilverdenen, inklusive enheder og deres egenskaber.
     */
    void process(GameData gameData, World world);
}