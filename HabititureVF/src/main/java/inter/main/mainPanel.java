package inter.main;

import inter.main.panels.ActividadesPanel;
import inter.main.panels.HabitListPanel;
import inter.main.panels.HistorialPanel;

import com.habiture.Habits.HabitTracker;
import com.habiture.Objects.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

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

    public mainPanel() {
        H_Fundamental fundamental = new H_Fundamental();

        Activity act1 = new Activity("Activity 1", LocalDate.of(2023, 10, 1), 3);
        Activity act2 = new Activity("Activity 2", LocalDate.of(2023, 9, 15), 2);
        Activity act3 = new Activity("Activity 3", LocalDate.of(2023, 11, 5), 1);

        fundamental.addActivity(act1);
        fundamental.addActivity(act2);
        fundamental.addActivity(act3);
        
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
        ImageIcon icon = new ImageIcon("src/main/java/figures/MainPanel/logo.png");
        Image img = icon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(img));
        lblLogo.setBounds(10, 11, 243, 96);
        panelLateral.add(lblLogo);

        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);
        panelPrincipal.setBounds(262, 0, 1002, 681);
        contentPane.add(panelPrincipal);

        // Reemplaza el DefaultPanel con un panel que tenga un fondo degradado
        GradientPanel DefaultPanel = new GradientPanel();
        panelPrincipal.add(DefaultPanel, "Default");

        JPanel panelMisActividades = new ActividadesPanel(fundamental);
        panelMisActividades.setBackground(Color.WHITE);
        panelPrincipal.add(panelMisActividades, "MisActividades");

        HabitTracker habitTracker = new HabitTracker();
        JPanel panelMisHabitos = new HabitListPanel(panelPrincipal, cardLayout, habitTracker);
        panelMisHabitos.setBackground(Color.WHITE);
        panelPrincipal.add(panelMisHabitos, "MisHabitos");

        HistorialPanel panelHistorialDeActividades = new HistorialPanel(fundamental);
        panelHistorialDeActividades.setBackground(Color.WHITE);
        panelPrincipal.add(panelHistorialDeActividades, "HistorialDeActividades");

        JPanel panelMisMotivaciones = new JPanel();
        panelMisMotivaciones.setBackground(Color.YELLOW);
        panelPrincipal.add(panelMisMotivaciones, "MisMotivaciones");

        JButton btnMisActividades = new JButton("<html>Mis Actividades</html>");
        styleButton(btnMisActividades);
        btnMisActividades.setBounds(10, 210, 243, 42);
        btnMisActividades.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((ActividadesPanel) panelMisActividades).IniciarActividades(fundamental);
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
        btnHistorialDeActividades.setBounds(10, 339, 243, 42);
        btnHistorialDeActividades.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((HistorialPanel) panelHistorialDeActividades).IniciarActividades(fundamental);
                showCard("HistorialDeActividades");
            }
        });
        panelLateral.add(btnHistorialDeActividades);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(135, 206, 250));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(243, 42));
    }

    private void showCard(String cardName) {
        cardLayout.show(panelPrincipal, cardName);
    }
}

class GradientPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        
        // Crear un degradado diagonal desde la esquina superior izquierda a la inferior derecha
        GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#E0FFFF"), width, height, Color.decode("#E1E5FF"));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height); // Rellenar el fondo con el degradado
    }
}