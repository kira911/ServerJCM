package br.com.fox.util;

import br.com.fox.controller.AlarmeJpaController;
import br.com.fox.controller.ClienteJpaController;
import br.com.fox.controller.ProtocoloJpaController;
import br.com.fox.db.Cliente;
import java.util.List;

/**
 *
 * @author Dvr
 */
public class CheckHorarioAlarme implements Runnable {

    private ClienteJpaController jpaCliente = new ClienteJpaController();
    private ProtocoloJpaController jpaProtocol = new ProtocoloJpaController();
    private AlarmeJpaController jpaAlarme = new AlarmeJpaController();
    
    @Override
    public void run() {
        while (true) {
            List<Cliente> listClinte = jpaCliente.findClienteEntities();
            for (Cliente cliente : listClinte) {
                
            }
        }
    }
}
