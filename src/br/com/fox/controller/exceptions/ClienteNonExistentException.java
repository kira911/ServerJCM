/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.controller.exceptions;

import br.com.fox.controller.exceptions.NonexistentEntityException;

/**
 *
 * @author Felipe
 */
public class ClienteNonExistentException extends NonexistentEntityException {
    public ClienteNonExistentException(String message, Throwable cause) {
        super("Cliente não existe!\n"+message, cause);
    }
    public ClienteNonExistentException(String message) {
        super("Cliente não existe!\n"+message);
    }    
}
