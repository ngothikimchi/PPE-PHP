package Controleur;

public class DemandeMono {
	private int idDemande;
	private String dateDemande,dateRep, etat;
	private int idcitoyen, idTypeD, idEmploye;
	
	public DemandeMono(int idDemande, String dateDemande, String dateRep, String etat, int idcitoyen, int idTypeD,
			int idEmploye) {
		super();
		this.idDemande = idDemande;
		this.dateDemande = dateDemande;
		this.dateRep = dateRep;
		this.etat = etat;
		this.idcitoyen = idcitoyen;
		this.idTypeD = idTypeD;
		this.idEmploye = idEmploye;
	}
	
	public DemandeMono(String dateDemande, String etat, int idcitoyen, int idTypeD,
			int idEmploye) {
		super();
		this.idDemande = 0;
		this.dateDemande = dateDemande;
		this.dateRep = "";
		this.etat = etat;
		this.idcitoyen = idcitoyen;
		this.idTypeD = idTypeD;
		this.idEmploye = idEmploye;
	}

	public int getIdDemande() {
		return idDemande;
	}

	public void setIdDemande(int idDemande) {
		this.idDemande = idDemande;
	}

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getDateRep() {
		return dateRep;
	}

	public void setDateRep(String dateRep) {
		this.dateRep = dateRep;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getIdcitoyen() {
		return idcitoyen;
	}

	public void setIdcitoyen(int idcitoyen) {
		this.idcitoyen = idcitoyen;
	}

	public int getIdTypeD() {
		return idTypeD;
	}

	public void setIdTypeD(int idTypeD) {
		this.idTypeD = idTypeD;
	}

	public int getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}
	
	
	
	
	
	
	
	
	

}
