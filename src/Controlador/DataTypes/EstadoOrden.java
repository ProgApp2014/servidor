/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador.DataTypes;

/**
 *
 * @author rodro
 */
public enum EstadoOrden {
    ORDEN_RECIBIDA(0),ORDEN_PREPARADA(1),ORDEN_CONFIRMADA(2),ORDEN_CANCELADA(3);
    
    private final int value;
    private EstadoOrden(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
}
