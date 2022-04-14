package Controleur;

public class Association {
	private int idassoc;
	private String nom, adresse, cp, ville,tel;
	public Association(int idassoc, String nom, String adresse, String cp, String ville, String tel) {
		super();
		this.idassoc = idassoc;
		this.nom = nom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.tel = tel;
	}
	
	public Association(String nom, String adresse, String cp, String ville, String tel) {
		super();
		this.idassoc = 0;
		this.nom = nom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.tel = tel;
	}

	public int getIdassoc() {
		return idassoc;
	}

	public void setIdassoc(int idassoc) {
		this.idassoc = idassoc;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	

}
