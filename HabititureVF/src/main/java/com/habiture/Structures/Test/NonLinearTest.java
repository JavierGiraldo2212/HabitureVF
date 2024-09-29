package com.habiture.Structures.Test;

import java.time.LocalDate;

import com.habiture.Objects.Activity;
import com.habiture.Structures.NonLinear.*;

public class NonLinearTest {

	public static void gMaxHeap_Try() {
        // Crear un maxHeap
		GenericMaxHeap<Activity> maxHeap = new GenericMaxHeap<>(Activity.byImportancia());

        // Crear algunas Activityes
        Activity act1 = new Activity("Activity 1", LocalDate.of(2023, 10, 1), 3);
        Activity act2 = new Activity("Activity 2", LocalDate.of(2023, 9, 15), 2);
        Activity act3 = new Activity("Activity 3", LocalDate.of(2023, 11, 5), 1);

        // Insertar Activityes en el maxHeap
        maxHeap.insert(act1);
        maxHeap.insert(act2);
        maxHeap.insert(act3);

        // Mostrar el maxHeap
        maxHeap.printHeap();

        // Crear un maxHeap
        GenericMaxHeap<Activity> maxHeap2 = new GenericMaxHeap<>(Activity.byFecha());

        // Insertar Activityes en el maxHeap
        maxHeap2.insert(act1);
        maxHeap2.insert(act2);
        maxHeap2.insert(act3);

        // Mostrar el maxHeap
        maxHeap2.printHeap();

    }

    static void gBST_Try() {
        // Crear un BST usando el comparador por fecha
        GenericBinarySearchTree<Activity> bstByFecha = new GenericBinarySearchTree<>(Activity.byFecha());

        // Crear algunas Activityes
        Activity act1 = new Activity("Activity 1", LocalDate.of(2023, 10, 1), 3);
        Activity act2 = new Activity("Activity 2", LocalDate.of(2023, 9, 15), 2);
        Activity act3 = new Activity("Activity 3", LocalDate.of(2023, 11, 5), 1);

        // Insertar Activityes en el BST
        bstByFecha.insert(act1);
        bstByFecha.insert(act2);
        bstByFecha.insert(act3);

        // Mostrar el BST
        bstByFecha.display();
        System.out.println();

        // Crear un BST usando el comparador por importancia
        GenericBinarySearchTree<Activity> bstByImportancia = new GenericBinarySearchTree<>(Activity.byImportancia());

        // Insertar Activityes en el BST
        bstByImportancia.insert(act1);
        bstByImportancia.insert(act2);
        bstByImportancia.insert(act3);

        // Mostrar el BST
        bstByImportancia.display();
    }

    static void gAVL_Try() {
        // Crear un AVL usando el comparador por fecha
        GenericAVL<Activity> avlByFecha = new GenericAVL<>(Activity.byFecha());

        // Crear algunas Activityes
        Activity act1 = new Activity("Activity 1", LocalDate.of(2023, 10, 1), 3);
        Activity act2 = new Activity("Activity 2", LocalDate.of(2023, 9, 15), 2);
        Activity act3 = new Activity("Activity 3", LocalDate.of(2023, 11, 5), 1);

        // Insertar Activityes en el AVL
        avlByFecha.insert(act1);
        avlByFecha.insert(act2);
        avlByFecha.insert(act3);

        // Mostrar el AVL
        avlByFecha.display();
        System.out.println();

        // Crear un AVL usando el comparador por importancia
        GenericAVL<Activity> avlByImportancia = new GenericAVL<>(Activity.byImportancia());

        // Insertar Activities en el AVL
        avlByImportancia.insert(act1);
        avlByImportancia.insert(act2);
        avlByImportancia.insert(act3);

        // Mostrar el AVL
        avlByImportancia.display();
    }
	
    static void gDisjointSet_Try() {
        // Crear un conjunto disjunto genérico
        GenericDisjointSet<Activity> ds = new GenericDisjointSet<>();

        // Crear algunas actividades
        Activity act1 = new Activity("Actividad 1", LocalDate.of(2023, 10, 1), 3);
        Activity act2 = new Activity("Actividad 2", LocalDate.of(2023, 10, 2), 2);
        Activity act3 = new Activity("Actividad 3", LocalDate.of(2023, 10, 3), 1);
        Activity act4 = new Activity("Actividad 4", LocalDate.of(2023, 10, 4), 5);

        // Crear algunos conjuntos
        ds.makeSet(act1);
        ds.makeSet(act2);
        ds.makeSet(act3);
        ds.makeSet(act4);

        // Unir algunos conjuntos
        ds.union(act1, act2);
        ds.union(act3, act4);

        // Verificar si están conectados
        System.out.println("Actividad 1 y Actividad 2 están conectadas: " + ds.isConnected(act1, act2)); // true
        System.out.println("Actividad 1 y Actividad 3 están conectadas: " + ds.isConnected(act1, act3)); // false

        // Unir más conjuntos
        ds.union(act2, act3);

        // Verificar nuevamente
        System.out.println("Actividad 1 y Actividad 3 están conectadas: " + ds.isConnected(act1, act3)); // true
        System.out.println("Actividad 3 y Actividad 4 están conectadas: " + ds.isConnected(act3, act4)); // true
    }
}
