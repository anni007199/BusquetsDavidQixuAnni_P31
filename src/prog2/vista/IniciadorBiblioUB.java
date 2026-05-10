package prog2.vista;

/**
 * Classe principal que conté el punt d'entrada de l'aplicació BiblioUB.
 * La seva funció és instanciar la classe de la vista i iniciar
 * la gestió de la biblioteca.
 */
public class IniciadorBiblioUB {
    public static void main (String[] args){
        BiblioUB biblioUB = new BiblioUB();
        biblioUB.gestioBiblioUB();
    }
}
