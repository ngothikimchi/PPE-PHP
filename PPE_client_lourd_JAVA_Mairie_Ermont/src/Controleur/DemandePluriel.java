package Controleur;

public class DemandePluriel 
{
	private int idDemande;
	private String dateDemande,dateRep, etat;
	private int idcitoyen1, idcitoyen2, idTypeD, idEmploye;
	
	
	public DemandePluriel(int idDemande, String dateDemande, String dateRep, String etat, int idcitoyen1,
			int idcitoyen2, int idTypeD, int idEmploye) {
		super();
		this.idDemande = idDemande;
		this.dateDemande = dateDemande;
		this.dateRep = dateRep;
		this.etat = etat;
		this.idcitoyen1 = idcitoyen1;
		this.idcitoyen2 = idcitoyen2;
		this.idTypeD = idTypeD;
		this.idEmploye = idEmploye;
	}
	
	public DemandePluriel(String dateDemande, String etat, int idcitoyen1,
			int idcitoyen2, int idTypeD, int idEmploye) 
	{
		super();
		this.idDemande = 0;
		this.dateDemande = dateDemande;
		this.dateRep = "";
		this.etat = etat;
		this.idcitoyen1 = idcitoyen1;
		this.idcitoyen2 = idcitoyen2;
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

	public int getIdcitoyen1() {
		return idcitoyen1;
	}

	public void setIdcitoyen1(int idcitoyen1) {
		this.idcitoyen1 = idcitoyen1;
	}

	public int getIdcitoyen2() {
		return idcitoyen2;
	}

	public void setIdcitoyen2(int idcitoyen2) {
		this.idcitoyen2 = idcitoyen2;
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
