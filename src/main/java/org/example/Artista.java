package org.example;
import java.sql.*;

public class Artista {
    private int id;
    private String nombre;
    private String generoMusical;
    private String paisOrigen;

    public Artista(int id, String nombre, String genero, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.generoMusical = genero;
        this.paisOrigen = pais;
    }

    // GETTERS
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getGeneroMusical() { return generoMusical; }
    public String getPaisOrigen() { return paisOrigen; }

    // SETTERS
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setGeneroMusical(String generoMusical) { this.generoMusical = generoMusical; }
    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }


    //OPCIONES
        //CREAR
    public static void crear(Connection conn, String nombre, String genero, String pais) throws SQLException {
        String sql = "INSERT INTO Artista (nombre, generoMusical, paisOrigen) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, genero);
            pstmt.setString(3, pais);
            pstmt.executeUpdate();
            System.out.println("Artista creado con éxito.");
        }
    }
        //ELIMINAR
    public static void eliminar(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM Artista WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int filas = pstmt.executeUpdate();
            if (filas > 0) System.out.println("Artista eliminado.");
            else System.out.println("No se encontró el ID.");
        }
    }
        //LISTAR
    public static void listar(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Artista";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- LISTA DE ARTISTAS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | " + rs.getString("nombre") + " (" + rs.getString("generoMusical") + ")");
            }
        }
    }
}
