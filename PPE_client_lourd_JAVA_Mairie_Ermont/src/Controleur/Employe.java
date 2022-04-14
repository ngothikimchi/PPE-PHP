package Controleur;

public class Employe {
	private int idemploye, idservice;
	private String nom, prenom, email;
	public Employe(int idemploye, int idservice, String nom, String prenom, String email) {
		super();
		this.idemploye = idemploye;
		this.idservice = idservice;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	public Employe(int idservice, String nom, String prenom, String email) {
		super();
		this.idemploye = 0;
		this.idservice = idservice;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public int getIdemploye() {
		return idemploye;
	}

	public void setIdemploye(int idemploye) {
		this.idemploye = idemploye;
	}

	public int getIdservice() {
		return idservice;
	}

	public void setIdservice(int idservice) {
		this.idservice = idservice;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
