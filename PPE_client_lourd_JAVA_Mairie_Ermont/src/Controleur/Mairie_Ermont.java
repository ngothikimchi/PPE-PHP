package Controleur;

import Controleur.User;
import Modele.modele;
import Vue.VueConnexion;
import Vue.VueGenerale;

public class Mairie_Ermont {
	//add
	private static VueConnexion uneConnexion ;
	private static VueGenerale uneVueGenerale;
	public static void main(String[] args) {
		uneConnexion = new VueConnexion();

	}

	/*****************Gestion des users ********************/
	public static User selectWhereUser(String email, String mdp) 
	{
		User unUser = modele.selectWhereUser(email, mdp);
		return unUser;
	}

	public static void instancierVueGenerale(User unUser) {
		uneVueGenerale = new VueGenerale(unUser);
		
	}

	public static void rendreVisibleVueConnexion(boolean action) {
		uneConnexion.setVisible(action);
		
	}

	public static void fermerVueGenerale() 
	{
		uneVueGenerale.dispose();
		
	}

	

}
