package Controleur;

public class TypeEve 
{
	private String codeTypeE, nomTypeE;

	public TypeEve(String codeTypeE, String nomTypeE) 
	{
		super();
		this.codeTypeE = codeTypeE;
		this.nomTypeE = nomTypeE;
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
	
	
}
