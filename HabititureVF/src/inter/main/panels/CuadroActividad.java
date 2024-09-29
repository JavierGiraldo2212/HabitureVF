package inter.main.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import com.habiture.Objects.Activity;

public class CuadroActividad extends JPanel {
    private Activity activity;

    public CuadroActividad(Activity activity, ActionListener eliminarListener) {
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

        // Botón de eliminar
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(eliminarListener); // Asociar el ActionListener

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
        add(btnEliminar); // Añadir el botón de eliminar al final

        // Borde opcional para visualizar mejor el panel
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Activity getActivity() {
        return activity;
    }
}
