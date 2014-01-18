package br.com.fox.util;

import br.com.fox.controller.AlarmeJpaController;
import br.com.fox.controller.ClienteJpaController;
import br.com.fox.controller.CucJpaController;
import br.com.fox.controller.ProtocoloJpaController;
import br.com.fox.db.Alarme;
import br.com.fox.db.Protocolo;
import br.com.fox.view.PortaSerialView;
import br.com.fox.util.PortaSerial;
import br.com.fox.view.ServidorView;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Dvr
 */
public class ServidorSensor implements Runnable {

    private ClienteJpaController jpaCliente = new ClienteJpaController();
    private ProtocoloJpaController jpaProtocol = new ProtocoloJpaController();
    private AlarmeJpaController jpaAlarme = new AlarmeJpaController();
    private CucJpaController jpaCuc = new CucJpaController();
    private PortaSerial ps;
    private JTextArea txtLog;

    public ServidorSensor(PortaSerial ps, JTextArea txtLog) {
        this.ps = ps;
        this.txtLog = txtLog;
    }

    @Override
    public void run() {
        try {
            txtLog.append("Abrindo porta: " + ps.getPorta() + "\n\n");
            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(ps.getPorta());
            final SerialPort serialPort = (SerialPort) portId.open(PortaSerialView.class.getName(), 0);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serialPort.getInputStream(), "UTF-8"));

            serialPort.addEventListener(new SerialPortEventListener() {

                @Override
                public void serialEvent(SerialPortEvent spe) {
                    BufferedOutputStream saida = null;
                    try {
                        String mensagem = bufferedReader.readLine();

                        if (!mensagem.equals("@")) {

                            if (mensagem.length() == 36 || mensagem.length() == 24) {
                                //saida = new BufferedOutputStream(serialPort.getOutputStream());
                                //saida.write(6);
                                //saida.flush();

                                if (mensagem.length() == 36) {
                                    txtLog.append("===========================Sinal do Sensor===========================\n\n");
                                    txtLog.append("Data: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "\n");
                                    txtLog.append("Mensagem recebida do CONTACT ID: " + mensagem + "\n\n");
                                    String receiver = mensagem.substring(14, 15);
                                    String linha = mensagem.substring(15, 16);
                                    String conta = mensagem.substring(17, 21);
                                    String evento = mensagem.substring(25, 29);
                                    String particao = mensagem.substring(30, 32);
                                    String zona = mensagem.substring(33, 36);
                                    
                                    Protocolo protocolo = jpaProtocol.findProtocolo(evento);
                                    if (protocolo == null) {                                    

                                        protocolo = new Protocolo();
                                        protocolo.setEvento(evento);
                                        protocolo.setDescricao("DESCONHECIDO");
                                        protocolo.setTipo("Contact ID");
                                        protocolo.setCuc(jpaCuc.findCuc(76));

                                        jpaProtocol.create(protocolo);

                                        txtLog.append("\nERRO: O evento '"+evento+"' não está cadastrado.\nSerá cadastrado mantendo o código, porém, com descrição 'DESCONHECIDO'.\n\n");
                                    }

                                    Alarme alarme = new Alarme();
                                    alarme.setDataRecebimento(new Date());
                                    alarme.setReceiver(Integer.parseInt(receiver));
                                    alarme.setLinha(Integer.parseInt(linha));
                                    try {
                                        alarme.setIdcliente(jpaCliente.findCliente(conta));
                                    } catch (Exception ex) {
                                        Logger.getLogger(ServidorSensor.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    alarme.setIdprotocolo(protocolo);
                                    alarme.setParticao(Integer.parseInt(particao));
                                    alarme.setStatus("NÃO ATENDIDO");
                                    alarme.setZona(Integer.parseInt(zona));

                                    jpaAlarme.create(alarme);
                                    txtLog.append("Alarme registrado com sucesso. \n\n");
                                    txtLog.append("==================================================================\n\n");
                                } else {
                                    if (mensagem.length() == 24) {
                                        txtLog.append("===========================Sinal do Sensor===========================\n\n");
                                        txtLog.append("Data: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "\n");
                                        txtLog.append("Mensagem recebida do 4/2: " + mensagem + "\n\n");
                                        String receiver = mensagem.substring(14, 15);
                                        String linha = mensagem.substring(15, 16);
                                        String conta = mensagem.substring(17, 21);
                                        String evento = mensagem.substring(22, 24);
                                        
                                        Protocolo protocolo = jpaProtocol.findProtocolo(evento);
                                        if (protocolo == null) {
                                            protocolo = jpaProtocol.findProtocoloByDescricao("DESCONHECIDO");                                    
                                            txtLog.append("\nERRO: O evento '"+evento+"' não está cadastrado.\nSerá utilizado um evento padrão.\n\n");
                                        }

                                        Alarme alarme = new Alarme();
                                        alarme.setDataRecebimento(new Date());
                                        alarme.setReceiver(Integer.parseInt(receiver));
                                        alarme.setLinha(Integer.parseInt(linha));
                                        try {
                                            alarme.setIdcliente(jpaCliente.findCliente(conta));
                                        } catch (Exception ex) {
                                            Logger.getLogger(ServidorSensor.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        alarme.setIdprotocolo(jpaProtocol.findProtocolo(evento));
                                        alarme.setParticao(0);
                                        alarme.setStatus("NÃO ATENDIDO");
                                        alarme.setZona(0);

                                        jpaAlarme.create(alarme);
                                        txtLog.append("Alarme registrado com sucesso. \n\n");
                                        txtLog.append("==================================================================\n\n");
                                    }
                                }
                                
                                saida = new BufferedOutputStream(serialPort.getOutputStream());
                                saida.write(6);
                                saida.flush();                                
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PortaSerialView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            if (ps.getStopBits() == 1.5) {
                ps.setStopBits(3);
            }

            //Nenhum
            int parity = 0;
            if (ps.getParidade().equals("Par")) {
                parity = 2;
            } else {
                if (ps.getParidade().equals("Marked")) {
                    parity = 3;
                } else {
                    if (ps.getParidade().equals("Ímpar")) {
                        parity = 1;
                    } else {
                        if (ps.getParidade().equals("Spaced")) {
                            parity = 4;
                        }
                    }
                }
            }

            serialPort.notifyOnDataAvailable(true);
            serialPort.setSerialPortParams(
                    ps.getBaudRate(),
                    ps.getNumBits(),
                    ps.getStopBits(),
                    parity);

        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TooManyListenersException ex) {
            Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PortInUseException ex) {
            Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPortException ex) {
            Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
