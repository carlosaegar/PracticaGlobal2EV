package org.example;

import java.sql.*;

public class Concierto {

    private int id;
    private int artista_id;
    private Date fecha;
    private String lugar;
    private double precioEntrada;

    public Concierto(int id, int artista_id, Date fecha, String lugar, double precioEntrada) {
        this.id = id;
        this.artista_id = artista_id;
        this.fecha = fecha;
        this.lugar = lugar;
        this.precioEntrada = precioEntrada;
    }

    //GETTERS
    public int getId() {
        return id; }

    public int getArtista_id() {
        return artista_id; }

    public Date getFecha() {
        return fecha; }

    public String getLugar() {
        return lugar; }

    public double getPrecioEntrada() {
        return precioEntrada; }

    //SETTERS
    public void setId(int id) {
        this.id = id; }

    public void setArtista_id(int artista_id) {
        this.artista_id = artista_id; }

    public void setFecha(Date fecha) {
        this.fecha = fecha; }

    public void setLugar(String lugar) {
        this.lugar = lugar; }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada; }


    //CREAR
    public static void crear(Connection conn, int artistaId, String lugar, double precio) throws SQLException {
        String sql = "INSERT INTO Concierto (artista_id, fecha, lugar, precioEntrada) VALUES (?, CURRENT_DATE, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, artistaId);
            pstmt.setString(2, lugar);
            pstmt.setDouble(3, precio);
            pstmt.executeUpdate();
            System.out.println("Concierto registrado.");
        }
    }

    //LISTAR
    public static void listar(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Concierto";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println(" LISTA DE CONCIERTOS ");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Artista ID: " + rs.getInt("artista_id") +
                        " | Lugar: " + rs.getString("lugar") +
                        " | Precio: " + rs.getDouble("precioEntrada") + "€");
            }
        }
    }

    //ELIMINAR
    public static void eliminar(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM Concierto WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Concierto eliminado.");
            } else {
                System.out.println("No se encontro concierto con ID: " + id);
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 2292) {
                System.out.println("Error: no se puede eliminar el concierto ");
            } else {
                throw e;
            }
        }
    }
}
