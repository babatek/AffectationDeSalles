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

 
public class Affectation extends JFrame {
  
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
  
	/*Construit la liste déroulante*/
	
	private JLabel label1 = new JLabel("Ligue");
	private JLabel label2 = new JLabel("Salle");
	JLabel affectation = new JLabel("");
	
	@SuppressWarnings("rawtypes")
	private JComboBox listeLigue = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox listeSalle = new JComboBox();
	
	private JButton affecter = new JButton("AFFECTER");
	private JButton desaffecter = new JButton("DESAFFECTER");	
	private JButton retour = new JButton("Retour");


	@SuppressWarnings("unchecked")
	public Affectation(){
	  
		/*Construit la fenetre en y integrant la liste deroulante*/
		this.setTitle("Affectation");
		this.setSize(320, 320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setLayout(new BorderLayout());

		JPanel top = new JPanel();
		top.setBackground(Color.white);
		top.setLayout(null);
		
		label1.setBounds(40, 20, 80, 30);
		listeLigue.setBounds(110,20, 150, 30);
		top.add(label1);
		top.add(listeLigue);
    
		label2.setBounds(40, 60, 80, 30);
		listeSalle.setBounds(110, 60, 150, 30);
		top.add(label2);
		top.add(listeSalle);
		
		affectation.setBounds(30, 110, 280, 30);
		top.add(affectation);
		
		retour.setBounds(1, 1, 10, 10);
		container.add(retour);
		
		retour.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Accueil choix = new Accueil();
				choix.affiche();
				dispose();
			}
		}
		);

		desaffecter.setBounds(35, 240, 250, 20);
		top.add(desaffecter);
		
		desaffecter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				RequeteSQL ajout = new RequeteSQL();
				RequeteSQL ligue = new RequeteSQL();
				
				if (ajout.toString(listeLigue.getSelectedIndex()+1, label1.getText(), "salle_affectee").equals("non affectée"))
					affectation.setText("La " + ligue.toString(listeLigue.getSelectedIndex()+1, "ligue", "nom_ligue") +
					" n'a pas de salle affectée.");
				else	
				{
					listeSalle.addItem(ajout.toString(listeLigue.getSelectedIndex()+1, label1.getText(), "salle_affectee"));
					
					affectation.setText("La salle de la " +
					ligue.toString(listeLigue.getSelectedIndex()+1, "ligue", "nom_ligue") +
					" a été desaffectée.");
				}	
				ligue.desaffecter(listeLigue.getSelectedIndex()+1);
			}	
		}	
		);
		
		affecter.setBounds(35, 260, 250, 20);
		top.add(affecter);
		
		affecter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				RequeteSQL sup = new RequeteSQL();
				RequeteSQL salle = new RequeteSQL();
				
				if (sup.toString(listeLigue.getSelectedIndex()+1, "ligue", "salle_affectee").equals("non affectée"))
				{
					affectation.setText("La " +
					salle.toString(sup.idSalle(listeSalle.getSelectedItem().toString()), "salle", "nom_salle") +
					" a été affectée à la " + salle.toString(listeLigue.getSelectedIndex()+1, "ligue", "nom_ligue"));  
					
					salle.update_table(listeLigue.getSelectedIndex()+1, salle.idSalle(listeSalle.getSelectedItem().toString()));
					listeSalle.removeItem(listeSalle.getSelectedItem());
				}	
				else
					affectation.setText("La " +
					salle.toString(listeLigue.getSelectedIndex()+1, "ligue", "nom_ligue") +
					" a déjà une salle d'affectée.");
			}	
		}	
		);
		
		container.add(top);
		this.setContentPane(container);
		
		RequeteSQL sql = new RequeteSQL();
		
		/*Ajoute des objet dans la liste*/
		
		for (int i = 1; i <= 5; i++)    {
			
			listeLigue.addItem(sql.toString(i, label1.getText(), "nom_ligue"));
			
			if (disponible(sql.toString(i, label2.getText(), "nom_salle")))
				listeSalle.addItem(sql.toString(i, label2.getText(), "nom_salle"));
		}	
	}
  
	public boolean disponible (String salle)
	{
		boolean dispo = true;
		RequeteSQL salleAffectee = new RequeteSQL();
		
		for (int i = 1; i <= 5; i++)
			if (salle.equals(salleAffectee.toString(i, label1.getText(), "salle_affectee")))
				dispo = false;
		
		return dispo;
	}	
  
	public void affiche()
	{
		setVisible(true);
	}	

	public static void main(String[] args)
	{
	
	}
}