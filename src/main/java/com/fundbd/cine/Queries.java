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

    public static String selectAllSalas() {
        return "SELECT * FROM SALAS";
    }

    public static String selectAllPeliculas() {
        return "SELECT * FROM PELICULAS";
    }

    public static String selectSala(String idCine) {
        return String.format("SELECT * FROM SALAS WHERE ID_CINE = '%s'", idCine);
    }

    public static String selectCine(String idCine) {
        return String.format("SELECT * FROM CINES WHERE id_cine = '%s'", idCine);
    }

    public static String selectPelicula(String idPelicula, boolean imagenYVideo) {
        if (imagenYVideo) {
            return String.format("SELECT * FROM PELICULAS WHERE id_pelicula = '%s'", idPelicula);
        }
        return String.format("SELECT ID_PELICULA, NOMBRE, SINOPSIS, DURACION, IDIOMA, CLASIFICACION, GENERO  FROM PELICULAS WHERE ID_PELICULA = '%s'", idPelicula);
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
        return String.format("SELECT P.*, F.HORARIO, S.NOMBRE, F.ID_FUNCION, S.TIPO\n"
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

    public static String insertarFuncion() {
        return "INSERT INTO FUNCIONES VALUES(\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?)\n"
                + ")";
    }

    public static String insertarAsiento() {
        return "INSERT INTO ASIENTOS VALUES(\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?),\n"
                + "    (?)\n"
                + ")";
    }

    public static String contarAsientos() {
        return "SELECT COUNT(*) FROM ASIENTOS";
    }

    public static String subirImagenPelicula(String idPelicula) {
        return String.format("UPDATE PELICULAS SET IMAGEN = (?) WHERE ID_PELICULA = '%s'", idPelicula);
    }

    public static String subirVideoPelicula(String idPelicula) {
        return String.format("UPDATE PELICULAS SET TRAILER = (?) WHERE ID_PELICULA = '%s'", idPelicula);
    }

}
