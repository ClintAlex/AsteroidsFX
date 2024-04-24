package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * IEntityProcessingService interfacet definerer en kontrakt for tjenester til behandling af enheder,
 * som er ansvarlige for at håndtere spillogik relateret til enheders interaktioner og adfærd
 * under hver spilopdateringscyklus.
 */
public interface IEntityProcessingService {

    /**
     * Behandler spillogik relateret til enheder.
     *
     * Implementeringer af denne metode bør indeholde logik, der opdaterer enhedernes tilstand
     * baseret på spillets regler, interaktioner mellem enheder, eller andre kriterier defineret af spillet.
     *
     * @param gameData indeholder data relateret til spillets nuværende tilstand,
     *                 såsom spilindstillinger og kontroller.
     * @param world indeholder den nuværende tilstand af spilverdenen, inklusive enheder og deres egenskaber.
     */
    void process(GameData gameData, World world);
}
