package prog2.model;

import java.util.Date;

public class PrestecNormal extends Prestec{
    // Atributs
    // "70000L" vol dir 70 segons
    private static final long DURADA_PRESTEC_NORMAL = 70000L;

    // Constructor
    public PrestecNormal(Exemplar exemplar, Usuari usuari, Date dataCreacio){
        super(exemplar,usuari,dataCreacio);
    }

    @Override
    public String tipusPrestec(){
        return "Normal";
    }
    @Override
    public long duradaPrestec(){
        return DURADA_PRESTEC_NORMAL;
    }
}
