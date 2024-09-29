package inter.main;

import inter.main.panels.ActividadesPanel;

import com.habiture.Objects.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Main panel class for the application.
 */
public class mainPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelPrincipal;
    private CardLayout cardLayout;

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
    	
    	H_Fundamental fundamental = new H_Fundamental();
    	
        setTitle("Habiture");
        setIconImage(new ImageIcon("src/main/java/figures/icon.jpg").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelLateral = new JPanel();
        panelLateral.setBackground(new Color(224, 255, 255));
        panelLateral.setBounds(0, 0, 263, 681);
        contentPane.add(panelLateral);
        panelLateral.setLayout(null);

        JLabel lblLogo = new JLabel("");
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("src/main/java/figures/MainPanel/logo.png"); // Ruta de la imagen
        Image img = icon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH); // Redimensionar imagen
        lblLogo.setIcon(new ImageIcon(img));
        lblLogo.setBounds(10, 11, 243, 96);
        panelLateral.add(lblLogo);

        // Crear y estilizar los botones
        JButton btnMisActividades = new JButton("<html>Mis Actividades</html>");
        styleButton(btnMisActividades);
        btnMisActividades.setBounds(10, 210, 243, 42);
        btnMisActividades.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("MisActividades");
            }
        });
        panelLateral.add(btnMisActividades);

        JButton btnMisHbitos = new JButton("<html>Mis Hábitos</html>");
        styleButton(btnMisHbitos);
        btnMisHbitos.setBounds(10, 274, 243, 42);
        btnMisHbitos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("MisHabitos");
            }
        });
        panelLateral.add(btnMisHbitos);

        JButton btnHistorialDeActividades = new JButton("<html>Historial de actividades</html>");
        styleButton(btnHistorialDeActividades);
        btnHistorialDeActividades.setBounds(10, 411, 243, 42);
        btnHistorialDeActividades.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("HistorialDeActividades");
            }
        });
        panelLateral.add(btnHistorialDeActividades);

        JButton btnmisMotivaciones = new JButton("<html>Mis Motivaciones</html>");
        styleButton(btnmisMotivaciones);
        btnmisMotivaciones.setBounds(10, 345, 243, 42);
        btnmisMotivaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard("MisMotivaciones");
            }
        });
        panelLateral.add(btnmisMotivaciones);

        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);
        panelPrincipal.setBounds(262, 0, 1002, 681);
        contentPane.add(panelPrincipal);

        JPanel DefaultPanel = new JPanel();
        DefaultPanel.setBackground(Color.YELLOW);
        panelPrincipal.add(DefaultPanel, "Default");
        
        
        // Add different panels to the card layout
        JPanel panelMisActividades = new ActividadesPanel(fundamental);
        panelMisActividades.setBackground(Color.WHITE);
        panelPrincipal.add(panelMisActividades, "MisActividades");

        JPanel panelMisHabitos = new JPanel();
        panelMisHabitos.setBackground(Color.LIGHT_GRAY);
        panelPrincipal.add(panelMisHabitos, "MisHabitos");

        JPanel panelHistorialDeActividades = new JPanel();
        panelHistorialDeActividades.setBackground(Color.PINK);
        panelPrincipal.add(panelHistorialDeActividades, "HistorialDeActividades");

        JPanel panelMisMotivaciones = new JPanel();
        panelMisMotivaciones.setBackground(Color.YELLOW);
        panelPrincipal.add(panelMisMotivaciones, "MisMotivaciones");
    }

    /**
     * Styles a given button with predefined properties.
     * 
     * @param button the button to style
     */
    private void styleButton(JButton button) {
        button.setBackground(new Color(135, 206, 250)); // Color de fondo
        button.setForeground(Color.BLACK); // Color del texto
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente
        button.setFocusPainted(false); // Quitar el borde al recibir el foco
        button.setBorderPainted(false); // Quitar el borde
        button.setOpaque(true); // Hacer el botón opaco
        button.setPreferredSize(new Dimension(243, 42)); // Tamaño preferido
    }

    /**
     * Shows the specified card in the CardLayout.
     * 
     * @param cardName the name of the card to show
     */
    private void showCard(String cardName) {
        cardLayout.show(panelPrincipal, cardName);
    }
}