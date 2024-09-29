package inter.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StartJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartJFrame frame = new StartJFrame();
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
	public StartJFrame() {
		setTitle("Habiture");
		setIconImage(new ImageIcon("src/figures/icon.jpg").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new GradientPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JLabel backgrounFigure = new JLabel();
		ImageIcon icon = new ImageIcon("src/figures/StartPanel/icon.png"); // Ruta de la imagen
        Image img = icon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH); // Redimensionar imagen
        backgrounFigure.setIcon(new ImageIcon(img));
		contentPane.add(backgrounFigure);
		
		JButton btnStartButton = new JButton("COMENZAR");
		
		// Personalización del botón
        btnStartButton.setFont(new Font("Arial", Font.BOLD, 30));  // Fuente más grande y en negrita
        btnStartButton.setBackground(Color.decode("#94F7BE"));     // Color de fondo verde
        btnStartButton.setForeground(Color.WHITE);                 // Color de texto blanco
        btnStartButton.setPreferredSize(new Dimension(300, 100));  // Dimensiones del botón
        btnStartButton.setFont(new Font("Arial", Font.BOLD, 30));  // Fuente más grande y en negrita
        btnStartButton.setBackground(Color.decode("#94F7BE"));     // Color de fondo verde
        btnStartButton.setForeground(Color.WHITE);                 // Color de texto blanco
        btnStartButton.setPreferredSize(new Dimension(300, 100));  // Dimensiones del botón
        btnStartButton.setBorder(new LineBorder(Color.WHITE, 3));  // Borde blanco de 3 píxeles

        // Efecto de borde elevado
        btnStartButton.setBorder(BorderFactory.createRaisedBevelBorder());

        // Añadir ActionListener al botón
        btnStartButton.addActionListener(new ActionListener() {

        	@Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar el JFrame actual
                dispose();
                // Abrir el nuevo JFrame
                mainPanel nextFrame = new mainPanel();
                nextFrame.setVisible(true);
            }
        });
		
		contentPane.add(btnStartButton);
		
		
		
		
	}
	
	 class GradientPanel extends JPanel {
	        private static final long serialVersionUID = 1L;

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D g2d = (Graphics2D) g;
	            int width = getWidth();
	            int height = getHeight();

	            // Crear un degradado lineal de arriba (#1A3455) a abajo (#49E0FB)
	            GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#5E81AF"), 0, height, Color.decode("#49E0FB"));
	            g2d.setPaint(gradient);
	            g2d.fillRect(0, 0, width, height);
	        }
	    }
}
