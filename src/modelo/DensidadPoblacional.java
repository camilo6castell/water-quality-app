/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author kml10
 */
public class DensidadPoblacional extends ObjetoGeografico{
    
    private int h;

    public DensidadPoblacional() {
    }

    public int geth() {
        return h;
    }

    public void seth(int h) {
        this.h = h;
    }
    
    public String afeccion(){
        String nivelA = "";
        if (h < 10000){
            nivelA = "Nivel de afección: 0";
        }else if (h >= 10000 && h <= 50000){
            nivelA = "Nivel de afección: 1";
        } else if (h > 50000){
            nivelA = "Nivel de afección: 2";
        }
        return nivelA;
    }    
}
