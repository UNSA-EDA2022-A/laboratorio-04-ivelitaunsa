package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class SinglyLinkedList<T> {
    private Node<T> first; // Primero nodo de la lista
    private int size; // Tamano de la lista

    // Constructor (crea lista vacia)
    SinglyLinkedList() {
        first = null;
        size = 0;
    }

    // Retorna el tamano de la lista
    public int size() {
        return size;
    }

    // Devuelve true si la lista esta vazia o false caso contrario
    public boolean isEmpty() {
        return (size == 0);
    }

    // Adiciona v al inicio de la lista
    public void addFirst(T v) {
        Node<T> newNode = new Node<T>(v, first);
        first = newNode;
        size++;
    }

    // Adiciona v al final de la lista
    public void addLast(T v) {
        Node<T> newNode = new Node<T>(v, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            Node<T> cur = first;
            while (cur.getNext() != null)
                cur = cur.getNext();
            cur.setNext(newNode);
        }
        size++;
    }

    // Retorna el primer valor de la lista (o null si la lista esta vacia)
    public T getFirst() {
        if (isEmpty())
            return null;
        return first.getValue();
    }

    // Retorna el ultimo valor de la lista (o null si la lista esta vazia)
    public T getLast() {
        if (isEmpty())
            return null;
        Node<T> cur = first;
        while (cur.getNext() != null)
            cur = cur.getNext();
        return cur.getValue();
    }

    // Elimina el primer elemento de la lista (si esta vacia no hara nada)
    public void removeFirst() {
        if (isEmpty())
            return;
        first = first.getNext();
        size--;
    }

    // Elimina el ultimo elemento de la lista (si esta vacia no hara nada)
    public void removeLast() {
        if (isEmpty())
            return;
        if (size == 1) {
            first = null;
        } else {
            // Ciclo con for y uso de size para mostrar alternativa al while
            Node<T> cur = first;
            for (int i = 0; i < size - 2; i++)
                cur = cur.getNext();
            cur.setNext(cur.getNext().getNext());
        }
        size--;
    }

    // Convierte la lista para um String
    public String toString() {
        String str = "{";
        Node<T> cur = first;
        while (cur != null) {
            str += cur.getValue();
            cur = cur.getNext();
            if (cur != null)
                str += ",";
        }
        str += "}";
        return str;
    }

    // NUEVOS METODOS

    // Elimina aquellos nodos de la lista que esten duplicados
    public void deleteDuplicates() {
        //Add auxiliary arrayList
        List<T> set = new ArrayList<T>();
        //Si el tamaño es 0 o 1 no hay nada que hacer
        if(size() == 0 || size() == 1) {
            return;
        }else {
            Node<T> temp1 = first;
            Node<T> temp2 = first;
            while(temp1 != null) {
                // Cast value in String for valid compareTo method
                if(set.contains(temp1.getValue())) {
                    temp2.setNext(temp1.getNext());
                    size--;
                }else {
                    set.add(temp1.getValue());
                    temp2 = temp1;
                }
                temp1 = temp1.getNext();
            }
        }
    }

    // Inserta un nuevo nodo en una posicion especifica de la lista
    public void insertNth(T data, int position) {
        if(verifyPosition(position, 0, size())) {
            Node<T> temp = first;
            Node<T> prev = first;
            if(position == 0) {
                addFirst(data);
                return;
            }else if(position == size()) {
                addLast(data);
                return;
            }
            for(int i=0; i<size();i++) {
                if(position == i){
                    Node<T> newNode = new Node<T>(data, prev.getNext());
                    prev.setNext(newNode);
                    size++;
                }
                prev = temp;
                temp = temp.getNext();
            }
        }else {
            System.out.println("\"Fuera de rango.\"");
        }
    }

    public boolean verifyPosition(int position, int first, int last) {
        if(position>=first && position<=last) {
            return true;
        }
        return false;
    }

    // Elimina el nodo de una posicion especifica de la lista
    public void deleteNth(int position) {
        if(verifyPosition(position, 0, size())) {
            Node<T> temp = first;
            Node<T> prev = first;
            if(position == 0 ) {
                removeFirst();
                return;
            }else if(position == size()-1) {
                removeLast();
                return;
            }
            for(int i=0; i<size();i++) {
                if(position == i){
                    prev.setNext(temp.getNext());
                    size--;
                }
                prev = temp;
                temp = temp.getNext();
            }
        } else {
            System.out.println("\"Fuera de rango.\"");
        }
    }

    public static void main(final String[] args) {

        // testExercicio1();
        // testExercicio2();
        testExercicio3();       

    }

    public static void testExercicio1(){

        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

        list.addLast(47);
        list.addLast(89);
        list.addLast(56);
        list.addLast(89);
        list.addLast(56);

        System.out.println(list);

        list.deleteDuplicates();

        System.out.println(list);
    }

    public static void testExercicio2(){

        SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

        list.addLast('a');
        list.addLast('b');
        list.addLast('d');

        System.out.println(list);

        list.insertNth('c', 5);

        System.out.println(list);
    }

    public static void testExercicio3(){

        SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

        list.addLast('a');
        list.addLast('b');
        list.addLast('d');

        System.out.println(list);

        list.deleteNth(2);

        System.out.println(list);
    }

}