package Modele.Entitees.EntiteeAvecInventaire;

import java.util.Iterator;

import Modele.Entitees.Ressources.Ressource;

public class Inventaire implements Iterable<Ressource>
{
	/** Inventaire : Liste de Ressources de chaque type avec un compteur 
	 * incremente ou decremente lors de la recuperation ou la supression de ressources
	 */
	private Ressource[] inventaire = new Ressource[Ressource.Type.COUNT.ordinal()];
	
	public Ressource get(int i) { return inventaire[i]; }
	public Ressource[] getInventaire() { return this.inventaire; }
	
	/** Ajoute la ressource donnee a l'inventaire de l'entitee
	 * @param r : Ressource a mettre dans l'inventaire
	 */
	public void add(Ressource r) 
	{ 
		Ressource inInvRess = this.inventaire[r.getType().ordinal()];
		if(inInvRess == null) this.inventaire[r.getType().ordinal()] = new Ressource(null, r.getQuantitee(), r.getType());
		else inInvRess.setQuantitee(inInvRess.getQuantitee() + r.getQuantitee());
//		System.out.print(Arrays.toString(inventaire)); //debug
	}
	
	/** Supprime la ressource donnee de l'inventaire
	 * @param r : Ressource a enlever
	 * @return : Reusite de l'operation (test de quantitee suffisante ?)
	 */
	public boolean remove(Ressource r) 
	{ 
		Ressource inInvRess = this.inventaire[r.getType().ordinal()];
		if(inInvRess == null) return false;
		int quantitee = inInvRess.getQuantitee() - r.getQuantitee();
		if(quantitee < 0) return false;		
		else inInvRess.setQuantitee(quantitee);
		return true;
	}

	@Override
	public Iterator<Ressource> iterator() 
	{
		// TODO Auto-generated method stub @Override
        Iterator<Ressource> it = new Iterator<Ressource>() 
        {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() 
            {
                return currentIndex < inventaire.length && inventaire[currentIndex] != null;
            }

            @Override
            public Ressource next() {
                return inventaire[currentIndex++];
            }

            @Override
            public void remove() 
            {
                throw new UnsupportedOperationException();
            }
        };
        return it;
	}

	private void transfert(Inventaire cible)
	{
		for (int i = 0; i < inventaire.length ; i++)
		{
			Ressource r = get(i);
			if (r != null)
			{
				cible.add(r);
				remove(r);
			}
		}
	}
}
