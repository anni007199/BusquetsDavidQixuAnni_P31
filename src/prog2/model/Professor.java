package prog2.model;

public class Professor extends Usuari{
    // Constructor
    public Professor (String email, String nom, String adreca){
        super(email,nom,adreca);
    }

    @Override
    public int getMaxPrestecsNormals(){
        return 2;
    }
    @Override
    public int getMaxPrestecsLlargs(){
        return 2;
    }
}
