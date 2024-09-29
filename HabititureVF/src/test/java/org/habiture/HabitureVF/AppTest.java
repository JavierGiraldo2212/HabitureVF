package org.habiture.HabitureVF;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser; // Importar JDateChooser
import java.text.SimpleDateFormat;

public class AppTest extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nameField;
    private JDateChooser dateChooser; // Cambiar a JDateChooser
    private JComboBox<Integer> importanceComboBox;
    private JTextField hourField;
    private JTextArea descriptionArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddActivityJFrame frame = new AddActivityJFrame();
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
    public AppTest() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 682, 402);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(6, 2));

        // Name field
        JLabel nameLabel = new JLabel("Nombre (Obligatorio):");
        nameField = new JTextField();
        contentPane.add(nameLabel);
        contentPane.add(nameField);

        // Date field (JDateChooser)
        JLabel dateLabel = new JLabel("Fecha (Obligatorio):");
        dateChooser = new JDateChooser();
        contentPane.add(dateLabel);
        contentPane.add(dateChooser);

        // Importance field
        JLabel importanceLabel = new JLabel("Nivel de Importancia (1-5):");
        Integer[] importanceLevels = {1, 2, 3, 4, 5};
        importanceComboBox = new JComboBox<>(importanceLevels);
        contentPane.add(importanceLabel);
        contentPane.add(importanceComboBox);

        // Hour field
        JLabel hourLabel = new JLabel("Hora (Opcional):");
        hourField = new JTextField();
        contentPane.add(hourLabel);
        contentPane.add(hourField);

        // Description field
        JLabel descriptionLabel = new JLabel("Descripción (Opcional):");
        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        contentPane.add(descriptionLabel);
        contentPane.add(descriptionScroll);

        // Submit button
        JButton submitButton = new JButton("Agregar Actividad");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        contentPane.add(submitButton);
        
        // Adjusting the layout
        contentPane.add(new JLabel()); // Empty cell for layout balance
    }

    private void handleSubmit() {
        String name = nameField.getText().trim();
        java.util.Date date = dateChooser.getDate(); // Obtener la fecha desde JDateChooser
        Integer importance = (Integer) importanceComboBox.getSelectedItem();
        String hour = hourField.getText().trim();
        String description = descriptionArea.getText().trim();

        // Validate required fields
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (date == null) {
            JOptionPane.showMessageDialog(this, "La fecha es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Process the data (e.g., store it, print it, etc.)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String message = "Actividad Agregada:\n" +
                         "Nombre: " + name + "\n" +
                         "Fecha: " + dateFormat.format(date) + "\n" +
                         "Nivel de Importancia: " + importance + "\n" +
                         "Hora: " + hour + "\n" +
                         "Descripción: " + description;

        JOptionPane.showMessageDialog(this, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
