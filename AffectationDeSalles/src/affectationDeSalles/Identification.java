package affectationDeSalles;



import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class Identification extends JFrame implements ActionListener{
	
	private JPanel container = new JPanel();

	private JLabel label1 = new JLabel("Login");
	private JLabel label2 = new JLabel("Password");
	private static JTextField login = new JTextField("login");
	private static JTextField password = new JTextField("password");

	private JButton connexion = new JButton("CONNEXION");
	private JButton retour = new JButton("R");

	private static RequeteSQL login2 = new RequeteSQL();
	private static RequeteSQL password2 = new RequeteSQL();

	public void actionPerformed(ActionEvent e)
	{

	}

	public Identification(){
		
		this.setTitle("Identification");
		this.setSize(320, 320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		
		label1.setBounds(30, 20, 150, 30);
		label2.setBounds(30, 70, 150, 30);
		
		container.setLayout(null);
		
		Font police = new Font("Arial", Font.BOLD, 14);
		login.setFont(police);
		login.setBounds(120, 20, 150, 30);
		login.setForeground(Color.BLACK);
		container.add(label1);
		container.add(login);
		
		password.setFont(police);
		password.setBounds(120, 70, 150, 30);
		password.setForeground(Color.BLACK);
		container.add(label2);
		container.add(password);
		
		retour.setBounds(1, 1, 10, 10);
		container.add(retour);
		
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
		
		connexion.setBounds(35, 260, 250, 20);
		container.add(connexion);
		
		connexion.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Affectation affectation = new Affectation();
				if (password.getText().equals(password2.toString(1, "utilisateur", "password")) 
						&& login.getText().equals(login2.toString(1, "utilisateur", "login")))
				{
					affectation.affiche();
					dispose();
				}
			}
		}
		);
  
		this.setContentPane(container);            
	}

	public void affiche()
	{
		setVisible(true);
	}

	public static void main(String[] args)
	{

	}
}
