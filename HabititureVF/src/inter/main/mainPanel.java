package inter.main;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainPanel frame = new mainPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public mainPanel() {
		setTitle("Habiture");
		setIconImage(new ImageIcon("src/figures/icon.jpg").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelLateral = new JPanel();
		panelLateral.setBounds(0, 0, 263, 681);
		contentPane.add(panelLateral);
		panelLateral.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
		ImageIcon icon = new ImageIcon("src/figures/MainPanel/logo.png"); // Ruta de la imagen
        Image img = icon.getImage().getScaledInstance(150, 75, Image.SCALE_SMOOTH); // Redimensionar imagen
		lblLogo.setIcon(new ImageIcon(img));
		
		lblLogo.setBounds(10, 11, 243, 78);
		panelLateral.add(lblLogo);
		
		JButton btnMisActividades = new JButton("<html>Mis Actividades</html>\r\n");
		btnMisActividades.setBounds(10, 118, 158, 42);
		panelLateral.add(btnMisActividades);
		
		JButton btnMisHbitos = new JButton("<html>Mis Hábitos</html>");
		btnMisHbitos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMisHbitos.setBounds(10, 182, 158, 42);
		panelLateral.add(btnMisHbitos);
		
		JButton btnHistorialDeActividades = new JButton("<html>Historial de actividades</html>");
		btnHistorialDeActividades.setHorizontalAlignment(SwingConstants.RIGHT);
		btnHistorialDeActividades.setBounds(10, 248, 158, 61);
		panelLateral.add(btnHistorialDeActividades);
		
		JPanel panel = new JPanel();
		panel.setBounds(262, 0, 1002, 681);
		contentPane.add(panel);
		panel.setLayout(new CardLayout(0, 0));
	}
}
