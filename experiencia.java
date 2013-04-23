/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package taller2;

//administrador de codigo fuente  github
/**
 *Clase experiencia, permite registrar, borrar y buscar experiencia.
 * @author invitadofei
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Experiencia extends javax.swing.JFrame{

     //lista de atributos
    int NRC;
    String nombre;
    int cupo;
    int bloque;
    int seccion;
    int id_horario;

    private static Conexion con = new Conexion();
    private  static ResultSet rs;

    //constructor
    public Experiencia() {
        this.NRC = 0;
        this.nombre = "";
        this.cupo = 0;
        this.bloque = 0;
        this.seccion = 0;
        this.id_horario = 0;
    }

  

    public int getNRC() {
        return NRC;
    }

    public void setNRC(int NRC) {
        this.NRC = NRC;
    }

    public int getBloque() {
        return bloque;
    }

    public void setBloque(int bloque) {
        this.bloque = bloque;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }


    //lista de metodos

    public void crear_experiencia(int nrc, String nombre, int cupo, int bloque, int seccion){
                           
        if (con.crearConexion()){
             con.ejecutarSQL("INSERT INTO experiencia (NRC,Nombre,Cupo,Bloque,Seccion) VALUES("+nrc+",'"+nombre+"',"+cupo+","+bloque+","+seccion+");");
            
         }else{
         
         JOptionPane.showMessageDialog(null,"Error al establecer la conexion");
         }

         


    }

    public  ResultSet Busca_NRC(int nrc){
          
           if (con.crearConexion()){
            try {
                rs = con.ejecutarSQLSelect("SELECT NRC, Nombre, Cupo, Bloque, Seccion FROM experiencia e WHERE e.NRC=" + nrc + ";");

                if (rs.next()) {
                    //  t_nrc. rs.getInt(1)
                   // System.out.println(rs);
                    System.out.println(rs.getString(2));
                    System.out.println(rs.getInt(3));
                    System.out.println(rs.getInt(4));
                    System.out.println(rs.getInt(5));
                    return rs;
                } else {
                    System.out.println("No se encontro NRC");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Experiencia.class.getName()).log(Level.SEVERE, null, ex);
                
            }


           }else{
            JOptionPane.showMessageDialog(null,"Error al establecer la conexion");
           }
       return rs; 

    }

    public void Eliminar_NRC(int nrc){

       
       if (con.crearConexion()){
       rs = con.ejecutarSQLSelect("SELECT NRC, Nombre FROM experiencia e WHERE e.NRC="+nrc+";");
            try {
                if (rs.next()) {
                    con.ejecutarSQL("DELETE FROM experiencia WHERE NRC=" + nrc + ";");
                    System.out.println("materia eliminada");
                }else{
                System.out.println("Materia no encontrada");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Experiencia.class.getName()).log(Level.SEVERE, null, ex);
            }
           
       
       }else{
       JOptionPane.showMessageDialog(null,"Error al establecer la conexion");
       }
    }

    
    public boolean busca_nrc(int nrc){

       
        boolean bander = false;

        if (con.crearConexion()){

            rs = con.ejecutarSQLSelect("SELECT NRC FROM experiencia e WHERE e.NRC="+nrc+";");
            try {
                if (rs.next()) {
                    bander = true;
                } else {
                    bander = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Experiencia.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
 
        return bander;

    }

    public int recuperarid(int nrc){
    int idexperiencia=0;
    if (con.crearConexion()){
            try {
                rs = con.ejecutarSQLSelect("select ID_Experiencia from experiencia where NRC = " + nrc + ";");
                if (rs.next())
                idexperiencia = rs.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(Experiencia.class.getName()).log(Level.SEVERE, null, ex);
            }
    }else{
     JOptionPane.showMessageDialog(null,"Error al establecer la conexion");

    }


    return idexperiencia;
    }




}
