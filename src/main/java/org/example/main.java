package org.example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "RIBERA";
    private static final String PASS = "ribera";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            int opcion = -1;
            while (opcion != 0) {
                try {
                    System.out.println("Elige una Opcion");
                    System.out.println("1. Añadir Artista");
                    System.out.println("2. Listar Artistas");
                    System.out.println("3. Eliminar Artista");
                    System.out.println("4. Nuevo Concierto");
                    System.out.println("5. Listar Conciertos");
                    System.out.println("6. Borrar Conciertos");
                    System.out.println("7. Vender Entrada");
                    System.out.println("8. Listar Entradas");
                    System.out.println("0. Salir");
                    System.out.println("Tu opcion: ");
                    opcion = sc.nextInt();
                    sc.nextLine();

                    switch (opcion) {
                        case 1:
                            System.out.print("Nombre: "); String n = sc.nextLine();
                            System.out.print("Genero: "); String g = sc.nextLine();
                            System.out.print("País: "); String p = sc.nextLine();
                            Artista.crear(conn, n, g, p);
                            break;
                        case 2:
                            Artista.listar(conn);
                            break;
                        case 3:
                            System.out.print("ID Artista a borrar: ");
                            Artista.eliminar(conn, sc.nextInt());
                            break;
                        case 4:
                            System.out.print("ID Artista: "); int idA = sc.nextInt(); sc.nextLine();
                            System.out.print("Lugar: "); String l = sc.nextLine();
                            System.out.print("Precio: "); double pr = sc.nextDouble();
                            Concierto.crear(conn, idA, l, pr);
                            break;
                        case 5:
                            Concierto.listar(conn);
                            break;
                        case 6:
                            System.out.print("ID del Concierto a borrar: ");
                            int idConciertoBorrar = sc.nextInt();
                            Concierto.eliminar(conn, idConciertoBorrar);
                            break;
                        case 7:
                            System.out.print("ID Concierto: "); int idC = sc.nextInt(); sc.nextLine();
                            System.out.print("Comprador: "); String comp = sc.nextLine();
                            System.out.print("Cantidad: "); int cant = sc.nextInt();
                            Entrada.vender(conn, idC, comp, cant);
                            break;
                        case 8:
                            Entrada.listar(conn);
                            break;
                        case 0:
                            System.out.println("Acabado.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error.");
                    sc.nextLine();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
