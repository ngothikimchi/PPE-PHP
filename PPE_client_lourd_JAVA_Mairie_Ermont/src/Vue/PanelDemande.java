package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PanelDemande extends PanelDeBase implements ActionListener
	{
	private JButton btDemandeMono= new JButton("Gestion Demande Mono");
	private JButton btDemandePluriel= new JButton("Gestion Demande Pluriel");
	private PanelDemandeMono unPanelDemandeMono= new PanelDemandeMono();
	private PanelDemandePluriel unPanelDemandePluriel= new PanelDemandePluriel();
	
		public PanelDemande ()
		{
			super(new Color(195, 232, 199));
			this.btDemandeMono.setBounds(250,30,200,30);
			this.btDemandeMono.setBackground(new Color(243, 240, 215));
			this.btDemandeMono.setFont(new Font("Verdana", Font.BOLD, 12));
			
			this.btDemandePluriel.setBounds(480,30,250,30);
			this.btDemandePluriel.setBackground(new Color(243, 240, 215));
			this.btDemandePluriel.setFont(new Font("Verdana", Font.BOLD, 12));
			
			this.add(this.btDemandeMono);
			this.add(this.btDemandePluriel);
			
			
			this.add(this.unPanelDemandeMono);
			this.add(this.unPanelDemandePluriel);
			
			 unPanelDemandeMono.setVisible(false);
	         unPanelDemandePluriel.setVisible(false);
			
			
			this.btDemandeMono.addActionListener(this);
			this.btDemandePluriel.addActionListener(this);
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == this.btDemandeMono)				
			{
				 unPanelDemandeMono.setVisible(true);
		         unPanelDemandePluriel.setVisible(false);
			}
			else if(e.getSource() == this.btDemandePluriel)
			{
				 unPanelDemandeMono.setVisible(false);
		         unPanelDemandePluriel.setVisible(true);
			}
				
		}
	
	}
