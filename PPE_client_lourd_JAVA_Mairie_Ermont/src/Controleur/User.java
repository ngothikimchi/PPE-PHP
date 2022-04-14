package Controleur;

public class User {
	private int iduser,idrole;
	private String email, mdp;
	public User(int iduser, int idrole, String email, String mdp) {
		super();
		this.iduser = iduser;
		this.idrole = idrole;
		this.email = email;
		this.mdp = mdp;
	}
	
	public User(int idrole, String email, String mdp) {
		super();
		this.iduser = 0;
		this.idrole = idrole;
		this.email = email;
		this.mdp = mdp;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getIdrole() {
		return idrole;
	}

	public void setIdrole(int idrole) {
		this.idrole = idrole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	

}
