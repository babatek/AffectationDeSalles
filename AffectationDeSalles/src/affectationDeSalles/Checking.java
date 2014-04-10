package affectationDeSalles;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

 
public class Checking extends JFrame implements ActionListener{
  
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();

	@SuppressWarnings("rawtypes")
  	
	private JComboBox listeLigue = new JComboBox();
	private JLabel ligue = new JLabel("Ligue");
	private JButton cheker = new JButton("CHECKER");
	private JButton retour = new JButton("R");
	private JLabel salle = new JLabel(""); 
	
	private JPanel top = new JPanel();


	public void actionPerformed(ActionEvent e)
	{

	}

	@SuppressWarnings("unchecked")
	public Checking(){
	  
		/*Construit la fenetre en y integrant la liste deroulante*/
    
		this.setTitle("Checking");
		this.setSize(320, 320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setLayout(new BorderLayout());
		
		top.setBackground(Color.white);
		top.setLayout(null);
		ligue.setBounds(40, 20, 80, 30);
		top.add(ligue);
		listeLigue.setBounds(110,20, 150, 30);
		top.add(listeLigue);
		
		salle.setBounds(110, 150, 100, 20);
		top.add(salle);
		
		retour.setBounds(1, 1, 10, 10);
		top.add(retour);
		
		retour.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Accueil accueil = new Accueil();
				accueil.affiche();
				dispose();
			}	
		}
		);
		
		cheker.setBounds(35, 260, 250, 20);
		top.add(cheker);
		
		cheker.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				RequeteSQL salle2 = new RequeteSQL();
				salle.setText(salle2.salle_affectee(listeLigue.getSelectedIndex()+1));
			}	
		}	
		);
		
		container.add(top);
		this.setContentPane(container);
		
		RequeteSQL nomLigue = new RequeteSQL();
		
		/*Ajoute des objet dans la liste*/
		
		for (int i = 1; i <= 5; i++)
		{
			listeLigue.addItem(nomLigue.toString(i, ligue.getText(), "nom_ligue"));
		}	
	}
	
	public void affiche()
	{
		setVisible(true);
	}	
	
	public static void main(String[] args)
	{
	
	}
}