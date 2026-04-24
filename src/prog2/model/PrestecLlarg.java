package prog2.model;

import java.util.Date;

public class PrestecLlarg extends Prestec{
    // Atributs
    // "140000L" vol dir 140 segons
    private static final long DURADA_PRESTEC_LLARGS = 140000L;

    // Constructor
    public PrestecLlarg(Exemplar exemplar, Usuari usuari, Date dataCreacio){
        super(exemplar,usuari,dataCreacio);
    }

    @Override
    public String tipusPrestec(){
        return "Llarg";
    }
    @Override
    public long duradaPrestec(){
        return DURADA_PRESTEC_LLARGS;
    }
}
