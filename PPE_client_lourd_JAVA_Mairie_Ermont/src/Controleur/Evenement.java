package Controleur;

public class Evenement {
	private int idE;
	private String nomE,contenuE, adresseE, dateDebut, dateFin, dateFinInscription;
	private int nbMax;
	private String codeTypeE;
	private int idAssoc;
	public Evenement(int idE, String nomE, String contenuE, String adresseE, String dateDebut, String dateFin,
			String dateFinInscription, int nbMax, String codeTypeE, int idAssoc) {
		super();
		this.idE = idE;
		this.nomE = nomE;
		this.contenuE = contenuE;
		this.adresseE = adresseE;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateFinInscription = dateFinInscription;
		this.nbMax = nbMax;
		this.codeTypeE = codeTypeE;
		this.idAssoc = idAssoc;
	}
	
	public Evenement(String nomE, String contenuE, String adresseE, String dateDebut, String dateFin,
			String dateFinInscription, int nbMax, String codeTypeE, int idAssoc) {
		super();
		this.idE = 0;
		this.nomE = nomE;
		this.contenuE = contenuE;
		this.adresseE = adresseE;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateFinInscription = dateFinInscription;
		this.nbMax = nbMax;
		this.codeTypeE = codeTypeE;
		this.idAssoc = idAssoc;
	}

	public int getIdE() {
		return idE;
	}

	public void setIdE(int idE) {
		this.idE = idE;
	}

	public String getNomE() {
		return nomE;
	}

	public void setNomE(String nomE) {
		this.nomE = nomE;
	}

	public String getContenuE() {
		return contenuE;
	}

	public void setContenuE(String contenuE) {
		this.contenuE = contenuE;
	}

	public String getAdresseE() {
		return adresseE;
	}

	public void setAdresseE(String adresseE) {
		this.adresseE = adresseE;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getDateFinInscription() {
		return dateFinInscription;
	}

	public void setDateFinInscription(String dateFinInscription) {
		this.dateFinInscription = dateFinInscription;
	}

	public int getNbMax() {
		return nbMax;
	}

	public void setNbMax(int nbMax) {
		this.nbMax = nbMax;
	}

	public String getCodeTypeE() {
		return this.codeTypeE;
	}

	public void setCodeTypeE(String codeTypeE) {
		this.codeTypeE = codeTypeE;
	}

	public int getIdAssoc() {
		return idAssoc;
	}

	public void setIdAssoc(int idAssoc) {
		this.idAssoc = idAssoc;
	}
	
	
	
}
