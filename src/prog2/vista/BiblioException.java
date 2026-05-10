package prog2.vista;

/**
 * Classe personalitzada per a la gestió d'excepcions dins de l'aplicació BiblioUB.
 * S'utilitza per capturar i notificar errors específics de la lògica.
 */
public class BiblioException extends Exception{

    /**
     * Constructor de l'excepció BiblioException.
     * Permet definir un missatge personalitzat que descrigui l'error produït.
     * @param message String que conté el detall de l'error per informar l'usuari.
     */
    public BiblioException(String message){
        super(message);
    }
}
