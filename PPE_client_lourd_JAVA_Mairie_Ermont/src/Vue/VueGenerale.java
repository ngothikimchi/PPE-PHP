package Vue;

import javax.swing.*;

import Controleur.Mairie_Ermont;
import Controleur.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class VueGenerale extends JFrame implements ActionListener{
	private JButton btQuitter = new JButton("Déconnexion");
	private JButton btProfil = new JButton("Profil");
	private JButton btCitoyens = new JButton("Citoyens");
	private JButton btEmployes = new JButton("Employes");
	private JButton btAssociations = new JButton("Associations");
	private JButton btTypeService = new JButton("Service");
	//-------------------------------------------------
	private JButton btDemandeGenerale = new JButton("Demande");
	//-----------------------------------------------------
	//private JButton btDemandeMono = new JButton("Demande mono");
	//private JButton btDemandePluriel = new JButton("Demande Pluriel");
	//-------------------------------------------------
	private JButton btTypeEve = new JButton("G-Type Evenement");
	//-----------------------------------------------------
	
	//private JButton btTypeEveEnfant = new JButton("Type Eve Enfant");
	//private JButton btTypeEveAdulte = new JButton("Type Eve Adulte");
	private JButton btEvenements = new JButton("Evenements");
	private JButton btParticipantEve = new JButton("Participants");
	private JButton btStats = new JButton("Statistique");
	private JButton btBord = new JButton("Tableau de bord");
	
	/*************************Les Panels *****/
	private JPanel panelMenu = new JPanel();
	private JPanel panelProfil = new JPanel();
	private JPanel panelProfilChildLeft = new JPanel();
	
	private static PanelCitoyen unPanelCitoyen= new PanelCitoyen();
	private static PanelAssociation unPanelAssoc= new PanelAssociation();
	private static PanelTypeDemande unPanelTypeD= new PanelTypeDemande();
	private static PanelEmploye unPanelEmploye= new PanelEmploye();
	private static PanelDemandeMono unPanelDemandeMono= new PanelDemandeMono();
	private static PanelDemandePluriel unPanelDemandePluriel= new PanelDemandePluriel();
	private static PanelTypeEveEnfant unPanelTypeEveEnfant= new PanelTypeEveEnfant();
	private static PanelTypeEveAdulte unPanelTypeEveAdulte= new PanelTypeEveAdulte();
	private static PanelEvenement unPanelEvenement= new PanelEvenement();
	private static PanelParticipant unPanelParticipant= new PanelParticipant();
	private static PanelDemande unPanelDemande= new PanelDemande(); 
	private static PanelTypeEve unPanelTypeEve= new PanelTypeEve(); 
	private static PanelStats unPanelStats= new PanelStats(); 
	private static PanelDeBord unPanelBord= new PanelDeBord(); 

	
	
	public VueGenerale(User unUser)
	{
		this.setTitle("GestErmont ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(245, 242, 225));
		this.setBounds(300, 300, 1000, 500);
		this.setLayout(null);
		
		
		//construction du panel menu
		this.panelMenu.setLayout(new GridLayout(2,7));
		this.panelMenu.setBounds(20,10,950,50);
		this.panelMenu.setBackground(new Color(238, 237, 222));
		this.panelMenu.add(this.btProfil);
		//this.btProfil.setForeground(Color.RED);
		this.btProfil.setBackground(new Color(213, 223, 247));
		this.btProfil.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.panelMenu.add(this.btCitoyens);
		this.btCitoyens.setBackground(new Color(213, 223, 247));
		this.btCitoyens.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.panelMenu.add(this.btEmployes);
		this.btEmployes.setBackground(new Color(213, 223, 247));
		this.btEmployes.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.panelMenu.add(this.btAssociations);
		this.btAssociations.setBackground(new Color(213, 223, 247));
		this.btAssociations.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.panelMenu.add(this.btTypeService);
		this.btTypeService.setBackground(new Color(213, 223, 247));
		this.btTypeService.setFont(new Font("Verdana", Font.BOLD, 12));
		//--------------------------------------
		this.panelMenu.add(this.btDemandeGenerale);
		this.btDemandeGenerale.setBackground(new Color(213, 223, 247));
		this.btDemandeGenerale.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.panelMenu.add(this.btTypeEve);
		this.btTypeEve.setBackground(new Color(213, 223, 247));
		this.btTypeEve.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.panelMenu.add(this.btEvenements);
		this.btEvenements.setBackground(new Color(213, 223, 247));
		this.btEvenements.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.panelMenu.add(this.btParticipantEve);
		this.btParticipantEve.setBackground(new Color(213, 223, 247));
		this.btParticipantEve.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.panelMenu.add(this.btStats);
		this.btStats.setBackground(new Color(213, 223, 247));
		this.btStats.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.panelMenu.add(this.btBord);
		this.btBord.setBackground(new Color(213, 223, 247));
		this.btBord.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.panelMenu.add(this.btQuitter);
		this.btQuitter.setBackground(new Color(213, 223, 247));
		this.btQuitter.setFont(new Font("Verdana", Font.BOLD, 12));
		
		this.add(this.panelMenu);
		
		
		//construction du panel profil
		this.panelProfil.setLayout(new GridLayout(1,2));
		this.panelProfil.setBounds(30,100,930,320);
		this.panelProfil.setBackground(new Color(213, 223, 247));
		this.panelProfil.setVisible(false);
		
        
		//------------------------------------------
		this.panelProfilChildLeft.setLayout(new GridLayout(4,1));
		this.panelProfilChildLeft.setBounds(50,150,500,170);
		this.panelProfilChildLeft.setBackground(new Color(50, 47, 128));
		
		JLabel textUser1 = new JLabel("*****  BIENVENU AU SITE INTRANET DE LA MAIRIE  *******");
		textUser1.setForeground(Color.white);
		textUser1.setFont(new Font("Verdana", Font.PLAIN,14));
		this.panelProfilChildLeft.add(textUser1);
		
		JLabel textUser2 = new JLabel("Bonjour!");
		textUser2.setForeground(Color.white);
		textUser2.setFont(new Font("Verdana", Font.PLAIN,12));
		this.panelProfilChildLeft.add(textUser2);
		
		JLabel textUser3 = new JLabel("Email de l'user: " + unUser.getEmail());
		textUser3.setForeground(Color.white);
		textUser3.setFont(new Font("Verdana", Font.PLAIN,12));
		this.panelProfilChildLeft.add(textUser3);
		
		JLabel textUser4 = new JLabel("Role de l'user: " + unUser.getIdrole());
		textUser4.setForeground(Color.white);
		textUser4.setFont(new Font("Verdana", Font.PLAIN,12));
		this.panelProfilChildLeft.add(textUser4);
		
/*
		this.panelProfilChildLeft.add(new JLabel("BIENVENU AU SITE INTRANET DE LA MAIRIE"));
		this.panelProfilChildLeft.add(new JLabel("Bonjour !!! "));
		this.panelProfilChildLeft.add(new JLabel("Email de l'user:" + unUser.getEmail()));
		this.panelProfilChildLeft.add(new JLabel("Role de l'user:" + unUser.getIdrole()));*/
		this.panelProfil.add(this.panelProfilChildLeft);
		
		ImageIcon User = new ImageIcon("src/images/mairie.jpg");
		JLabel lbUser = new JLabel(User);
		lbUser.setBounds(570,150,380,200);
		//this.add(lbUser);
		this.panelProfil.add(lbUser);
		
		
		
		this.add(this.panelProfil);
		//insertion des panels d'administration: Pilote/avion/vol
		
		this.add(unPanelCitoyen);
		this.add(unPanelAssoc);
		this.add(unPanelTypeD);
		this.add(unPanelEmploye);
		//------------------------
		this.add(unPanelDemande);
		//------------------------
		//this.add(unPanelDemandeMono);
		//this.add(unPanelDemandePluriel);
		this.add(unPanelTypeEve);
		this.add(unPanelEvenement);
		this.add(unPanelParticipant);
		this.add(unPanelStats);
		this.add(unPanelBord);
				
		
		//rendre les boutons cliquables
		this.btQuitter.addActionListener(this);
		this.btCitoyens.addActionListener(this);
		this.btProfil.addActionListener(this);
		this.btBord.addActionListener(this);
		this.btAssociations.addActionListener(this);
		this.btTypeService.addActionListener(this);
		this.btEmployes.addActionListener(this);
		
		this.btDemandeGenerale.addActionListener(this);
		this.btTypeEve.addActionListener(this);
		//this.btDemandeMono.addActionListener(this);
		//this.btDemandePluriel.addActionListener(this);
		//this.btTypeEveEnfant.addActionListener(this);
		//this.btTypeEveAdulte.addActionListener(this);
		this.btEvenements.addActionListener(this);
		this.btParticipantEve.addActionListener(this);
		this.btStats.addActionListener(this);
		this.btBord.addActionListener(this);
		
		this.setVisible(true);

	}



	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btQuitter)
		{
			Mairie_Ermont.fermerVueGenerale();
			Mairie_Ermont.rendreVisibleVueConnexion(true);
		
		}
		else if(e.getSource()== this.btProfil) {
            this.panelProfil.setVisible(true);
            unPanelCitoyen.setVisible(false);
            unPanelAssoc.setVisible(false);
            unPanelTypeD.setVisible(false);
            unPanelEmploye.setVisible(false);
            this.unPanelTypeEve.setVisible(false);
            unPanelTypeEveEnfant.setVisible(false);
            unPanelTypeEveAdulte.setVisible(false);
            unPanelEvenement.setVisible(false);
            unPanelParticipant.setVisible(false);
            unPanelDemande.setVisible(false);
            unPanelStats.setVisible(false);
            unPanelBord.setVisible(false);
        }
		else if(e.getSource()== this.btCitoyens) 
		{
            this.panelProfil.setVisible(false);
            unPanelCitoyen.setVisible(true);
            unPanelAssoc.setVisible(false);    
            unPanelTypeD.setVisible(false);
            unPanelDemande.setVisible(false);
            unPanelEmploye.setVisible(false);
            unPanelTypeEveEnfant.setVisible(false);
            unPanelTypeEveAdulte.setVisible(false);
            unPanelDemande.setVisible(false);
            unPanelStats.setVisible(false);
            unPanelBord.setVisible(false);
            
        }
		else if(e.getSource()== this.btAssociations) 
		{
            this.panelProfil.setVisible(false);
            unPanelCitoyen.setVisible(false);
            unPanelAssoc.setVisible(true); 
            unPanelTypeD.setVisible(false);
            unPanelEmploye.setVisible(false);           
            unPanelTypeEveEnfant.setVisible(false);
            unPanelTypeEveAdulte.setVisible(false);
            unPanelDemande.setVisible(false);
            unPanelStats.setVisible(false);
            unPanelBord.setVisible(false);
        }
		else if(e.getSource()== this.btTypeService) 
		{
            this.panelProfil.setVisible(false);
            unPanelCitoyen.setVisible(false);
            unPanelAssoc.setVisible(false);   
            unPanelTypeD.setVisible(true);
            unPanelEmploye.setVisible(false);
            unPanelTypeEveEnfant.setVisible(false);
            unPanelTypeEveAdulte.setVisible(false);
            unPanelEvenement.setVisible(false);
            unPanelParticipant.setVisible(false);
            unPanelDemande.setVisible(false);
            unPanelStats.setVisible(false);
            unPanelBord.setVisible(false);
        }
		else if(e.getSource()== this.btEmployes) 
		{
            this.panelProfil.setVisible(false);
            unPanelCitoyen.setVisible(false);
            unPanelAssoc.setVisible(false);   
            unPanelTypeD.setVisible(false);
            unPanelEmploye.setVisible(true);         
            unPanelTypeEveEnfant.setVisible(false);
            unPanelTypeEveAdulte.setVisible(false);
            unPanelEvenement.setVisible(false);
            unPanelParticipant.setVisible(false);
            unPanelDemande.setVisible(false);
            unPanelStats.setVisible(false);
            unPanelBord.setVisible(false);
        }
		
		
		else if(e.getSource()== this.btTypeEve) 
		{
            this.panelProfil.setVisible(false);
            unPanelCitoyen.setVisible(false);
            unPanelAssoc.setVisible(false);   
            unPanelTypeD.setVisible(false);
            unPanelEmploye.setVisible(false);         
            unPanelTypeEve.setVisible(false);
            unPanelDemande.setVisible(false);
            unPanelTypeEve.setVisible(true);
            unPanelEvenement.setVisible(false);
            unPanelParticipant.setVisible(false);
            unPanelStats.setVisible(false);
            unPanelBord.setVisible(false);
        }
		
		else if(e.getSource()== this.btEvenements) 
		{
            this.panelProfil.setVisible(false);
            unPanelCitoyen.setVisible(false);
            unPanelAssoc.setVisible(false);   
            unPanelTypeD.setVisible(false);
            unPanelEmploye.setVisible(false);
            unPanelTypeEve.setVisible(false);
            unPanelEvenement.setVisible(true);
            unPanelParticipant.setVisible(false);
            unPanelDemande.setVisible(false);
            unPanelStats.setVisible(false);
            unPanelBord.setVisible(false);
        }
		else if(e.getSource()== this.btParticipantEve) 
		{
            this.panelProfil.setVisible(false);
            unPanelCitoyen.setVisible(false);
            unPanelAssoc.setVisible(false);   
            unPanelTypeD.setVisible(false);
            unPanelEmploye.setVisible(false);
            unPanelTypeEve.setVisible(false);        
            unPanelEvenement.setVisible(false);
            unPanelParticipant.setVisible(true);
            unPanelDemande.setVisible(false);
            unPanelStats.setVisible(false);
            unPanelBord.setVisible(false);
        }
		
		else if(e.getSource()== this.btDemandeGenerale) 
		{
            this.panelProfil.setVisible(false);
            unPanelCitoyen.setVisible(false);
            unPanelAssoc.setVisible(false);   
            unPanelTypeD.setVisible(false);
            unPanelEmploye.setVisible(false);            
            unPanelDemande.setVisible(true);
            unPanelTypeEve.setVisible(false);
            unPanelEvenement.setVisible(false);
            unPanelParticipant.setVisible(false);
            unPanelStats.setVisible(false);
            unPanelBord.setVisible(false);
        }
		else if(e.getSource()== this.btStats) 
		{
            this.panelProfil.setVisible(false);
            unPanelCitoyen.setVisible(false);
            unPanelAssoc.setVisible(false);   
            unPanelTypeD.setVisible(false);
            unPanelEmploye.setVisible(false);            
            unPanelDemande.setVisible(false);
            unPanelTypeEve.setVisible(false);
            unPanelEvenement.setVisible(false);
            unPanelParticipant.setVisible(false);
            unPanelStats.setVisible(true);
            unPanelBord.setVisible(false);
        }
		else if(e.getSource()== this.btBord) 
		{
            this.panelProfil.setVisible(false);
            unPanelCitoyen.setVisible(false);
            unPanelAssoc.setVisible(false);   
            unPanelTypeD.setVisible(false);
            unPanelEmploye.setVisible(false);            
            unPanelDemande.setVisible(false);
            unPanelTypeEve.setVisible(false);
            unPanelEvenement.setVisible(false);
            unPanelParticipant.setVisible(false);
            unPanelStats.setVisible(false);
            unPanelBord.setVisible(true);
        }
		
	}
}
