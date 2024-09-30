package inter.main.panels;

import com.habiture.Habits.Habit;
import com.habiture.Habits.HabitTracker;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class HabitDetailPanel extends JPanel {
    private JLabel habitNameLabel;
    private JTable completionsTable;
    private DefaultTableModel tableModel;
    private Habit currentHabit;
    private JPanel panelPrincipal;
    private CardLayout cardLayout;

    public HabitDetailPanel(JPanel panelPrincipal, CardLayout cardLayout, HabitTracker habitTracker) {
        this.panelPrincipal = panelPrincipal;
        this.cardLayout = cardLayout;
        setLayout(new BorderLayout());

        habitNameLabel = new JLabel("", SwingConstants.CENTER); // Centrar el texto
        habitNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(habitNameLabel, BorderLayout.NORTH);

        completionsTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"Fecha", "Completado"}, 0);
        completionsTable.setModel(tableModel);
        completionsTable.setRowHeight(30);
        completionsTable.setFont(new Font("Arial", Font.PLAIN, 16));
        completionsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        completionsTable.getTableHeader().setBackground(new Color(70, 130, 180));
        completionsTable.getTableHeader().setForeground(Color.WHITE);
        completionsTable.setGridColor(new Color(200, 200, 200));
        completionsTable.setShowGrid(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        completionsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        completionsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        add(new JScrollPane(completionsTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnAddCompletion = new JButton("Agregar Completado");
        JButton btnAddNotCompleted = new JButton("Agregar No Completado");
        JButton btnRemoveCompletion = new JButton("Quitar");
        JButton btnModifyCompletion = new JButton("Modificar Completado");
        JButton btnBack = new JButton("Retroceder");

        btnAddCompletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = JOptionPane.showInputDialog("Ingrese la fecha (YYYY-MM-DD):");
                if (date != null && !date.trim().isEmpty()) {
                    currentHabit.addCompletion(date, true);
                    updateTable();
                }
            }
        });

        btnAddNotCompleted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = JOptionPane.showInputDialog("Ingrese la fecha (YYYY-MM-DD):");
                if (date != null && !date.trim().isEmpty()) {
                    currentHabit.addCompletion(date, false);
                    updateTable();
                }
            }
        });

        btnRemoveCompletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = completionsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String date = (String) tableModel.getValueAt(selectedRow, 0);
                    currentHabit.removeCompletion(date);
                    updateTable();
                }
            }
        });

        btnModifyCompletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = completionsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String date = (String) tableModel.getValueAt(selectedRow, 0);
                    boolean completed = "Sí".equals(tableModel.getValueAt(selectedRow, 1));
                    currentHabit.addCompletion(date, !completed);
                    updateTable();
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "MisHabitos");
            }
        });

        buttonPanel.add(btnAddCompletion);
        buttonPanel.add(btnAddNotCompleted);
        buttonPanel.add(btnRemoveCompletion);
        buttonPanel.add(btnModifyCompletion);
        buttonPanel.add(btnBack);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setHabit(Habit habit) {
        this.currentHabit = habit;
        habitNameLabel.setText(habit.getName());
        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Map.Entry<String, Boolean> entry : currentHabit.getCompletions().entrySet()) {
            tableModel.addRow(new Object[]{entry.getKey(), entry.getValue() ? "Sí" : "No"});
        }
    }
}