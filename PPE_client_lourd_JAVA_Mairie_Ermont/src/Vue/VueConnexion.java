package Vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controleur.Mairie_Ermont;
import Controleur.User;

public class VueConnexion extends JFrame implements ActionListener, KeyListener{
	
	private JPanel panelConnexion = new JPanel();
	private JButton btSeConnecter = new JButton("Se Connecter");
	private JButton btAnnuler = new JButton("Annuler");
	private JTextField txtEmail = new JTextField("a@gmail.com");
	private JPasswordField txtMdp = new JPasswordField("123");
	
	public VueConnexion ()
	{
		this.setTitle("gestErmont");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// change taille interface---------------
		this.setResizable(true);
		this.getContentPane().setBackground(new Color(252, 242, 216));
		this.setBounds(300, 300, 600, 250);
		// change taille interface---------------
		
		this.setLayout(new GridBagLayout());
		
		this.panelConnexion.setLayout(new GridLayout(3,2));
		this.panelConnexion.setBounds(300,40,260,120);
		this.panelConnexion.setBackground(new Color(252, 242, 216));
		
		// change taille interface---------------
		
		GridBagConstraints gbcConnexion = new GridBagConstraints();
		gbcConnexion.fill = GridBagConstraints.CENTER;
		gbcConnexion.insets = new Insets(20,20,20,20);
		
		GridBagConstraints gbcLogo = new GridBagConstraints();
		gbcLogo.fill = GridBagConstraints.CENTER;
		gbcLogo.insets = new Insets(20,20,20,20);
        
		//------------------------------------------
		

		//installation du logo 
		ImageIcon leLogo = new ImageIcon("src/images/logo.png");
		JLabel lbLogo = new JLabel(leLogo);
		lbLogo.setBounds(20,40,250,150);
		this.add(lbLogo, gbcLogo);
		
		
		this.panelConnexion.add(new JLabel("Email : "));
		this.panelConnexion.add(this.txtEmail);
		
		this.panelConnexion.add(new JLabel("MDP : "));
		this.panelConnexion.add(this.txtMdp);
		
		this.panelConnexion.add(this.btAnnuler);
		this.btAnnuler.setBackground(new Color(206, 229, 208));
		this.panelConnexion.add(this.btSeConnecter);
		this.btSeConnecter.setBackground(new Color(206, 229, 208));
		
		this.add(this.panelConnexion, gbcConnexion);		
		
		this.btAnnuler.addActionListener(this);
		
		this.btSeConnecter.addActionListener(this);
		
		//rendre les boutons écoutables
		this.txtEmail.addKeyListener(this);
		this.btSeConnecter.addKeyListener(this);
		
		this.setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btAnnuler)
		{
			this.txtEmail.setText("");//set empty
			this.txtMdp.setText("");
		}
		else if (e.getSource() == this.btSeConnecter)
		{
			traitement();
		}
		
	}

	public void traitement() {
		String email =this.txtEmail.getText();
		String mdp = new String (this.txtMdp.getPassword());
		
		//vérification en BDD de l'user
		User unUser =Mairie_Ermont.selectWhereUser(email, mdp);
		if(unUser == null)
		{
			JOptionPane.showMessageDialog(this,"Veuillez vérifier vos indentifiants");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
			
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Bienvenu MR/MM " + unUser.getEmail()
			+ "\n Vous avez le drole: "+ unUser.getIdrole());
			//appel de la vue générale
			//instancier la vue générale 
			Mairie_Ermont.instancierVueGenerale(unUser);
		
			//cacher la vue connexion 
			Mairie_Ermont.rendreVisibleVueConnexion(false);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			traitement();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
	
	
}
