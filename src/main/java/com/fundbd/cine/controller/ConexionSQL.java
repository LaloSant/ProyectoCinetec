/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void subirBlob(String ruta, String query) {
        try {
            this.conectar();
            File file = new File(ruta);
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
            PreparedStatement stat = conn.prepareStatement(query);
            Blob blob = conn.createBlob();
            blob.setBytes(1, bytes);
            stat.setBlob(1, blob);
            stat.executeUpdate();
            stat.close();
            conn.close();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leerBlob(String ruta, String query) {
        Statement st;
        try {
            this.conectar();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                InputStream is = rs.getBinaryStream(1);
                FileOutputStream fos = new FileOutputStream(ruta);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                fos.close();
            }
            conn.close();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
