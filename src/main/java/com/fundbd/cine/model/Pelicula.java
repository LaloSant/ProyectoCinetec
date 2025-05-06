/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine.model;

/**
 *
 * @author eduar
 */
public class Pelicula {

    private static final String RUTA_VIDEO_TEMP = "src/main/resources/temp/";

    private String idPelicula;
    private String nombre;
    private String sinopsis;
    private int duracion; //Segundos
    private String rutaImagen;
    private String rutaVideo;
    private String idioma;
    private String clasificacion;
    private String genero;

    public Pelicula(String idPelicula, String nombre, String sinopsis, int duracion, String rutaImagen, String rutaVideo, String idioma, String clasificacion, String genero) {
        this.idPelicula = idPelicula;
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
        this.rutaImagen = rutaImagen;
        this.rutaVideo = rutaVideo;
        this.idioma = idioma;
        this.clasificacion = clasificacion;
        this.genero = genero;
    }

    public Pelicula() {
    }

    /**
     * @return the idPelicula
     */
    public String getIdPelicula() {
        return idPelicula;
    }

    /**
     * @param idPelicula the idPelicula to set
     */
    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the sinopsis
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * @param sinopsis the sinopsis to set
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    /**
     * @return the duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the rutaImagen
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    /**
     * @param rutaImagen the rutaImagen to set
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    /**
     * @return the rutaVideo
     */
    public String getRutaVideo() {
        return rutaVideo;
    }

    /**
     * @param rutaVideo the rutaVideo to set
     */
    public void setRutaVideo(String rutaVideo) {
        this.rutaVideo = rutaVideo;
    }

    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * @return the clasificacion
     */
    public String getClasificacion() {
        return clasificacion;
    }

    /**
     * @param clasificacion the clasificacion to set
     */
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pelicula{");
        sb.append("\nidPelicula=").append(idPelicula);
        sb.append(", \nnombre=").append(nombre);
        sb.append(", \nsinopsis=").append(sinopsis);
        sb.append(", \nduracion=").append(duracion);
        sb.append(", \nrutaImagen=").append(rutaImagen);
        sb.append(", \nrutaVideo=").append(rutaVideo);
        sb.append(", \nidioma=").append(idioma);
        sb.append(", \nclasificacion=").append(clasificacion);
        sb.append(", \ngenero=").append(genero);
        sb.append('}');
        return sb.toString();
    }
    
    
}
