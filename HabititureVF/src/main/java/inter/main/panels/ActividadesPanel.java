package inter.main.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.habiture.Objects.Activity;
import com.habiture.Objects.H_Fundamental;

import inter.main.AddActivityDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ActividadesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel panelContenedor;
    private JComboBox filtro;
    /**
     * Constructor del panel de actividades.
     */
    public ActividadesPanel(H_Fundamental fundamental) {
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        // Panel contenedor donde se mostrarán los ActivityPanel
        panelContenedor = new JPanel();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
        panelContenedor.setBackground(Color.WHITE);

        // ScrollPane para hacer que el panel contenedor sea desplazable si hay muchas
        // actividades
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
        
        JLabel lblfiltro = new JLabel("MOSTRAR ACTIVIDADES SEGÚN:");
        lblfiltro.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblfiltro.setBounds(10, 11, 251, 38);
        add(lblfiltro);
        
        filtro = new JComboBox();
        filtro.setFont(new Font("Tahoma", Font.PLAIN, 15));
        filtro.setModel(new DefaultComboBoxModel(new String[] {"Más cercano", "Más urgente"}));
        filtro.setBounds(277, 11, 233, 38);
        filtro.setSelectedIndex(0);
        add(filtro);

        // Acción del botón para crear una nueva actividad
        btnAgregarActividad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNuevaActividad(fundamental);
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
		System.out.println("IniciarActividades");
		System.out.println(filtro.getSelectedItem());
		panelContenedor.removeAll();
		// Obtener las actividades del AVL del objeto fundamental
        List<Activity> actividades;// = fundamental.TempActivities.getSortedElements();

        if(filtro.getSelectedIndex() == 0) {
        	actividades = fundamental.TempActivities.getSortedElements();
        }
		else {
			actividades = fundamental.ImpActivities.getSortedElements();
		}
        
        
        // Iterar sobre cada actividad y crear un CuadroActividad para cada una
        for (Activity actividad : actividades) {
            // Crear un nuevo CuadroActividad para la actividad
            CuadroActividad activityPanel = new CuadroActividad(actividad, fundamental,this);

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
    
    /**
     * Método para agregar una nueva actividad y su panel correspondiente.
     */
	private void agregarNuevaActividad(H_Fundamental fundamental) {
	    try {
	        // Crear el diálogo como modal
	        AddActivityDialog dialog = new AddActivityDialog(fundamental);
	        dialog.setModal(true); // Establecer como modal
	        dialog.setVisible(true); // Mostrar el diálogo

	        // Después de que el diálogo se cierra, actualizar las actividades
	        IniciarActividades(fundamental);
	    } catch (Exception e) {
	        e.printStackTrace(); // Imprime el error en la consola
	    }
	}
}