package prog2.model;

import java.util.Date;

/**
 * La classe PrestecNormal representa un préstec amb una durada estàndard.
 * Implementa la lògica específica per a la modalitat de préstec normal
 * definida pel sistema.
 */
public class PrestecNormal extends Prestec{
    // Atributs
    // "70000L" vol dir 70 segons
    private static final long DURADA_PRESTEC_NORMAL = 70000L;

    /**
     * Constructor per crear un préstec de tipus normal.
     * Invoca el constructor de la superclasse Prestec per inicialitzar els atributs.
     */
    public PrestecNormal(Exemplar exemplar, Usuari usuari, Date dataCreacio){
        super(exemplar,usuari,dataCreacio);
    }

    /**
     * {@inheritDoc}
     * @return String amb el literal "Normal".
     */
    @Override
    public String tipusPrestec(){
        return "Normal";
    }

    /**
     * {@inheritDoc}
     * Retorna la durada específica definida per la constant DURADA_PRESTEC_NORMAL.
     * @return long amb la durada en mil·lisegons.
     */
    @Override
    public long duradaPrestec(){
        return DURADA_PRESTEC_NORMAL;
    }
}
