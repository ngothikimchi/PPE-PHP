package Controleur;

public class Citoyen {
	//pas oblige de ressemble dans la bdd
	private int idcitoyen;
	private String nom, prenom, sexe, dateNaissance,lieuNaissance,cpNaissance, adresse,ville, cp,situation, email;
	private String question, reponse;
	
	
	public Citoyen(int idcitoyen, String nom, String prenom, String sexe, String dateNaissance, String lieuNaissance,
			String cpNaissance, String adresse, String ville, String cp, String situation, String email,
			String question, String reponse) 
	{
		super();
		this.idcitoyen = idcitoyen;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
		this.cpNaissance = cpNaissance;
		this.adresse = adresse;
		this.ville = ville;
		this.cp = cp;
		this.situation = situation;
		this.email = email;
		this.question = question;
		this.reponse = reponse;
	}
	
	public Citoyen(String nom, String prenom, String sexe, String dateNaissance, String lieuNaissance,
			String cpNaissance, String adresse, String ville, String cp, String situation, String email,
			String question, String reponse) 
	{
		super();
		this.idcitoyen = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
		this.cpNaissance = cpNaissance;
		this.adresse = adresse;
		this.ville = ville;
		this.cp = cp;
		this.situation = situation;
		this.email = email;
		this.question = question;
		this.reponse = reponse;
	}

	public int getIdcitoyen() {
		return idcitoyen;
	}

	public void setIdcitoyen(int idcitoyen) {
		this.idcitoyen = idcitoyen;
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

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getCpNaissance() {
		return cpNaissance;
	}

	public void setCpNaissance(String cpNaissance) {
		this.cpNaissance = cpNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	
	
	
	

}
