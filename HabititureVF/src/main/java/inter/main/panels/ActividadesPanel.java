package inter.main.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.habiture.Objects.Activity;
import com.habiture.Objects.H_Fundamental;

import inter.main.AddActivityJFrame;

public class ActividadesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel panelContenedor;
    private int actividadCount = 0; // Contador para crear actividades con nombres únicos

    /**
     * Constructor del panel de actividades.
     */
    public ActividadesPanel(H_Fundamental fundamental) {
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        
        // Panel contenedor donde se mostrarán los ActivityPanel
        panelContenedor = new JPanel();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
        panelContenedor.setBackground(Color.LIGHT_GRAY);
        
        // ScrollPane para hacer que el panel contenedor sea desplazable si hay muchas actividades
        JScrollPane scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 60, 980, 571);
        add(scrollPane);
        
        // Botón para agregar nuevas actividades
        JButton btnAgregarActividad = new JButton("Agregar Actividad");
        btnAgregarActividad.setBounds(50, 642, 200, 30);
        ImageIcon icono = new ImageIcon("src/figures/icon.jpg");
        Image imagen = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Tamaño del ícono
        btnAgregarActividad.setIcon(new ImageIcon(imagen));
        add(btnAgregarActividad);
        
        // Acción del botón para crear una nueva actividad
        btnAgregarActividad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNuevaActividad();
            }
        });
    }

    /**
     * Método para agregar una nueva actividad y su panel correspondiente.
     */
    private void agregarNuevaActividad() {
    	
    	AddActivityJFrame frame = new AddActivityJFrame();
    	frame.setVisible(true);
        // Crear una nueva actividad con un nombre y datos ficticios
        actividadCount++;
        Activity nuevaActividad = new Activity(
                "Actividad " + actividadCount, 
                LocalDate.now(), 
                (actividadCount % 5) + 1, 
                LocalTime.of(actividadCount % 24, 0), 
                "Descripción para actividad " + actividadCount
        );

        // Crear un nuevo CuadroActividad para la nueva actividad
        CuadroActividad activityPanel = new CuadroActividad(nuevaActividad);

        // Establecer solo la altura fija, pero dejar que el ancho se ajuste automáticamente
        Dimension panelSize = new Dimension(panelContenedor.getWidth(), 100);  // Ancho dinámico, altura fija de 100px
        activityPanel.setPreferredSize(panelSize);
        activityPanel.setMinimumSize(new Dimension(0, 100));  // Mínimo ancho 0, altura fija
        activityPanel.setMaximumSize(new Dimension(panelContenedor.getWidth(), 100));

        // Alinear a la izquierda para evitar estiramientos
        activityPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        // Añadir el panel al panel contenedor
        panelContenedor.add(activityPanel);

        // Añadir un espacio entre los paneles para que no se peguen
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 10)));  // Espacio de 10px de altura entre los paneles

        // Refrescar el panel contenedor para que muestre los nuevos componentes
        panelContenedor.revalidate();
        panelContenedor.repaint();
    }


}
