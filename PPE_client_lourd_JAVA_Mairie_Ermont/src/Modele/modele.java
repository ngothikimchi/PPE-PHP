package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controleur.Association;
import Controleur.Citoyen;
import Controleur.DemandeMono;
import Controleur.DemandePluriel;
import Controleur.EU;
import Controleur.Employe;
import Controleur.Evenement;
import Controleur.Participer;
import Controleur.TypeDemande;
import Controleur.TypeEve;
import Controleur.TypeEveAdulte;
import Controleur.TypeEveEnfant;
import Controleur.User;

public class modele {
	
	private static Bdd uneBdd = new Bdd("localhost","ppe_mairie_lourde","root","root");

	public static User selectWhereUser(String email, String mdp) 
	{
		User unUser =null;
		String requete="select * from user where emailUser ='" + email 
				+"'  and mdpUser ='" + mdp + "';";
		try  
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);	
			if(unResultat.next())
			{
				//repecter les ordres
				unUser = new User(
						unResultat.getInt("idUser"),		
						unResultat.getInt("idRoleUser"),
						unResultat.getString("emailUser"),
						unResultat.getString("mdpUser")
						);				
				
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
			return unUser;
				
	}

	public static void insertCitoyen(Citoyen unCitoyen) 
	{
		String requete ="insert into citoyen values (null, '"
				+ unCitoyen.getNom()+"','" + unCitoyen.getPrenom()+"','"
				+ unCitoyen.getSexe()+"','" + unCitoyen.getDateNaissance()+"','"
				 + unCitoyen.getLieuNaissance()+"','"
				+ unCitoyen.getCpNaissance()+"','" + unCitoyen.getAdresse()+"','"
				+ unCitoyen.getVille()+"','" + unCitoyen.getCp()+"','"
				+ unCitoyen.getSituation()+"','" + unCitoyen.getEmail()+"','"
				+ unCitoyen.getQuestion()+"','" + unCitoyen.getReponse()+"');";
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	
		
	}

