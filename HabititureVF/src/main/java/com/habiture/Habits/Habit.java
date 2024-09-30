package com.habiture.Habits;

import java.util.HashMap;
import java.util.Map;

public class Habit {
    private final String name;
    private final Map<String, Boolean> completions; // Mapa de fechas de completación

    public Habit(String name) {
        this.name = name;
        this.completions = new HashMap<>(); // Inicialización del HashMap
    }

    public String getName() {
        return name;
    }

    public Map<String, Boolean> getCompletions() {
        return completions;
    }

    public void addCompletion(String date, boolean completed) {
        completions.put(date, completed); // Agregar una completición al mapa
    }

    public void removeCompletion(String date) {
        completions.remove(date); // Eliminar una completición del mapa
    }

    public void editCompletion(String date, boolean completed) {
        completions.put(date, completed); // Editar una completición en el mapa
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(": ");
        for (Map.Entry<String, Boolean> entry : completions.entrySet()) {
            sb.append(entry.getKey()).append(" - ").append(entry.getValue() ? "Completed" : "Not Completed").append(", ");
        }
        return sb.toString();
    }
}
