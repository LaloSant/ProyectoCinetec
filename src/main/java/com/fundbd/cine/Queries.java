/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine;

/**
 *
 * @author eduar
 */
public class Queries {

    private Queries() {
    }

    public static String selectAllCines() {
        return "SELECT * FROM CINES";
    }

    public static String selectCines(String idCine) {
        return String.format("SELECT * FROM CINES WHERE id_cine = '%s'", idCine);
    }

    /**
     * Para cartelera
     *
     * @param idCine
     * @return
     */
    public static String selectPeliculas(String idCine) {
        return String.format("SELECT P.*, F.HORARIO \n"
                + "FROM PELICULAS P\n"
                + "JOIN FUNCIONES F ON P.ID_PELICULA = F.ID_PELICULA\n"
                + "WHERE F.ID_CINE = '%s'", idCine);
    }

    public static String selectPelicula(String idPelicula) {
        return String.format("SELECT * FROM PELICULAS WHERE id_pelicula = '%s'", idPelicula);
    }

    public static String insertarPelicula() {
        return "INSERT INTO PELICULAS VALUES(\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?)\n"
                + ")";
    }
    
     public static String insertarCliente() {
        return "INSERT INTO CLIENTES VALUES(\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?)\n"
                + ")";
    }

    public static String subirImagenPelicula(String idPelicula) {
        return String.format("UPDATE PELICULAS SET IMAGEN = (?) WHERE ID_PELICULA = '%s'", idPelicula);
    }

    public static String subirVideoPelicula(String idPelicula) {
        return String.format("UPDATE PELICULAS SET TRAILER = (?) WHERE ID_PELICULA = '%s'", idPelicula);
    }

}