	public static ArrayList<Citoyen> selectAllCitoyens() {
		ArrayList<Citoyen> lesCitoyens = new ArrayList<Citoyen>();
		String requete="select * from citoyen ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			//extraction des pilotes: fetchall en PHP
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				//prendre les donnees dans la bdd
				Citoyen unCitoyen = new Citoyen (
						desResultats.getInt("idCit"),
						desResultats.getString("nomCit"),
						desResultats.getString("prenomCit"),
						desResultats.getString("sexeCit"),
						desResultats.getString("dateNaissCit"),
						desResultats.getString("lieuNaissCit"),
						desResultats.getString("cpLieuNaissCit"),						
						desResultats.getString("adresseCit"),
						desResultats.getString("villeCit"),
						desResultats.getString("cpCit"),
						desResultats.getString("situationFamilialeCit"),
						desResultats.getString("emailCit"),
						desResultats.getString("question"),
						desResultats.getString("reponse")						
						);
				
				//on ajoute l'avion dans la liste des avions
				lesCitoyens.add(unCitoyen);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesCitoyens;
	}

// select like citoyen
	public static ArrayList<Citoyen> selectLikeCitoyen(String mot) 
	{
		ArrayList<Citoyen> lesCitoyens = new ArrayList<Citoyen>();
		String requete="select * from citoyen where "
		+ "nomCit like '%" + mot+ "%' or  "
		+ "prenomCit like '%" + mot+ "%' or "
		+ "sexeCit like '%" + mot+ "%' or "
		+ "dateNaissCit like '%" + mot+ "%' or  "
		+ "lieuNaissCit like '%" + mot+ "%' or "
		+ "cpLieuNaissCit like '%" + mot+ "%' or "
		+ "adresseCit like '%" + mot+ "%' or  "
		+ "villeCit like '%" + mot+ "%' or "
		+ "cpCit like '%" + mot+ "%' or "
		+ "situationFamilialeCit like '%" + mot+ "%' or "
		+ "emailCit like '%" +  mot+ "%' or "
		+"question like '%" + mot + "%' or "
		+"reponse like '%"+ mot + "%' ;"
		;
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				Citoyen unCitoyen = new Citoyen  (						
						desResultats.getString("nomCit"),
						desResultats.getString("prenomCit"),
						desResultats.getString("sexeCit"),
						desResultats.getString("dateNaissCit"),
						desResultats.getString("lieuNaissCit"),
						desResultats.getString("cpLieuNaissCit"),						
						desResultats.getString("adresseCit"),
						desResultats.getString("villeCit"),
						desResultats.getString("cpCit"),
						desResultats.getString("situationFamilialeCit"),
						desResultats.getString("emailCit"),
						desResultats.getString("question"),
						desResultats.getString("reponse")						
						
						);
				
				
				lesCitoyens.add(unCitoyen);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesCitoyens;
	}
// delete citoyen
	public static void deleteCitoyen(int idcitoyen) {
			String requete="delete from citoyen where idCit = " + idcitoyen +";"; 
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}
//select where citoyen
	public static Citoyen selectWhereCitoyen(String nom, String prenom, String sexe,
			String dateNaissance, String lieuNaissance, String cpNaissance, String adresse, String ville, String cp,
			String situation, String email, String question, String reponse) {
		
		Citoyen unCitoyen =null;
		String requete="select * from citoyen where nomCit = '" + nom
				+"' and prenomCit='" + prenom
				+"' and sexeCit='" + sexe
				+"' and dateNaissCit='" + dateNaissance
				+"' and lieuNaissCit='" + lieuNaissance
				+"' and cpLieuNaissCit='" + cpNaissance
				+"' and adresseCit='" + adresse
				+"' and villeCit='" + ville
				+"' and cpCit='" + cp
				+"' and situationFamilialeCit='" + situation
				+"' and emailCit ='"+ email
				+"'and question ='" + question
				+"' and reponse ='" + reponse
				+ "';";
		try  
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			
			
			if(unResultat.next())//s'il y a un resultat
			{
				unCitoyen = new Citoyen (
						unResultat.getInt("idCit"),
						unResultat.getString("nomCit"),
						unResultat.getString("prenomCit"),
						unResultat.getString("sexeCit"),
						unResultat.getString("dateNaissCit"),
						unResultat.getString("lieuNaissCit"),
						unResultat.getString("cpLieuNaissCit"),
						unResultat.getString("adresseCit"),
						unResultat.getString("villeCit"),
						unResultat.getString("cpCit"),
						unResultat.getString("situationFamilialeCit"),
						unResultat.getString("emailCit"),
						unResultat.getString("question"),
						unResultat.getString("reponse")						
						);
				
				
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
			return unCitoyen;
				
		
		
	}

	public static void updateCitoyen(Citoyen unCitoyen) {
		String requete="update citoyen set nomCit = '"+ unCitoyen.getNom()
		+ "', prenomCit = '" + unCitoyen.getPrenom()
		+ "', sexeCit ='" + unCitoyen.getSexe()
		+ "', dateNaissCit = '" + unCitoyen.getDateNaissance()
		+ "', lieuNaissCit ='" + unCitoyen.getLieuNaissance()
		+ "', cpLieuNaissCit = '" + unCitoyen.getCpNaissance()
		+ "', adresseCit ='" + unCitoyen.getAdresse()
		+ "', villeCit = '" + unCitoyen.getVille()
		+ "', cpCit ='" + unCitoyen.getCp()
		+ "', situationFamilialeCit = '" + unCitoyen.getSituation()
		+ "', emailCit ='" + unCitoyen.getEmail() +
		"' where idCit ="
				+unCitoyen.getIdcitoyen()
		
		+";";
			
			try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			
			catch(SQLException exp)
			{
				System.out.println("Erreur de requete:" + requete);
			}
	}
	//ASSOCIATION---------------------------------------------------------------------------------------------
			
	public static void deleteAssociation(int idassoc) {
		String requete="delete from association where idAssoc = " + idassoc +";"; 
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
		
	}

	public static ArrayList<Association> selectAllAssociations() {
		ArrayList<Association> lesAssocs = new ArrayList<Association>();
		String requete="select * from association ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			//extraction des association: fetchall en PHP
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				//prendre les donnees dans la bdd
				Association unAssoc = new Association (
						desResultats.getInt("idAssoc"),
						desResultats.getString("nomAssoc"),
						desResultats.getString("adresseAssoc"),
						desResultats.getString("cpAssoc"),
						desResultats.getString("villeAssoc"),
						desResultats.getString("telAssoc")
						);
				
				//on ajoute asso dans la liste des assocs
				lesAssocs.add(unAssoc);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesAssocs;
	}

	public static ArrayList<Association> selectLikeAssociation(String mot) {
		ArrayList<Association> lesAssocs = new ArrayList<Association>();
		String requete="select * from association where "
		+ "nomAssoc like '%" + mot+ "%' or  "
		+ "adresseAssoc like '%" + mot+ "%' or "
		+ "cpAssoc like '%" + mot+ "%' or "
		+ "villeAssoc like '%" + mot+ "%' or  "
		+ "telAssoc like '%" + mot+ "%' ;"
		;
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				Association unAssoc = new Association  (
						
						desResultats.getInt("idAssoc"),
						desResultats.getString("nomAssoc"),
						desResultats.getString("adresseAssoc"),
						desResultats.getString("cpAssoc"),
						desResultats.getString("villeAssoc"),
						desResultats.getString("telAssoc")
						
						);
				
				
				lesAssocs.add(unAssoc);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesAssocs;
	}

	public static void insertAssociation(Association uneAssoc) {
		String requete ="insert into association values (null, '"
				+ uneAssoc.getNom()+"','" + uneAssoc.getAdresse()+"','"
				+ uneAssoc.getCp()+"','" + uneAssoc.getVille()+"','"
				 + uneAssoc.getTel()+"') ;";

		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	
		
	}

	public static Association selectWhereAssoc(String nom, String adresse, String cp, String ville, String tel) 
	{

		Association uneAssoc =null;
		String requete="select * from association where nomAssoc = '" + nom
			+"' and adresseAssoc='" + adresse		
			+"' and cpAssoc='" + cp
			+"' and villeAssoc='" + ville
			+"' and telAssoc='" + tel
			+ "';";
	try  
	{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet unResultat = unStat.executeQuery(requete);
		
		
		if(unResultat.next())//s'il y a un resultat
		{
			uneAssoc = new Association (
					unResultat.getInt("idAssoc"),
					unResultat.getString("nomAssoc"),
					unResultat.getString("adresseAssoc"),
					unResultat.getString("cpAssoc"),
					unResultat.getString("villeAssoc"),
					unResultat.getString("telAssoc")
);
			
			
		}
		unStat.close();
		uneBdd.seDeconnecter();
	}
	
	catch(SQLException exp)
	{
		System.out.println("Erreur de requete:" + requete);
	}
		return uneAssoc;
			
	
	}

	public static void updateAssociation(Association uneAssoc) {
		String requete="update association set nomAssoc = '"+ uneAssoc.getNom()
		+ "', adresseAssoc = '" + uneAssoc.getAdresse()
		+ "', cpAssoc ='" + uneAssoc.getCp()
		+ "', villeAssoc = '" + uneAssoc.getVille()
		+ "', telAssoc ='" + uneAssoc.getTel()
		+"' where idAssoc ="
				+uneAssoc.getIdassoc()
		
		+";";
			
			try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			
			catch(SQLException exp)
			{
				System.out.println("Erreur de requete:" + requete);
			}
		
	}
//TYPE DEMANDE -----------------------------------------------------------------------------------
	public static ArrayList<TypeDemande> selectLikeTypeD(String mot) {
		ArrayList<TypeDemande> lesTypeDs = new ArrayList<TypeDemande>();
		String requete="select * from type_demande where "
		+ "nomTypeDem like '%" + mot+ "%' or  "
		+ "etrePlurielDem like '%" + mot+  "%' ;"
		;
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				TypeDemande uneTypeD = new TypeDemande  (
						desResultats.getInt("idTypeDem"),
						desResultats.getString("nomTypeDem"),
						desResultats.getBoolean("etrePlurielDem")
						);
				
				
				lesTypeDs.add(uneTypeD);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesTypeDs;
	}

	public static ArrayList<TypeDemande> selectAllTypeDs() {
		ArrayList<TypeDemande> lesTypeDs = new ArrayList<TypeDemande>();
		String requete="select * from type_demande ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			//extraction des association: fetchall en PHP
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				//prendre les donnees dans la bdd
				TypeDemande uneTypeD = new TypeDemande (
						desResultats.getInt("idTypeDem"),
						desResultats.getString("nomTypeDem"),
						desResultats.getBoolean("etrePlurielDem")
						
						);
				
				//on ajoute asso dans la liste des assocs
				lesTypeDs.add(uneTypeD);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesTypeDs;
	}

	
	public static void insertTypeD(TypeDemande uneTypeD) {
		String requete ="insert into type_demande values (null, '"
				+ uneTypeD.getNom()+"'," + uneTypeD.isEtrePlurielDem()+") ;";

		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	public static void updateTypeD(TypeDemande uneTypeD) {
		String requete="update type_demande set nomTypeDem = '"+ uneTypeD.getNom()
		+ "', etrePlurielDem = " + uneTypeD.isEtrePlurielDem()
		+" where idTypeDem ="
		+uneTypeD.getId()
		+" ;";
			
			try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			
			catch(SQLException exp)
			{
				System.out.println("Erreur de requete:" + requete);
			}
		
		
	}

	public static TypeDemande selectWheretypeD(String nom, boolean etrePlurielDem) {
		TypeDemande uneTypeD =null;
		String requete="select * from type_demande where nomTypeDem = '" + nom
			+"' and etrePlurielDem=" + etrePlurielDem
			+ ";";
	try  
	{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet unResultat = unStat.executeQuery(requete);
		
		
		if(unResultat.next())//s'il y a un resultat
		{
			uneTypeD = new TypeDemande (
					unResultat.getInt("idTypeDem"),
					unResultat.getString("nomTypeDem"),
					unResultat.getBoolean("etrePlurielDem"));	
		}
		unStat.close();
		uneBdd.seDeconnecter();
	}
	
	catch(SQLException exp)
	{
		System.out.println("Erreur de requete:" + requete);
	}
		return uneTypeD;
			
	}

	public static void deletetypeD(int idTypeD) {
String requete="delete from type_demande where idTypeDem = " + idTypeD +";"; 
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	//EMPLOYE -----------------------------------------------------------
	
	public static void deleteEmploye(int idemploye) {
		String requete="delete from employe where idemploye =" + idemploye; 
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	public static ArrayList<Employe> selectLikeEmploye(String mot) {
		ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
		String requete="select * from employe where "
		+ "nomEmploye like '%" + mot+ "%' or  "
		+ "prenomEmploye like '%" + mot+ "%' or "
		+ "emailEmploye like '%" + mot+ "%' or "
		+ "idServiceEmploye like '%" + mot+ "%' or  "
		+ "idEmploye like '%" + mot+ "%' ;"
		;
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				Employe unEmploye = new Employe  (
						
						desResultats.getInt("idEmploye"),
						desResultats.getInt("idServiceEmploye"),
						desResultats.getString("nomEmploye"),
						desResultats.getString("prenomEmploye"),
						desResultats.getString("emailEmploye")	
						);
				
				
				lesEmployes.add(unEmploye);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesEmployes;
	}

	public static ArrayList<Employe> selectAllEmployes() {
		ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
		String requete="select * from employe ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			//extraction des association: fetchall en PHP
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				//prendre les donnees dans la bdd
					Employe unEmploye = new Employe  (						
						desResultats.getInt("idEmploye"),
						desResultats.getInt("idServiceEmploye"),
						desResultats.getString("nomEmploye"),
						desResultats.getString("prenomEmploye"),
						desResultats.getString("emailEmploye")	
						);
				
				//on ajoute asso dans la liste des assocs
					lesEmployes.add(unEmploye);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesEmployes;
	}

	public static void insertEmploye(Employe unEmploye) {
		String requete ="insert into employe values (null, '"
				+ unEmploye.getNom()+"','" + unEmploye.getPrenom()+"','"
				+ unEmploye.getEmail()+"','" + unEmploye.getIdservice()+"') ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	
		
	}

	public static Employe selectWhereEmploye(int idservice, String nom, String prenom, String email) {
		Employe unEmploye =null;
		String requete="select * from employe where nomEmploye = '" + nom
			+"' and prenomEmploye='" + prenom		
			+"' and emailEmploye='" + email
			+"' and idServiceEmploye='" + idservice	
			+ "';";
	try  
	{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet unResultat = unStat.executeQuery(requete);
		
		//extraction des pilotes: fetchall en PHP
		if(unResultat.next())//s'il y a un resultat
		{
			unEmploye = new Employe (
					unResultat.getInt("idEmploye"),
					unResultat.getInt("idServiceEmploye"),
					unResultat.getString("nomEmploye"),
					unResultat.getString("prenomEmploye"),
					unResultat.getString("emailEmploye"));
	
		}
		unStat.close();
		uneBdd.seDeconnecter();
	}
	
	catch(SQLException exp)
	{
		System.out.println("Erreur de requete:" + requete);
	}
		return unEmploye;
			
	
	}

	public static void updateEmploye(Employe unEmploye) {
		String requete="update employe set nomEmploye = '"+ unEmploye.getNom()
		+ "', prenomEmploye = '" + unEmploye.getPrenom()
		+ "', emailEmploye = '" + unEmploye.getEmail()
		+ "', idServiceEmploye ='" + unEmploye.getIdservice()
		+"' where idEmploye ="
		+unEmploye.getIdemploye()
		+";";
			
			try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			
			catch(SQLException exp)
			{
				System.out.println("Erreur de requete:" + requete);
			}
		
	}

	
//DEMANDE_MONO
	public static void ValiderDemandeMono(int idemande) 
	{
		String requete="update demande_mono set etat = 'Demande acceptée'" + ", dateRep = curdate() "
		+" where idDemande =" + idemande +";";
			
			try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			
			catch(SQLException exp)
			{
				System.out.println("Erreur de requete:" + requete);
			}
		
	}

	public static void RefuserDemandeMono(int idemande) {
		String requete="update demande_mono set etat = 'Demande refusée'"+ ", dateRep = curdate() "
				+" where idDemande =" + idemande +";";
					
					try 
					{
						uneBdd.seConnecter();
						Statement unStat = uneBdd.getMaconnexion().createStatement();
						unStat.execute(requete);
						unStat.close();
						uneBdd.seDeconnecter();
					}
					
					catch(SQLException exp)
					{
						System.out.println("Erreur de requete:" + requete);
					}
		
	}

	public static ArrayList<DemandeMono> selectAllDemandeM() {
		ArrayList<DemandeMono> lesDemandeM = new ArrayList<DemandeMono>();
		String requete="select * from demande_mono ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
						
			while(desResultats.next())
			{
				//prendre les donnees dans la bdd
				DemandeMono uneDemandeM = new DemandeMono(
						desResultats.getInt("idDemande"),
						desResultats.getString("dateDemande"),
						desResultats.getString("dateRep"),
						desResultats.getString("etat"),
						desResultats.getInt("idCit"),
						desResultats.getInt("idTypeDem"),
						desResultats.getInt("idEmploye")
						);
				
				//on ajoute asso dans la liste des assocs
				lesDemandeM.add(uneDemandeM);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesDemandeM;
	}

	public static ArrayList<DemandeMono> selectLikeDemandeM(String mot) {
		ArrayList<DemandeMono> lesDemandeM = new ArrayList<DemandeMono>();
		String requete="select * from demande_mono where "
		+ "dateDemande like '%" + mot+ "%' or  "
		+ "dateRep like '%" + mot+ "%' or "
		+ "etat like '%" + mot+ "%' or "
		+ "idCit like '%" + mot+ "%' or  "
		+ "idEmploye like '%" + mot+ "%' or  "
		+ "idTypeDem like '%" + mot+ "%' ;"
		;
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			//extraction des pilotes: fetchall en PHP
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				DemandeMono uneDemandeM = new DemandeMono(
						desResultats.getInt("idDemande"),
						desResultats.getString("dateDemande"),
						desResultats.getString("dateRep"),
						desResultats.getString("etat"),
						desResultats.getInt("idCit"),
						desResultats.getInt("idTypeDem"),
						desResultats.getInt("idEmploye")
						);
				
				//on ajoute le pilote dans la liste des pilotes
				lesDemandeM.add(uneDemandeM);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesDemandeM;
	}

	public static void insertDemandeM(DemandeMono uneDemandeM) {
		String requete ="insert into demande_mono (idDemande,dateDemande,etat,idCit,idTypeDem,idEmploye) values (null, '"
				+ uneDemandeM.getDateDemande()+"','"+  uneDemandeM.getEtat()+"','" + 
				+ uneDemandeM.getIdcitoyen()+"','" + uneDemandeM.getIdTypeD()+"','"
				 + uneDemandeM.getIdEmploye()+"') ;";

		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	public static DemandeMono selectWhereDemandeM(String dateDemande, String dateRep, String etat, int idcitoyen,
			int idTypeD, int idEmploye) {

		DemandeMono uneDemandeM =null;
		String requete="select * from demande_mono where dateDemande = '" + dateDemande
			+"' and dateRep " + (dateRep.isEmpty() ? " IS NULL" : "='" + dateRep + "'") 		
			+" and etat='" + etat
			+"' and idCit='" + idcitoyen
			+"' and idTypeDem='" + idTypeD
			+"' and idEmploye='" + idEmploye
			+ "';";
	try  
	{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet unResultat = unStat.executeQuery(requete);
		
		//extraction des pilotes: fetchall en PHP
		if(unResultat.next())//s'il y a un resultat
		{
			uneDemandeM = new DemandeMono (
					unResultat.getInt("idDemande"),
					unResultat.getString("dateDemande"),
					unResultat.getString("dateRep"),
					unResultat.getString("etat"),
					unResultat.getInt("idCit"),
					unResultat.getInt("idTypeDem"),
					unResultat.getInt("idEmploye")					
);
			
			
		}
		unStat.close();
		uneBdd.seDeconnecter();
	}
	
	catch(SQLException exp)
	{
		System.out.println("Erreur de requete:" + requete);
	}
		return uneDemandeM;
	}
	
	
	//Demande Pluriel -----------------------------------------------------------------------------------
	public static void RefuserDemandePluriel(int idemande) 
	{
		String requete="update demande_pluriel set etat = 'Demande refusée'"+ ", dateRep = curdate() "
				+" where idDemande =" + idemande +";";
					
					try 
					{
						uneBdd.seConnecter();
						Statement unStat = uneBdd.getMaconnexion().createStatement();
						unStat.execute(requete);
						unStat.close();
						uneBdd.seDeconnecter();
					}
					
					catch(SQLException exp)
					{
						System.out.println("Erreur de requete:" + requete);
					}
		
	}

	public static void ValiderDemandePluriel(int idemande) 
	{
		String requete="update demande_pluriel set etat = 'Demande acceptée'" + ", dateRep = curdate() "
				+" where idDemande =" + idemande +";";
					
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	public static ArrayList<DemandePluriel> selectAllDemandeP() 
	{
		ArrayList<DemandePluriel> lesDemandeP = new ArrayList<DemandePluriel>();
		String requete="select * from demande_pluriel ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
						
			while(desResultats.next())
			{
				//prendre les donnees dans la bdd
				DemandePluriel uneDemandeP = new DemandePluriel(
						desResultats.getInt("idDemande"),
						desResultats.getString("dateDemande"),
						desResultats.getString("dateRep"),	
						desResultats.getString("etat"),
						desResultats.getInt("idCit1"),
						desResultats.getInt("idCit2"),
						desResultats.getInt("idTypeDem"),
						desResultats.getInt("idEmploye")
			
						);
				
				//on ajoute asso dans la liste des assocs
				lesDemandeP.add(uneDemandeP);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesDemandeP;
	}

	public static ArrayList<DemandePluriel> selectLikeDemandeP(String mot) 
	{
		ArrayList<DemandePluriel> lesDemandeP = new ArrayList<DemandePluriel>();
		String requete="select * from demande_pluriel where "
		+ "dateDemande like '%" + mot+ "%' or  "
		+ "dateRep like '%" + mot+ "%' or "
		+ "etat like '%" + mot+ "%' or "
		+ "idCit1 like '%" + mot+ "%' or  "
		+ "idCit2 like '%" + mot+ "%' or  "
		+ "idEmploye like '%" + mot+ "%' or  "
		+ "idTypeDem like '%" + mot+ "%' ;"
		;
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			//extraction des pilotes: fetchall en PHP
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				DemandePluriel uneDemandeP = new DemandePluriel(
						desResultats.getInt("idDemande"),
						desResultats.getString("dateDemande"),
						desResultats.getString("dateRep"),
						desResultats.getString("etat"),
						desResultats.getInt("idCit1"),
						desResultats.getInt("idCit2"),
						desResultats.getInt("idTypeDem"),
						desResultats.getInt("idEmploye")
						
						);
				
				
				lesDemandeP.add(uneDemandeP);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesDemandeP;
	}

	public static DemandePluriel selectWhereDemandeP(String dateDemande, String dateRep, String etat, int idcitoyen1,
			int idcitoyen2, int idTypeD, int idEmploye) 
	{
		DemandePluriel uneDemandeP =null;
		String requete="select * from demande_pluriel where dateDemande = '" + dateDemande
			+"' and dateRep " + (dateRep.isEmpty() ? " IS NULL" : "='" + dateRep + "'") 		
			+" and etat='" + etat
			+"' and idCit1='" + idcitoyen1
			+"' and idCit2='" + idcitoyen2
			+"' and idTypeDem='" + idTypeD
			+"' and idEmploye='" + idEmploye
			+ "';";
	try  
	{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet unResultat = unStat.executeQuery(requete);
		
		
		if(unResultat.next())//s'il y a un resultat
		{
			uneDemandeP = new DemandePluriel (
					unResultat.getInt("idDemande"),
					unResultat.getString("dateDemande"),
					unResultat.getString("dateRep"),
					unResultat.getString("etat"),
					unResultat.getInt("idCit1"),
					unResultat.getInt("idCit2"),
					unResultat.getInt("idTypeDem"),
					unResultat.getInt("idEmploye")
					
			);
		
		}
		unStat.close();
		uneBdd.seDeconnecter();
	}
	
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		return uneDemandeP;
	}

	public static void insertDemandeP(DemandePluriel uneDemandeP) 
	{
		String requete ="insert into demande_pluriel (idDemande,dateDemande,idCit1,idCit2,idTypeDem,idEmploye,etat) values (null, '"
				+ uneDemandeP.getDateDemande()+"','"
				+ uneDemandeP.getIdcitoyen1()+"','" + uneDemandeP.getIdcitoyen2()+"','"+ uneDemandeP.getIdTypeD()+"','"
				 + uneDemandeP.getIdEmploye()+ "','"+uneDemandeP.getEtat() +"') ;";

		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	//TYPE EVENEMENT ENFANT
	
	public static void deletetypeEveEnfant(String codeTypeE) 
	{
	String requete="delete from type_evenement_enfant where codeTypeEve = '" + codeTypeE +"';"; 
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	public static ArrayList<TypeEveEnfant> selectAllTypeEveEnfant() {
		ArrayList<TypeEveEnfant> lesTypeEveEnfants = new ArrayList<TypeEveEnfant>();
		String requete="select * from type_evenement_enfant ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				//prendre les donnees dans la bdd
				TypeEveEnfant uneTypeEveEnfant = new TypeEveEnfant (
						desResultats.getString("codeTypeEve"),
						desResultats.getString("nomTypeEve"),
						desResultats.getInt("trancheAgeMin"),
						desResultats.getInt("trancheAgeMax"),
						desResultats.getBoolean("accompagnant")
						);
				
				//on ajoute l'avion dans la liste des avions
				lesTypeEveEnfants.add(uneTypeEveEnfant);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesTypeEveEnfants;
	}

	public static ArrayList<TypeEveEnfant> selectLikeTypeEveEnfant(String mot) {
		ArrayList<TypeEveEnfant> lesTypeEveEnfants = new ArrayList<TypeEveEnfant>();
		String requete="select * from type_evenement_enfant where "
		+ "codeTypeEve like '%" + mot+ "%' or  "
		+ "nomTypeEve like '%" + mot+ "%' or  "
		+ "trancheAgeMin like '%" + mot+ "%' or  "
		+ "trancheAgeMax like '%" + mot+ "%' or  "
		+ "accompagnant like '%" + mot+ "%' ;"
		;
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				TypeEveEnfant uneTypeEveEnfant = new TypeEveEnfant (						
						desResultats.getString("codeTypeEve"),
						desResultats.getString("nomTypeEve"),
						desResultats.getInt("trancheAgeMin"),
						desResultats.getInt("trancheAgeMax"),
						desResultats.getBoolean("accompagnant")
					);
				
				
				lesTypeEveEnfants.add(uneTypeEveEnfant);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesTypeEveEnfants;
	}

	public static void insertTypeEveEnfant(TypeEveEnfant uneTypeEveEnfant) {
		String requete ="insert into type_evenement_enfant values ('"
				+ uneTypeEveEnfant.getCodeTypeE()+"','" 
				+ uneTypeEveEnfant.getNomTypeE()+"','"
				+ uneTypeEveEnfant.getAgeMin()+"','"
				+ uneTypeEveEnfant.getAgeMax()+"','"
				+ uneTypeEveEnfant.isAccompagnant()
				+ "');";
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	public static void updateTypeEveEnfant(TypeEveEnfant uneTypeEveEnfant) {
		String requete="update type_evenement_enfant set nomTypeEve = '"+ uneTypeEveEnfant.getNomTypeE()
		+ "', trancheAgeMin = '" + uneTypeEveEnfant.getAgeMin()
		+ "', trancheAgeMax = '" + uneTypeEveEnfant.getAgeMax()
		+ "', accompagnant ='" + uneTypeEveEnfant.isAccompagnant()
		+"' where codeTypeEve ="
		+uneTypeEveEnfant.getCodeTypeE()
		+";";
			
			try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			
			catch(SQLException exp)
			{
				System.out.println("Erreur de requete:" + requete);
			}
		
		
	}

	
	//type eve adulte
	
	public static void deletetypeEveAdulte(String codeTypeE) {
			String requete="delete from type_evenement_adulte where codeTypeEve = '" + codeTypeE +"';"; 
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	public static ArrayList<TypeEveAdulte> selectAllTypeEveAdultes() {
		ArrayList<TypeEveAdulte> lesTypeEveAdultes = new ArrayList<TypeEveAdulte>();
		String requete="select * from type_evenement_adulte ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				//prendre les donnees dans la bdd
				TypeEveAdulte uneTypeEveAdulte = new TypeEveAdulte (
						desResultats.getString("codeTypeEve"),
						desResultats.getString("nomTypeEve")	
						);
				
				//on ajoute l'avion dans la liste des avions
				lesTypeEveAdultes.add(uneTypeEveAdulte);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesTypeEveAdultes;
	}

	public static ArrayList<TypeEveAdulte> selectLikeTypeEveAdulte(String mot) {
		ArrayList<TypeEveAdulte> lesTypeEveAdultes = new ArrayList<TypeEveAdulte>();
		String requete="select * from type_evenement_adulte where "
		+ "codeTypeEve like '%" + mot+ "%' or  "
		+ "nomTypeEve like '%" + mot+ "%' ;"
		;
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				TypeEveAdulte uneTypeEveAdulte = new TypeEveAdulte (						
						desResultats.getString("codeTypeEve"),
						desResultats.getString("nomTypeEve")
					);
				
				
				lesTypeEveAdultes.add(uneTypeEveAdulte);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesTypeEveAdultes;
	}

	public static void insertTypeEveAdulte(TypeEveAdulte uneTypeEveAdulte) {
		String requete ="insert into type_evenement_adulte values ('"
				+ uneTypeEveAdulte.getCodeTypeE()+"','" + uneTypeEveAdulte.getNomTypeE()+"');";
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	public static void updateTypeEveAdulte(TypeEveAdulte uneTypeEveAdulte) {
		String requete="update type_evenement_adulte set nomTypeEve = '"+ uneTypeEveAdulte.getNomTypeE() +
		
		"' where codeTypeEve ='"
				+uneTypeEveAdulte.getCodeTypeE()
		
		+"';";
			
			try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			
			catch(SQLException exp)
			{
				System.out.println("Erreur de requete:" + requete);
			}
		
	}
//EVENEMENT
	public static void deleteEvenement(int idE) {
String requete="delete from evenement where idEve = " + idE +";"; 
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	public static ArrayList<TypeEve> selectAllTypeEves() {
		ArrayList<TypeEve> lesTypeEves = new ArrayList<TypeEve>();
		String requete="select * from type_evenement ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				//prendre les donnees dans la bdd
				TypeEve uneTypeEve = new TypeEve (
						desResultats.getString("codeTypeEve"),
						desResultats.getString("nomtTypeEve")	
						);
				
				//on ajoute l'avion dans la liste des avions
				lesTypeEves.add(uneTypeEve);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesTypeEves;
	}

	public static ArrayList<Evenement> selectAllEvenements() {
		ArrayList<Evenement> lesEves = new ArrayList<Evenement>();
		String requete="select * from evenement ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				//prendre les donnees dans la bdd
				Evenement uneEve = new Evenement (
						desResultats.getInt("idEve"),
						desResultats.getString("nomEve"),	
						desResultats.getString("contenuEve"),
						desResultats.getString("adresseEve"),
						desResultats.getString("debutEve"),	
						desResultats.getString("finEve"),
						desResultats.getString("dateFinInscriptionEve"),
						desResultats.getInt("nbParticipantMaxEve"),	
						desResultats.getString("codeTypeEve"),
						desResultats.getInt("idAssocEve")	
						);
				
				//on ajoute l'avion dans la liste des avions
				lesEves.add(uneEve);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesEves;
	}

	

	public static void insertEvenement(Evenement uneEve) {
		String requete ="insert into evenement values (null,'"
				+ uneEve.getNomE()+"','" 
				+ uneEve.getContenuE()+"','"
				+ uneEve.getAdresseE()+"','"
				+ uneEve.getDateDebut()+"','"
				+ uneEve.getDateFin()
				+ uneEve.getDateFinInscription()+"',"
				+ uneEve.getNbMax()+"','"
				+ uneEve.getCodeTypeE() + "',"
				+ uneEve.getIdAssoc()
				+ ");";
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	public static Evenement selectWhereEve(String nomE, String contenuE, String adresseE, 
String dateDebut,
			String dateFin, String dateFinInscription, int nbMax, String codeTypeE, int idAssoc) {
		Evenement uneEve =null;
		String requete="select * from evenement where nomEve = '" + nomE					
			+"' and contenuEve='" + contenuE
			+"' and adresseEve='" + adresseE
			+"' and debutEve='" + dateDebut
			+"' and finEve='" + dateFin
			+"' and debutEve='" + dateDebut
			+"' and dateFinInscriptionEve='" + dateFinInscription
			+"' and nbParticipantMaxEve='" + dateFin
			+"' and codeTypeEve='" + codeTypeE
			+"' and idAssocEve='" + idAssoc
			+ "';";
	try  
	{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet unResultat = unStat.executeQuery(requete);
		
		
		if(unResultat.next())//s'il y a un resultat
		{
			uneEve = new Evenement (
					unResultat.getInt("idDemande"),
					unResultat.getString("nomEve"),	
					unResultat.getString("contenuEve"),
					unResultat.getString("adresseEve"),
					unResultat.getString("debutEve"),	
					unResultat.getString("finEve"),
					unResultat.getString("dateFinInscriptionEve"),
					unResultat.getInt("nbParticipantMaxEve"),	
					unResultat.getString("codeTypeEve"),
					unResultat.getInt("idAssocEve")	
					
			);
		
		}
		unStat.close();
		uneBdd.seDeconnecter();
	}
	
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		return uneEve;
	}

	public static void updateEve(Evenement uneEve) {
		String requete="update evenement set "
		+ "nomEve = '"+ uneEve.getNomE()
		+ "', contenuEve = '" + uneEve.getContenuE()
		+ "', adresseEve = '" + uneEve.getAdresseE()
		+ "debutEve = '"+ uneEve.getDateDebut()
		+ "', finEve = '" + uneEve.getDateFin()
		+ "', dateFinInscriptionEve = '" + uneEve.getDateFinInscription()
		+ "nbParticipantMaxEve = '"+ uneEve.getNbMax()
		+ "', codeTypeEve = '" + uneEve.getCodeTypeE()
		+ "', idAssocEve = " + uneEve.getIdAssoc()
		+" where idEve ="
		+uneEve.getIdE()
		+" ;";
			
			try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			
			catch(SQLException exp)
			{
				System.out.println("Erreur de requete:" + requete);
			}
		
	}

	public static ArrayList<Evenement> selectLikeEvenements(String mot) 
	{
		ArrayList<Evenement> lesEves = new ArrayList<Evenement>();
		String requete="select * from evenement where "
		+ "idEve like '%" + mot+ "%' or  "
		+ "nomEve like '%" + mot+ "%' or "
		+ "contenuEve like '%" + mot+ "%' or "
		+ "adresseEve like '%" + mot+ "%' or  "
		+ "debutEve like '%" + mot+ "%' or  "
		+ "finEve like '%" + mot+ "%' or "
		+ "dateFinInscriptionEve like '%" + mot+ "%' or "
		+ "nbParticipantMaxEve like '%" + mot+ "%' or  "
		+ "codeTypeEve like '%" + mot+ "%' or "
		+ "idAssocEve like '%" + mot+ "%' ;"
		;
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				Evenement uneEve = new Evenement (
						desResultats.getInt("idEve"),
						desResultats.getString("nomEve"),	
						desResultats.getString("contenuEve"),
						desResultats.getString("adresseEve"),
						desResultats.getString("debutEve"),	
						desResultats.getString("finEve"),
						desResultats.getString("dateFinInscriptionEve"),
						desResultats.getInt("nbParticipantMaxEve"),	
						desResultats.getString("codeTypeEve"),
						desResultats.getInt("idAssocEve")	
						);
				
				
				lesEves.add(uneEve);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesEves;
	}
	
	//PARTICIPANT

	

	public static ArrayList<Participer> selectLikeParticipant(String mot) {
		ArrayList<Participer> lesParticipants = new ArrayList<Participer>();
		String requete="select * from participer where "
		+ "idCit like '%" + mot+ "%' or  "
		+ "idEve like '%" + mot+ "%' or "
		+ "dateDemande like '%" + mot+ "%' ;"
		;
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			//extraction des pilotes: fetchall en PHP
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				Participer uneParticipant = new Participer  (
						
						desResultats.getInt("idCit"),
						desResultats.getInt("idEve"),
						desResultats.getString("dateDemande")

						);
				
				//on ajoute le pilote dans la liste des pilotes
				lesParticipants.add(uneParticipant);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesParticipants;
	}


	public static void insertParticipant(Participer unParticipant) {
		String requete ="insert into participer values ("
				+ unParticipant.getIdcitoyen()+"," + unParticipant.getIdE()+",'"
				+ unParticipant.getDateDemande()				
				+"');";
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	
		
	}

	public static void updateParticipant(Participer unParticipant) {
		String requete="update participer set dateDemande = '"+ unParticipant.getDateDemande() +
		"' where idCit ='"
		+unParticipant.getIdcitoyen() + "' and idEve =" + unParticipant.getIdE()		
		+";";
			
			try 
			{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			
			catch(SQLException exp)
			{
				System.out.println("Erreur de requete:" + requete);
			}
		
	}

	public static void deleteParticipant(int idcitoyen, int idE) {
		String requete="delete from participer where idCit = " + idcitoyen +" and idEve = "+idE+ ";"; 
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
	}

	public static ArrayList<Participer> selectAllParticipants() {
		ArrayList<Participer> lesParticipants = new ArrayList<Participer>();
		String requete="select * from participer ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			//extraction des pilotes: fetchall en PHP
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				//prendre les donnees dans la bdd
				Participer unParticipant = new Participer (
						desResultats.getInt("idCit"),
						desResultats.getInt("idEve"),
						desResultats.getString("dateDemande")
						);
				
				//on ajoute l'avion dans la liste des avions
				lesParticipants.add(unParticipant);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesParticipants;
	}
	
//statistique
	public static int countCitoyens() {
		int nbCitoyens =0;
		String requete = "select count(*) as nb from citoyen ;";
		
		try 
		{
			
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nbCitoyens = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
		return nbCitoyens;
	}

	public static int countEmployees() {
		int nbEmployee =0;
		String requete = "select count(*) as nb from employe ;";
		
		try 
		{
			
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nbEmployee = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
		return nbEmployee;
	}

	public static int countAssociations() {
		int nbAssocs =0;
		String requete = "select count(*) as nb from association ;";
		
		try 
		{
			
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nbAssocs = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
		return nbAssocs;
	}

	public static int countTypeDeService() {
		int nbTypeService =0;
		String requete = "select count(*) as nb from type_demande ;";
		
		try 
		{
			
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nbTypeService = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
		return nbTypeService;
	}

	public static int countDemandeValide() {
		int nbDemandeValide =0;
		String requete = "select count(*) as nb from demande_mono where etat ='Demande acceptée' ;";
		
		try 
		{
			
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nbDemandeValide = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
		return nbDemandeValide;
	}

	public static int countEvenements() {
		int nbEvenements =0;
		String requete = "select count(*) as nb from evenement ;";
		
		try 
		{
			
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nbEvenements = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
		return nbEvenements;
	}

	public static int countDemandeRefuse() {
		int nbDemandeRefuse =0;
		String requete = "select count(*) as nb from demande_mono where etat='Demande refusée' ;";
		
		try 
		{
			
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nbDemandeRefuse = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
		
		return nbDemandeRefuse;
	}

	public static ArrayList<EU> selectAllEu() {
		ArrayList<EU> lesEUs = new ArrayList<EU>();
		String requete="select * from V_employe_user;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				
				EU unEu = new EU (
						desResultats.getInt("iduser"),
						desResultats.getInt("idemploye"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("email"),
						desResultats.getString("service")											
						);
				
				
				lesEUs.add(unEu);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesEUs;
	}

	

	

	
	
	

	

	
	






}
