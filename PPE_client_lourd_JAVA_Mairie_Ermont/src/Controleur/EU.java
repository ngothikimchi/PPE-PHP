package Controleur;

public class EU 
{
	private int iduser, idemploye;
	private String nom, prenom, email, service;
	
	public EU(int iduser, int idemploye, String nom, String prenom, String email, String service) {
		super();
		this.iduser = iduser;
		this.idemploye = idemploye;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.service = service;
	}
	
	public EU(int idemploye, String nom, String prenom, String email, String service) {
		super();
		this.iduser = 0;
		this.idemploye = idemploye;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.service = service;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getIdemploye() {
		return idemploye;
	}

	public void setIdemploye(int idemploye) {
		this.idemploye = idemploye;
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

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
	
}
