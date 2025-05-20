/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.Global;
import com.fundbd.cine.Queries;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduar
 */
public class ConexionSQL {

    private static final String USUARIO = "lalo";
    private static final String PASSWORD = "150605";
    private static final String URL = "jdbc:oracle:thin:@//25.52.189.97:1521/XEPDB1";
    private Connection conn;

    public ConexionSQL() {
        try {
            this.conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void conectar() {
        try {
            this.conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrar() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> consulta(String query, String columna) {
        ArrayList<String> resultado = new ArrayList<>();
        try {
            this.conectar();
            PreparedStatement sentencia = conn.prepareStatement(query);
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next()) {
                resultado.add(rs.getString(columna));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public ResultSet consulta(String query) {
        ArrayList<String> resultado = new ArrayList<>();
        ResultSet rs = null;
        try {
            this.conectar();
            PreparedStatement sentencia = conn.prepareStatement(query);
            rs = sentencia.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }
        return rs;
    }

    public boolean insertarPelicula(String idPelicula, String nombre,
            String sinopsis, String duracion, File imagen,
            File video, String idioma, String clasificacion,
            String genero) throws SQLException, IOException {
        PreparedStatement ps = conn.prepareStatement(Queries.insertarPelicula());
        ps.setString(1, idPelicula);
        ps.setString(2, nombre);
        ps.setString(3, sinopsis);
        ps.setString(4, duracion);
        ps.setBlob(5, crearBlob(imagen));
        ps.setBlob(6, crearBlob(video));
        ps.setString(7, idioma);
        ps.setString(8, clasificacion);
        ps.setString(9, genero);
        ps.executeUpdate();
        ps.close();
        return true;
    }

    public boolean insertarCompra(String idCompra, String idCliente, int total) throws SQLException, IOException {
        PreparedStatement ps = conn.prepareStatement(Queries.insertarCompra());
        ps.setString(1, idCompra);
        ps.setString(2, idCliente);
        ps.setInt(3, total);
        ps.executeUpdate();
        ps.close();
        return true;
    }
    
    public boolean insertarBoleto(String idBoleto, String idAsiento, String idCompra) throws SQLException, IOException {
        PreparedStatement ps = conn.prepareStatement(Queries.insertarBoleto());
        ps.setString(1, idBoleto);
        ps.setString(2, idAsiento);
        ps.setString(3, idCompra);
        ps.executeUpdate();
        ps.close();
        return true;
    }

    public boolean insertarCliente(String idCliente, String nombre,
            String apellidoP, String apellidoM, String contrasenia,
            Date fecha, String telefono, String correo, String numTarjeta,
            String nip) throws SQLException, IOException {
        PreparedStatement ps = conn.prepareStatement(Queries.insertarCliente());
        ps.setString(1, idCliente);
        ps.setString(2, nombre);
        ps.setString(3, apellidoP);
        ps.setString(4, apellidoM);
        ps.setString(5, correo);
        ps.setString(6, telefono);
        ps.setDate(7, fecha);
        ps.setString(8, contrasenia);
        ps.setString(9, numTarjeta);
        ps.setString(10, nip);
        ps.executeUpdate();
        ps.close();
        return true;
    }

    public boolean insertarFuncion(String idFuncion, String idPelicula,
            String idSala, String idCine, Timestamp horario) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Queries.insertarFuncion());
        ps.setString(1, idFuncion);
        ps.setString(2, idPelicula);
        ps.setString(3, idSala);
        ps.setString(4, idCine);
        ps.setTimestamp(5, horario);
        ps.executeUpdate();
        ps.close();
        return true;
    }

    public boolean insertarAsiento(String idAsiento, String idFuncion,
            String fila, String columna) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Queries.insertarAsiento());
        ps.setString(1, idAsiento);
        ps.setString(2, idFuncion);
        ps.setString(3, "True");
        ps.setString(4, fila);
        ps.setString(5, columna);
        ps.executeUpdate();
        ps.close();
        return true;
    }
    
    public void updateAsientoDisponible(String idAsiento) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(Queries.updateBoleto());
        ps.setString(1, idAsiento);
        ps.executeUpdate();
        ps.close();
    }

    public Blob crearBlob(File file) throws IOException, SQLException {
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        int bytesRead;
        int offset = 0;
        while (offset < bytes.length) {
            bytesRead = fis.read(bytes, offset, bytes.length - offset);
            if (bytesRead == -1) {
                break;
            }
            offset += bytesRead;
        }
        fis.close();
        Blob b = conn.createBlob();
        b.setBytes(1, bytes);
        return b;
    }

//    public void subirBlob(String ruta, String query) {
//        try {
//            this.conectar();
//            File file = new File(ruta);
//            FileInputStream fis = new FileInputStream(file);
//            byte[] bytes = new byte[(int) file.length()];
//            int bytesRead;
//            int offset = 0;
//            while (offset < bytes.length) {
//                bytesRead = fis.read(bytes, offset, bytes.length - offset);
//                if (bytesRead == -1) {
//                    break;
//                }
//                offset += bytesRead;
//            }
//            fis.close();
//            PreparedStatement stat = conn.prepareStatement(query);
//            Blob blob = conn.createBlob();
//            blob.setBytes(1, bytes);
//            stat.setBlob(1, blob);
//            stat.executeUpdate();
//            stat.close();
//            conn.close();
//
//        } catch (IOException | SQLException ex) {
//            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void leerBlob(String ruta, String query) {
//        Statement st;
//        try {
//            this.conectar();
//            st = conn.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next()) {
//                InputStream is = rs.getBinaryStream(1);
//                FileOutputStream fos = new FileOutputStream(ruta);
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = is.read(buffer)) != -1) {
//                    fos.write(buffer, 0, bytesRead);
//                }
//                fos.close();
//            }
//            conn.close();
//        } catch (SQLException | IOException ex) {
//            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
