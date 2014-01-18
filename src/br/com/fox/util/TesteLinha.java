package br.com.fox.util;

import br.com.fox.controller.AlarmeJpaController;
import br.com.fox.controller.ClienteJpaController;
import br.com.fox.controller.ProtocoloJpaController;
import br.com.fox.db.Alarme;
import br.com.fox.db.Cliente;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 *
 * @author Dvr
 */
public class TesteLinha implements Runnable {

    ClienteJpaController jpaCliente = new ClienteJpaController();
    AlarmeJpaController jpaAlarme = new AlarmeJpaController();
    ProtocoloJpaController jpaProtocol = new ProtocoloJpaController();

    @Override
    public void run() {
        while (true) {
            List<Cliente> list = jpaCliente.findClienteEntities();
            DateTime dAtual = new DateTime(new Date());

            for (Cliente cliente : list) {
                //Alarme a = jpaAlarme.findAlarmeByTestLine(cliente);
                Alarme a = jpaAlarme.findAlarmeByLastDataRecebimento(cliente);

                if (a != null) {
                    DateTime dEvento = new DateTime(a.getDataRecebimento());

                    Duration d = new Duration(dEvento, dAtual);

                    if (d.getStandardMinutes() > 1450) {
                        Alarme alarme = new Alarme();
                        alarme.setDataRecebimento(new Date());
                        alarme.setReceiver(0);
                        alarme.setLinha(0);
                        alarme.setIdcliente(cliente);
                        alarme.setIdprotocolo(jpaProtocol.findProtocolo(386));
                        alarme.setParticao(0);
                        alarme.setStatus("NÃO ATENDIDO");
                        alarme.setZona(0);
                        
                        jpaAlarme.create(alarme);
                    }
                }
            }

            try {
                Thread.sleep(600000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TesteLinha.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
