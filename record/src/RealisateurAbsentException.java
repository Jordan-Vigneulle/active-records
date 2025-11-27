public class RealisateurAbsentException extends RuntimeException {
    public RealisateurAbsentException(String message) {

        super("Le réalisateur n'existe pas.");
    }
}
