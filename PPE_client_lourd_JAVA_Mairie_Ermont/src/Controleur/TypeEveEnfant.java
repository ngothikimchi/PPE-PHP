package Controleur;

public class TypeEveEnfant {
	private String codeTypeE, nomTypeE;
	private int ageMin, ageMax;
	private boolean accompagnant;
	
	public TypeEveEnfant(String codeTypeE, String nomTypeE, int ageMin, int ageMax, boolean accompagnant) {
		super();
		this.codeTypeE = codeTypeE;
		this.nomTypeE = nomTypeE;
		this.ageMin = ageMin;
		this.ageMax = ageMax;
		this.accompagnant = accompagnant;
	}

	public String getCodeTypeE() {
		return codeTypeE;
	}

	public void setCodeTypeE(String codeTypeE) {
		this.codeTypeE = codeTypeE;
	}

	public String getNomTypeE() {
		return nomTypeE;
	}

	public void setNomTypeE(String nomTypeE) {
		this.nomTypeE = nomTypeE;
	}

	public int getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}

	public int getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(int ageMax) {
		this.ageMax = ageMax;
	}

	public boolean isAccompagnant() {
		return accompagnant;
	}

	public void setAccompagnant(boolean accompagnant) {
		this.accompagnant = accompagnant;
	}
	
	
	
}
