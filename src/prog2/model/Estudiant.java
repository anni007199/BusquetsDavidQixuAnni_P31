package prog2.model;

public class Estudiant extends Usuari {
    // Constructor
    public Estudiant(String email, String nom, String adreca){
        super(email,nom,adreca);
    }

    @Override
    public String tipusUsuari(){
        return "Estudiant";
    }
    @Override
    public int getMaxPrestecsNormals(){
        return 2;
    }
    @Override
    public int getMaxPrestecsLlargs(){
        return 1;
    }
}
