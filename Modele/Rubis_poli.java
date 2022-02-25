package Modele;

public class Rubis_poli extends Recette{

    public Rubis_poli()
    {
        setAvancee(20);
        add_ingredient(Ressource.Type.RUBIS,1);
        setNom("Rubis poli");
    }
}
