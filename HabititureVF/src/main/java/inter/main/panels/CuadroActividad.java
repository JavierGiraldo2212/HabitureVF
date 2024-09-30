package inter.main.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.habiture.Objects.Activity;
import com.habiture.Objects.H_Fundamental;

public class CuadroActividad extends JPanel {
    private Activity activity;

    public CuadroActividad(Activity activity, H_Fundamental fundamental, ActividadesPanel parent) {
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

        JButton btnChekc = new JButton("Marcar como completado");
        btnChekc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fundamental.doneActivity(getActivity());
                parent.IniciarActividades(fundamental);
            }
        });
        add(btnChekc);
        
        
        add(btnChekc);
        
        JButton btnDelete = new JButton("Eliminar");
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
				fundamental.removeActivity(activity);
				parent.IniciarActividades(fundamental);
            }
        });
        add(btnDelete);
        
    }


    public Activity getActivity() {
        return activity;
    }
}
