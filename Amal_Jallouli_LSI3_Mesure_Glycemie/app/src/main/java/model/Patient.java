package model;
public class Patient {
    private int age;
    private float valeurMesuree;
    private boolean isFasting;
    private static String reponse;

    //Flèche "Update" Controller --> Model
    public Patient(int age, float valeurMesuree, boolean isFasting) {
        this.age = age;
        this.valeurMesuree = valeurMesuree;
        this.isFasting = isFasting;
        calculer();
    }

    private void calculer() {
        if (isFasting) {
            if (age >= 13) {
                if (valeurMesuree < 5.0)
                    reponse = "Niveau de glycémie est trop bas";
                else if (valeurMesuree >= 5.0 && valeurMesuree <= 7.2)
                    reponse = "Niveau de glycémie est normale";
                else
                    reponse = "Niveau de glycémie est trop élevé";
            } else if (age >= 6 && age <= 12) {
                if (valeurMesuree < 5.0)
                    reponse = "Niveau de glycémie est trop bas";
                else if (valeurMesuree >= 5.0 && valeurMesuree <= 10.0)
                    reponse = "Niveau de glycémie est normale";
                else
                    reponse = "Niveau de glycémie est trop élevé";
            } else if (age < 6) {
                if (valeurMesuree < 5.5)
                    reponse = "Niveau de glycémie est trop bas";
                else if (valeurMesuree >= 5.5 && valeurMesuree <= 10.0)
                    reponse = "Niveau de glycémie est normale";
                else
                    reponse = "Niveau de glycémie est trop élevé";
            }
        } else {
            if (valeurMesuree > 10.5)
                reponse = "Niveau de glycémie est trop élevé";
        }
    }

    public int getAge() {
        return age;
    }

    public float getValeurMesuree() {
        return valeurMesuree;
    }

    public boolean isFasting() {
        return isFasting;
    }

    //Flèche "Notify" Model --> Controller
    public String getReponse() {
        return reponse;
    }
}