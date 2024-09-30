package inter.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.habiture.Objects.Activity;
import com.habiture.Objects.H_Fundamental;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class AddActivityJFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nameField;
    private JDateChooser dateChooser;
    private JComboBox<Integer> importanceComboBox;
    private JSpinner hourSpinner;
    private JCheckBox hourCheckBox;
    private JTextArea descriptionArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    H_Fundamental fundamental = new H_Fundamental(); // Crear un objeto H_Fundamental válido
                    AddActivityJFrame frame = new AddActivityJFrame(null);
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

    public AddActivityJFrame(H_Fundamental fundamental) {

        setTitle("Agregar Actividad");
        setIconImage(new ImageIcon("src/main/java/figures/icon.jpg").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 682, 402);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(6, 2)); // Ajustado para 6 filas

        // Name field
        JLabel nameLabel = new JLabel("Nombre:");
        nameField = new JTextField();
        contentPane.add(nameLabel);
        contentPane.add(nameField);

        // Date field (JDateChooser)
        JLabel dateLabel = new JLabel("Fecha:");
        dateChooser = new JDateChooser();
        contentPane.add(dateLabel);
        contentPane.add(dateChooser);

        // Importance field
        JLabel importanceLabel = new JLabel("Nivel de Importancia:");
        Integer[] importanceLevels = { 1, 2, 3, 4, 5 };
        importanceComboBox = new JComboBox<>(importanceLevels);
        contentPane.add(importanceLabel);
        contentPane.add(importanceComboBox);

        // Hour field
        hourSpinner = new JSpinner(new SpinnerDateModel());
        hourSpinner.setBackground(Color.WHITE);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(hourSpinner, "HH:mm"); // Formato de 24 horas
        hourSpinner.setEditor(timeEditor);
        hourSpinner.setValue(new Date()); // Hora actual por defecto
        hourSpinner.setEnabled(false); // Deshabilitar hasta que se seleccione el checkbox

        // Avance de 15 minutos en el spinner
        hourSpinner.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // Hour label and checkbox
        JLabel hourLabel = new JLabel("Hora (Opcional):");
        panel.add(hourLabel);

        hourCheckBox = new JCheckBox("¿Agregar Hora?");
        hourCheckBox.setFont(new Font("Tahoma", Font.BOLD, 11));
        hourCheckBox.setBackground(new Color(255, 132, 135));
        panel.add(hourCheckBox);
        hourCheckBox.setSelected(false); // Default: not selected
        hourCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hourSpinner.setEnabled(hourCheckBox.isSelected());
            }
        });
        contentPane.add(hourSpinner);

        // Description area and checkbox
        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEnabled(false); // Deshabilitar hasta que se seleccione el checkbox

        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        contentPane.add(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

        JLabel descriptionLabel = new JLabel("Descripción (Opcional):");
        panel_1.add(descriptionLabel);

        JCheckBox descriptionCheckBox = new JCheckBox("¿Agregar Descripción?");
        descriptionCheckBox.setFont(new Font("Tahoma", Font.BOLD, 11));
        descriptionCheckBox.setBackground(new Color(255, 132, 135));
        descriptionCheckBox.setSelected(false); // Default: not selected
        descriptionCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                descriptionArea.setEnabled(descriptionCheckBox.isSelected());
            }
        });
        panel_1.add(descriptionCheckBox);

        contentPane.add(descriptionScroll);

        // Submit button
        JButton submitButton = new JButton("Agregar Actividad");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit(fundamental);
            }
        });
        contentPane.add(submitButton);

        // Empty label for layout balance
        contentPane.add(new JLabel());
    }

    private void handleSubmit(H_Fundamental fundamental) {
        String name = nameField.getText().trim();
        Date date = dateChooser.getDate();
        Integer importance = (Integer) importanceComboBox.getSelectedItem();
        String description = descriptionArea.getText().trim();

        // Validar campos obligatorios
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (date == null) {
            JOptionPane.showMessageDialog(this, "La fecha es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convertir Date a LocalDate
        LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String message = "Actividad Agregada:\n" +
                "Nombre: " + name + "\n" +
                "Fecha: " + dateFormat.format(date) + "\n" +
                "Nivel de Importancia: " + importance + "\n";

        // Procesar la hora si se seleccionó
        LocalTime localTime = null;
        if (hourCheckBox.isSelected()) {
            Date hour = (Date) hourSpinner.getValue();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            localTime = hour.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
            message += "Hora: " + timeFormat.format(hour) + "\n";
        } else {
            message += "Hora: No especificada\n";
        }

        // Procesar la descripción si se seleccionó
        if (!descriptionArea.isEnabled()) {
            description = "No especificada";
        }

        message += "Descripción: " + description;

        // Mostrar el mensaje
        JOptionPane.showMessageDialog(this, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Crear y retornar el objeto Activity
        fundamental.TempActivities.insert(new Activity(name, localDate, importance, localTime, description));
        // Cerrar el JFrame
        this.dispose();
    }
}
