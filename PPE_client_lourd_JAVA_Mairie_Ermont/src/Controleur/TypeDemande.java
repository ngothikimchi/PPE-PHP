package Controleur;

public class TypeDemande {
	private int id;
	private String nom;
	private boolean etrePlurielDem;
	public TypeDemande(int id, String nom, boolean etrePlurielDem) {
		super();
		this.id = id;
		this.nom = nom;
		this.etrePlurielDem = etrePlurielDem;
	}
	
	public TypeDemande(String nom, boolean etrePlurielDem) {
		super();
		this.id = 0;
		this.nom = nom;
		this.etrePlurielDem = etrePlurielDem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isEtrePlurielDem() {
		return etrePlurielDem;
	}

	public void setEtrePlurielDem(boolean etrePlurielDem) {
		this.etrePlurielDem = etrePlurielDem;
	}
	
	
	
	
}
