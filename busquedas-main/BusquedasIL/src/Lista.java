import javax.swing.*;

public class Lista {

    private Nodo inicio;
    private int tamano;

    public Lista() {
        inicio = null;
        tamano = 0;
    }

    // Método para agregar un valor entero al final de la lista
    public void agregar(int dato, JTextArea textArea) {
        Nodo nuevoNodo = new Nodo(dato);
        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            Nodo actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
        actualizarTextArea(textArea);
    }

    // Método para eliminar un valor entero de la lista
    public boolean eliminar(int dato, JTextArea textArea) {
        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "La lista está vacía.");
            return false;
        }
        if (inicio.dato == dato) {
            inicio = inicio.siguiente;
            tamano--;
            actualizarTextArea(textArea);
            return true;
        }
        Nodo actual = inicio;
        while (actual.siguiente != null && actual.siguiente.dato != dato) {
            actual = actual.siguiente;
        }
        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            tamano--;
            actualizarTextArea(textArea);
            return true;
        }
        JOptionPane.showMessageDialog(null, "Elemento no encontrado en la lista.");
        return false;
    }

    // Método para realizar una búsqueda lineal
    public int buscarLineal(int dato,  JTextArea textArea) {
        Nodo actual = inicio;
        int posicion = 0;
        while (actual != null) {
            if (actual.dato == dato) {
                return posicion;
            }
            actual = actual.siguiente;
            posicion++;
        }
        return -1; // Retorna -1 si no se encuentra
    }

    // Método para realizar una búsqueda interpolada (requiere lista ordenada)
    public int buscarInterpolado(int dato, JTextArea textArea) {
        ordenarBurbuja(textArea); // Ordena la lista antes de realizar la búsqueda
        int[] array = convertirAArray();
        int inicio = 0;
        int fin = tamano - 1;

        while (inicio <= fin && dato >= array[inicio] && dato <= array[fin]) {
            int pos = inicio + ((dato - array[inicio]) * (fin - inicio) / (array[fin] - array[inicio]));
            if (array[pos] == dato) {
                return pos;
            }
            if (array[pos] < dato) {
                inicio = pos + 1;
            } else {
                fin = pos - 1;
            }
        }
        return -1; // Retorna -1 si no se encuentra
    }

    // Método para ordenar la lista usando burbuja
    public void ordenarBurbuja(JTextArea textArea) {
        if (inicio == null || inicio.siguiente == null) return;

        boolean swapped;
        do {
            swapped = false;
            Nodo actual = inicio;
            Nodo siguiente = inicio.siguiente;
            while (siguiente != null) {
                if (actual.dato > siguiente.dato) {
                    int temp = actual.dato;
                    actual.dato = siguiente.dato;
                    siguiente.dato = temp;
                    swapped = true;
                }
                actual = siguiente;
                siguiente = siguiente.siguiente;
            }
        } while (swapped);

        actualizarTextArea(textArea); // Actualiza la vista en el JTextArea
    }

    // Método BUSCAR BINARIO
    public int buscarBinario(int dato, JTextArea textArea) {
        ordenarBurbuja(textArea); // Ordena la lista antes de buscar
        int[] array = convertirAArray();
        int inicio = 0;
        int fin = array.length - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            if (array[medio] == dato) {
                return medio; // Posición del valor encontrado
            } else if (array[medio] < dato) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1; // Si no se encuentra
    }

    //Método BUSCAR SECUENCIAL
    public int buscarSecuencial(int dato, JTextArea textArea) {
        int[] array = convertirAArray();  // Convertimos lista a array
        for (int i = 0; i < array.length; i++) {
            if (array[i] == dato) {
                return i;  // Retorna la posición si lo encuentra
            }
        }
        return -1;  // No encontrado
    }


    // Método auxiliar para convertir la lista a un array
    int[] convertirAArray() {
        int[] array = new int[tamano];
        Nodo actual = inicio;
        int i = 0;
        while (actual != null) {
            array[i++] = actual.dato;
            actual = actual.siguiente;
        }
        return array;
    }

    // Método para mostrar la lista en el JTextArea
    public void mostrarLista(JTextArea textArea) {
        if (inicio == null) {
            textArea.setText("La lista está vacía.");
        } else {
            StringBuilder listaStr = new StringBuilder();
            Nodo actual = inicio;
            while (actual != null) {
                listaStr.append(actual.dato).append("\n");
                actual = actual.siguiente;
            }
            textArea.setText(listaStr.toString());
        }
    }

    // Método para actualizar el JTextArea cada vez que cambia la lista
    private void actualizarTextArea(JTextArea textArea) {
        mostrarLista(textArea);
    }
}
