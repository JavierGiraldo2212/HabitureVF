package inter.main.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.habiture.Objects.Activity;
import com.habiture.Objects.H_Fundamental;

public class CuadroHistorialDone extends JPanel {
	private Activity activity;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public CuadroHistorialDone(Activity activity, H_Fundamental fundamental, HistorialPanel parent) {
		setBackground(new Color(255, 255, 255));
        this.activity = activity;
        setLayout(new GridLayout(0, 2));  // Configuración de 2 columnas

        // Etiquetas para mostrar los datos de la actividad
        JLabel nameLabel = new JLabel("Nombre: ");
        JLabel nameValue = new JLabel(activity.getNombre());

        JLabel dateLabel = new JLabel("Fecha: ");
        JLabel dateValue = new JLabel(activity.getFecha().toString());

        JLabel importanciaLabel = new JLabel("Nivel de importancia: ");
        JLabel importanciaValue = new JLabel(String.valueOf(activity.getNivelImportancia()));

        JLabel horaLabel = new JLabel("Hora: ");
        JLabel horaValue = new JLabel(activity.getHora().toString());

        JLabel descripcionLabel = new JLabel("Descripción: ");
        JLabel descripcionValue = new JLabel(activity.getDescripcion() != null ? activity.getDescripcion() : "Sin descripción");

        // Agregar componentes al panel
        add(nameLabel);
        add(nameValue);
        add(dateLabel);
        add(dateValue);
        add(importanciaLabel);
        add(importanciaValue);
        add(horaLabel);
        add(horaValue);
        add(descripcionLabel);
        add(descripcionValue);

        // Borde opcional para visualizar mejor el panel
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        
    }


    public Activity getActivity() {
        return activity;
    }
}
