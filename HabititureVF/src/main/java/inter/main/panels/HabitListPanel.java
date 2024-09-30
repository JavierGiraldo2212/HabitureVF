package inter.main.panels;

import com.habiture.Habits.Habit;
import com.habiture.Habits.HabitTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HabitListPanel extends JPanel {
    private DefaultListModel<String> habitListModel;
    private JList<String> habitList;
    private JPanel panelPrincipal;
    private CardLayout cardLayout;
    private HabitDetailPanel habitDetailPanel;
    private final HabitTracker habitTracker;

    public HabitListPanel(JPanel panelPrincipal, CardLayout cardLayout, HabitTracker habitTracker) {
        this.panelPrincipal = panelPrincipal;
        this.cardLayout = cardLayout;
        this.habitTracker = habitTracker;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado

        // Título bonito
        JLabel titleLabel = new JLabel("Mis Hábitos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        habitListModel = new DefaultListModel<>();
        habitList = new JList<>(habitListModel);
        habitList.setCellRenderer(new HabitListCellRenderer()); // Estilizar la lista
        JScrollPane scrollPane = new JScrollPane(habitList);
        scrollPane.setPreferredSize(new Dimension(300, 400)); // Tamaño preferido de la tabla

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(scrollPane, gbc);

        JButton btnAddHabit = new JButton("Agregar Hábito");
        styleButton(btnAddHabit);
        btnAddHabit.addActionListener(e -> {
            String habitName = JOptionPane.showInputDialog("Ingrese el nombre del hábito:");
            if (habitName != null && !habitName.trim().isEmpty()) {
                habitListModel.addElement(habitName);
                this.habitTracker.addHabit(habitName);
            }
        });

        JButton btnRemoveHabit = new JButton("Eliminar Hábito");
        styleButton(btnRemoveHabit);
        btnRemoveHabit.addActionListener(e -> {
            String selectedHabitName = habitList.getSelectedValue();
            if (selectedHabitName != null) {
                habitListModel.removeElement(selectedHabitName);
                this.habitTracker.removeHabit(selectedHabitName);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAddHabit, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnRemoveHabit, gbc);

        habitDetailPanel = new HabitDetailPanel(panelPrincipal, cardLayout, habitTracker);
        panelPrincipal.add(habitDetailPanel, "HabitDetail");

        habitList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedHabitName = habitList.getSelectedValue();
                    if (selectedHabitName != null) {
                        Habit selectedHabit = getHabitByName(selectedHabitName);
                        if (selectedHabit != null) {
                            habitDetailPanel.setHabit(selectedHabit);
                            cardLayout.show(panelPrincipal, "HabitDetail");
                        }
                    }
                }
            }
        });
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180)); // Color de fondo
        button.setForeground(Color.WHITE); // Color del texto
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente
        button.setFocusPainted(false); // Quitar el borde al recibir el foco
        button.setBorderPainted(false); // Quitar el borde
        button.setOpaque(true); // Hacer el botón opaco
        button.setPreferredSize(new Dimension(200, 50)); // Tamaño preferido
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar el cursor al pasar sobre el botón
    }

    private static class HabitListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setFont(new Font("Arial", Font.PLAIN, 18)); // Cambiar la fuente
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Añadir padding
            if (isSelected) {
                label.setBackground(new Color(70, 130, 180)); // Color de fondo al seleccionar
                label.setForeground(Color.WHITE); // Color del texto al seleccionar
            } else {
                label.setBackground(Color.WHITE); // Color de fondo normal
                label.setForeground(Color.BLACK); // Color del texto normal
            }
            return label;
        }
    }

    private Habit getHabitByName(String name) {
        return habitTracker.getHabit(name);
    }
}