package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * IGamePluginService interfacet definerer en kontrakt for spilplugin-tjenester,
 * som er ansvarlige for at håndtere livscyklussen for spilkomponenter såsom enheder.
 */
public interface IGamePluginService {

    /**
     * Initialiserer og tilføjer spilkomponenter til spillet ved spillets start eller
     * når et spilniveau indlæses.
     *
     * Denne metode bør opsætte den indledende spiltilstand, enheder og andre komponenter
     * der er nødvendige for at spillet kan fungere.
     *
     * @param gameData indeholder data relateret til spillets nuværende tilstand,
     *                 såsom spilindstillinger og kontroller.
     * @param world indeholder den nuværende tilstand af spilverdenen, inklusive enheder og deres egenskaber.
     */
    void start(GameData gameData, World world);

    /**
     * Rydder op og fjerner spilkomponenter fra spillet, når spillet eller et spilniveau forlades.
     *
     * Denne metode bør sikre, at ressourcer tildelt under start bliver korrekt frigivet
     * og at spiltilstanden efterlades i en konsistent tilstand.
     *
     * @param gameData indeholder data relateret til spillets nuværende tilstand,
     *                 såsom spilindstillinger og kontroller.
     * @param world indeholder den nuværende tilstand af spilverdenen, inklusive enheder og deres egenskaber.
     */
    void stop(GameData gameData, World world);
}