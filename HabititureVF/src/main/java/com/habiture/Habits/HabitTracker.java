package com.habiture.Habits;


import com.habiture.Structures.Linear.SinglyLinkedList;

import java.util.Iterator;

public class HabitTracker {
    private SinglyLinkedList<Habit> habitsList;

    public HabitTracker() {
        habitsList = new SinglyLinkedList<>();
    }

    public void addHabit(String name) {
        habitsList.add(new Habit(name));
    }

    public void markHabitCompleted(String name, String date) {
        if (habitExists(name)) {
            for (Habit habit : habitsList) {
                if (habit.getName().equals(name)) {
                    habit.addCompletion(date, true);
                    return;
                }
            }
        } else {
            System.out.println("El hábito no existe.");
        }
    }

    public void markHabitNotCompleted(String name, String date) {
        if (habitExists(name)) {
            for (Habit habit : habitsList) {
                if (habit.getName().equals(name)) {
                    habit.addCompletion(date, false);
                    return;
                }
            }
        } else {
            System.out.println("El hábito no existe.");
        }
    }

    public void removeHabit(String name) {
        Iterator<Habit> iterator = habitsList.iterator();
        while (iterator.hasNext()) {
            Habit habit = iterator.next();
            if (habit.getName().equals(name)) {
                iterator.remove();
                return;
            }
        }
        System.out.println("El hábito no existe.");
    }

    public boolean habitExists(String name) {
        for (Habit habit : habitsList) {
            if (habit.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void removeCompletion(String name, String date) {
        if (habitExists(name)) {
            for (Habit habit : habitsList) {
                if (habit.getName().equals(name)) {
                    habit.removeCompletion(date);
                    return;
                }
            }
        } else {
            System.out.println("El hábito no existe.");
        }
    }

    public void editCompletion(String name, String date, boolean completed) {
        if (habitExists(name)) {
            for (Habit habit : habitsList) {
                if (habit.getName().equals(name)) {
                    habit.editCompletion(date, completed);
                    return;
                }
            }
        } else {
            System.out.println("El hábito no existe.");
        }
    }

    @Override
    public String toString() {
        return habitsList.toString();
    }

    public void printHabits() {
        for (Habit habit : habitsList) {
            System.out.println(habit);
        }

    }

    public SinglyLinkedList<Habit> getHabits() {
        return habitsList;
    }

    public Habit getHabit(String name) {
        for (Habit habit : habitsList) {
            if (habit.getName().equals(name)) {
                return habit;
            }
        }
        return null;
    }
}
