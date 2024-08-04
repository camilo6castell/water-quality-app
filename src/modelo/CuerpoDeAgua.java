package modelo;

import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CuerpoDeAgua extends ObjetoGeografico {

    private String tipoC, tipoA;
    private Double irca;

    public CuerpoDeAgua() {
    }

    public CuerpoDeAgua(String nombre, int id, String municipio, String tipoC, String tipoA, Double irca) {
        super(nombre, id, municipio);
        this.tipoC = tipoC;
        this.tipoA = tipoA;
        this.irca = irca;
    }

    @Override
    public String toString() {
        return nombre + " " + id + " " + municipio + " " + tipoC + " " + tipoA + " " + irca;
    }

    public String getTipoC() {
        return tipoC;
    }

    public void setTipoC(String tipoC) {
        this.tipoC = tipoC;
    }

    public String getTipoA() {
        return tipoA;
    }

    public void setTipoA(String tipoA) {
        this.tipoA = tipoA;
    }

    public Double getIrca() {
        return irca;
    }

    public void setIrca(Double irca) {
        this.irca = irca;
    }

    public String nivel() {
        if (80 < irca && irca <= 100) {
            return ("INVIABLE SANITARIAMENTE");
        } else if (35 < irca && irca <= 80) {
            return ("ALTO");
        } else if (14 < irca && irca <= 35) {
            return ("MEDIO");
        } else if (5 < irca && irca <= 14) {
            return ("BAJO");
        } else if (0 < irca && irca <= 5) {
            return ("SIN RIESGO");
        } else {
            return "SIN CLASIFICACION";
        }
    }

    public void savecda(String nom, String ident, String muni, String tc, String ta, String ir) {
        Connection con = Conexion.conectar();

        nombre = nom;
        id = Integer.parseInt(ident);
        municipio = muni;
        tipoC = tc;
        tipoA = ta;
        irca = Double.parseDouble(ir);

        String sql = "INSERT INTO cda (nombre,id,municipio,tc,ta,irca) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, getNombre());
            ps.setInt(2, getId());
            ps.setString(3, getMunicipio());
            ps.setString(4, getTipoC());
            ps.setString(5, getTipoA());
            ps.setDouble(6, getIrca());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente ingresado con éxito");

        } catch (SQLException ex) {
            System.out.println("No se ha podido Insertar\n " + ex.getMessage());
        }

        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<CuerpoDeAgua> listarcda() {

        ArrayList<CuerpoDeAgua> vcda = new ArrayList<>();

        Connection con = Conexion.conectar();

        String sql = "SELECT * FROM cda";
        ResultSet rs;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                CuerpoDeAgua cda = new CuerpoDeAgua();
                cda.setId(rs.getInt("id"));
                cda.setNombre(rs.getString("nombre"));
                cda.setMunicipio(rs.getString("municipio"));
                cda.setTipoC(rs.getString("tc"));
                cda.setTipoA(rs.getString("ta"));
                cda.setIrca(rs.getDouble("irca"));

                vcda.add(cda);
            }

        } catch (SQLException ex) {
            System.out.println("Error en consulta\n " + ex.getMessage());
        }

        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return vcda;
    }
    
    public CuerpoDeAgua buscarId(String ident){
        
        Connection con = Conexion.conectar();
        id = Integer.parseInt(ident);
        
        String sql = "SELECT * FROM cda WHERE id=?";
        ResultSet rs;
        
        CuerpoDeAgua cda = new CuerpoDeAgua();
        
        try{
            PreparedStatement  ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()){
                cda.setId(rs.getInt("id"));
                cda.setNombre(rs.getString("nombre"));
                cda.setMunicipio(rs.getString("municipio"));
                cda.setTipoC(rs.getString("tc"));
                cda.setTipoA(rs.getString("ta"));
                cda.setIrca(rs.getDouble("irca"));
   
            }else{
                JOptionPane.showMessageDialog(null, "ID no encontrado");                
            }
   
        }catch(SQLException ex){
            System.out.println(ex.getMessage());            
        }
        try{
            con.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());            
        }
        return cda;
    }
    
    public void editarcda(String nom, String ident, String muni, String tc, 
            String ta, String ir){
        
        nombre = nom;
        id = Integer.parseInt(ident);
        municipio = muni;
        tipoC = tc;
        tipoA = ta;
        irca = Double.parseDouble(ir);
        
        Connection con = Conexion.conectar();

        String sql = "UPDATE cda SET nombre=?,municipio=?,tc=?,ta=?,"
                + "irca=? WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, getNombre());
            ps.setString(2, getMunicipio());
            ps.setString(3, getTipoC());
            ps.setString(4, getTipoA());
            ps.setDouble(5, getIrca());
            ps.setInt(6, getId());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Paciente Actualizado con éxito");
            

        } catch (SQLException ex) {
            System.out.println("No se ha podido Actualizar\n " + ex.getMessage());
        }

        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void eliminarcda(String ident){
        
        Connection con = Conexion.conectar();
        id = Integer.parseInt(ident);

        String sql = "DELETE FROM cda WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Paciente Eliminado");
            

        } catch (SQLException ex) {
            System.out.println("No se ha podido Eliminar\n " + ex.getMessage());
        }
    }

}
