package inter.main.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.habiture.Objects.Activity;
import com.habiture.Objects.H_Fundamental;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class HistorialPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel panelContenedor;
    private int actividadCount = 0; // Contador para crear actividades con nombres únicos
    private JComboBox filtro;

	/**
	 * Create the panel.
	 */
	public HistorialPanel(H_Fundamental fundamental) {
        setBackground(Color.WHITE);
        setLayout(null);

        // Panel contenedor donde se mostrarán los ActivityPanel
        panelContenedor = new JPanel();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
        panelContenedor.setBackground(Color.WHITE);

        // ScrollPane para hacer que el panel contenedor sea desplazable si hay muchas
        // actividades
        JScrollPane scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 60, 980, 569);
        add(scrollPane);
        ImageIcon icono = new ImageIcon("src/figures/icon.jpg");
        Image imagen = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        
        // Botón para agregar nuevas actividades
        JButton btnAgregarActividad = new JButton("Deshacer");
        btnAgregarActividad.setBounds(50, 642, 200, 30);
        btnAgregarActividad.setIcon(new ImageIcon(imagen));
        add(btnAgregarActividad);
        
        JLabel lblfiltro = new JLabel("MOSTRAR HISTORIAL SEGÚN");
        lblfiltro.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblfiltro.setBounds(10, 11, 325, 38);
        add(lblfiltro);
        
        filtro = new JComboBox();
        filtro.setModel(new DefaultComboBoxModel(new String[] {"Eliminado", "Hecho"}));
        filtro.setFont(new Font("Tahoma", Font.PLAIN, 15));
        filtro.setBounds(304, 11, 246, 38);
        filtro.setSelectedIndex(0);
        add(filtro);

        // Acción del botón para crear una nueva actividad
        btnAgregarActividad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				if (filtro.getSelectedIndex() == 0) {
					fundamental.removeHistActivity();
				} else {
					fundamental.removeDoneActivity();
				}
            	IniciarActividades(fundamental);
            }
        });
        
        filtro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IniciarActividades(fundamental);
			}
		});
        
	}

	
	
	
	public void IniciarActividades(H_Fundamental fundamental) {
		panelContenedor.removeAll();
		// Obtener las actividades del AVL del objeto fundamental
        List<Activity> actividades;// = fundamental.HistActivities.toList();

        if(filtro.getSelectedIndex() == 0) {
        	actividades = fundamental.HistActivities.toList();
        }
		else {
			actividades = fundamental.DoneActivities.toList();
		}
        
        // Iterar sobre cada actividad y crear un CuadroActividad para cada una
        for (Activity actividad : actividades) {
            // Crear un nuevo CuadroActividad para la actividad
        	CuadroHistorialDone activityPanel = new CuadroHistorialDone(actividad, fundamental, this);

            // Establecer solo la altura fija, pero dejar que el ancho se ajuste
            // automáticamente
            Dimension panelSize = new Dimension(panelContenedor.getWidth(), 100); // Ancho dinámico, altura fija de
                                                                                  // 100px
            activityPanel.setPreferredSize(panelSize);
            activityPanel.setMinimumSize(new Dimension(0, 100)); // Mínimo ancho 0, altura fija
            activityPanel.setMaximumSize(new Dimension(panelContenedor.getWidth(), 100));

            // Alinear a la izquierda para evitar estiramientos
            activityPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

            // Añadir el panel al panel contenedor
            panelContenedor.add(activityPanel);

            // Añadir un espacio entre los paneles para que no se peguen
            panelContenedor.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio de 10px de altura entre los
                                                                            // paneles
        }

        // Refrescar el panel contenedor para que muestre los nuevos componentes
        panelContenedor.revalidate();
        panelContenedor.repaint();
	}
}
