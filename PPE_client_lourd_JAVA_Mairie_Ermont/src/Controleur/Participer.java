package Controleur;

public class Participer {
	private int idcitoyen, idE;
	private String dateDemande;
	public Participer(int idcitoyen, int idE, String dateDemande) 
	{
		super();
		this.idcitoyen = idcitoyen;
		this.idE = idE;
		this.dateDemande = dateDemande;
	}
	public int getIdcitoyen() {
		return idcitoyen;
	}
	public void setIdcitoyen(int idcitoyen) {
		this.idcitoyen = idcitoyen;
	}
	public int getIdE() {
		return idE;
	}
	public void setIdE(int idE) {
		this.idE = idE;
	}
	public String getDateDemande() {
		return dateDemande;
	}
	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}
	
	
}
