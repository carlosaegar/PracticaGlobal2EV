package org.example;

import java.sql.*;

public class Entrada {
    private int id;
    private int concierto_id;
    private String comprador;
    private int cantidad;
    private Date fechaCompra;

    public Entrada(int id, int concierto_id, String comprador, int cantidad, Date fecha) {
        this.id = id;
        this.concierto_id = concierto_id;
        this.comprador = comprador;
        this.cantidad = cantidad;
        this.fechaCompra = fecha;
    }

    // GETTERS
    public int getId() { return id; }
    public int getConcierto_id() { return concierto_id; }
    public String getComprador() { return comprador; }
    public int getCantidad() { return cantidad; }
    public Date getFechaCompra() { return fechaCompra; }

    // SETTERS
    public void setId(int id) { this.id = id; }
    public void setConcierto_id(int cId) { this.concierto_id = cId; }
    public void setComprador(String comp) { this.comprador = comp; }
    public void setCantidad(int cant) { this.cantidad = cant; }
    public void setFechaCompra(Date f) { this.fechaCompra = f; }

    //VENDER
    public static void vender(Connection conn, int conciertoId, String comprador, int cantidad) throws SQLException {
        String sql = "INSERT INTO Entrada (concierto_id, comprador, cantidad, fechaCompra) VALUES (?, ?, ?, CURRENT_DATE)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, conciertoId);
            pstmt.setString(2, comprador);
            pstmt.setInt(3, cantidad);
            pstmt.executeUpdate();
            System.out.println("Venta de entrada realizada.");
        }
    }

    //LISTAR
    public static void listar(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Entrada";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("LISTA DE ENTRADAS VENDIDAS");
            while (rs.next()) {
                System.out.println("ID Venta: " + rs.getInt("id") +
                        " ID Concierto: " + rs.getInt("concierto_id") +
                        " Comprador: " + rs.getString("comprador") +
                        " Cantidad: " + rs.getInt("cantidad") +
                        " Fecha: " + rs.getDate("fechaCompra"));
            }
        }
    }
}
