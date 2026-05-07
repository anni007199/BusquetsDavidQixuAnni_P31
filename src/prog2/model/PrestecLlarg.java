package prog2.model;

import java.util.Date;

/**
 * La classe PrestecNormal representa un préstec amb una durada superior a l'estàndard.
 * Implementa la lògica específica per a la modalitat de préstec llarg
 * definida pel sistema.
 */
public class PrestecLlarg extends Prestec{
    // Atributs
    // "140000L" vol dir 140 segons
    private static final long DURADA_PRESTEC_LLARGS = 140000L;

    /**
     * Constructor per crear un préstec de tipus llarg.
     * Inicialitza l'objecte mitjançant el constructor de la superclasse Prestec.
     */
    public PrestecLlarg(Exemplar exemplar, Usuari usuari, Date dataCreacio){
        super(exemplar,usuari,dataCreacio);
    }

    /**
     * {@inheritDoc}
     * @return String amb el literal "Llarg".
     */
    @Override
    public String tipusPrestec(){
        return "Llarg";
    }

    /**
     * {@inheritDoc}
     * Retorna la durada específica definida per la constant DURADA_PRESTEC_LLARGS.
     * @return long amb la durada en mil·lisegons.
     */
    @Override
    public long duradaPrestec(){
        return DURADA_PRESTEC_LLARGS;
    }
}
