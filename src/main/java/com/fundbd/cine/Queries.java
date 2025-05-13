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
    public static String selectAllFunciones(String idCine) {
        return String.format("SELECT P.*, F.HORARIO, S.TIPO, F.ID_FUNCION \n"
                + "FROM PELICULAS P\n"
                + "JOIN FUNCIONES F ON P.ID_PELICULA = F.ID_PELICULA\n"
                + "JOIN SALAS S ON F.ID_SALA = S.ID_SALA\n"
                + "WHERE F.ID_CINE = '%s'", idCine);
    }

    public static String selectFuncion(String idFuncion) {
        return String.format("SELECT P.*, f.horario, s.tipo, f.id_funcion\n"
                + "FROM PELICULAS P\n"
                + "JOIN FUNCIONES F ON P.ID_PELICULA = F.ID_PELICULA\n"
                + "JOIN SALAS S ON F.ID_SALA = S.ID_SALA\n"
                + "WHERE F.ID_FUNCION = '%s'", idFuncion);
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
