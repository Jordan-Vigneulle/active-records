import java.util.ArrayList;

public class Personne {
    private int id;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.id = -1;
        this.nom = nom;
        this.prenom = prenom;
    }

    public void save(){

    }
    private void saveNew(){

    }
    public int getId(){
        return id;
    }
    public void delete(){

    }
    private void update(){

    }
    public static Personne findById(int id){
        return new Personne("Gg","DFl");
    }
    public static Personne findByNom(String nom){
        return new Personne("Gg","DFl");
    }
    public static ArrayList<Personne> findAll(){
        return new ArrayList<Personne>();
    }
    public static void createTable(){
        
    }
    public static void deleteTable(){

    }
    public String toString(){
        return nom + " " + prenom;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
