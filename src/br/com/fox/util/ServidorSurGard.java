package br.com.fox.util;

import br.com.fox.controller.AlarmeJpaController;
import br.com.fox.controller.ClienteJpaController;
import br.com.fox.controller.CucJpaController;
import br.com.fox.controller.ProtocoloJpaController;
import br.com.fox.db.Alarme;
import br.com.fox.db.Cliente;
import br.com.fox.db.Horario;
import br.com.fox.db.Protocolo;
import br.com.fox.view.PortaSerialView;
import br.com.fox.view.ServidorView;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import org.joda.time.DateTime;

/**
 *
 * @author Dvr
 */
public class ServidorSurGard {

    private ClienteJpaController jpaCliente = new ClienteJpaController();
    private ProtocoloJpaController jpaProtocol = new ProtocoloJpaController();
    private AlarmeJpaController jpaAlarme = new AlarmeJpaController();
    private CucJpaController jpaCuc = new CucJpaController();
    private PortaSerial ps;
    private JTextArea txtLog;

    public ServidorSurGard(PortaSerial ps, JTextArea txtLog) {
        this.ps = ps;
        this.txtLog = txtLog;
    }
    
    public long obterSegundos(String hora1) {
        String[] time = hora1.split(":");
        try {
            return (Integer.parseInt(time[1]) + (Integer.parseInt(time[0]) * 60)) * 60;
        } catch (NumberFormatException e) {
            return 0;
        }
    }        

    public void run() {
        try {
            txtLog.append("Abrindo porta: " + ps.getPorta() + "\n\n");
            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(ps.getPorta());
            final SerialPort serialPort = (SerialPort) portId.open(PortaSerialView.class.getName(), 0);
            final InputStream is = serialPort.getInputStream();

            serialPort.addEventListener(new SerialPortEventListener() {

                @Override
                public void serialEvent(SerialPortEvent spe) {
                    BufferedOutputStream saida = null;
                    try {
                        DataInputStream data = new DataInputStream(is);

                        byte[] assinatura = new byte[21];
                        data.read(assinatura);

                        String mensagem = new String(assinatura);              

                        if (mensagem.substring(0, 1).equals("5")) {                                                        

                            Alarme alarme = null;
                            
                            txtLog.append("===========================Sinal do Sensor===========================\n\n");
                            txtLog.append("Data: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "\n");
                            txtLog.append("Mensagem recebida do CONTACT ID: " + mensagem + "\n\n");

                            String receiver = mensagem.substring(1, 3);
                            String linha = mensagem.substring(3, 4);
                            String conta = mensagem.substring(7, 11);
                            String evento = mensagem.substring(11, 15);
                            String particao = mensagem.substring(15, 17);
                            String zona = mensagem.substring(17, 20);
                            
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

                            Cliente cliente = jpaCliente.findCliente(conta);      
                            if (cliente != null) {                        
                                
                                if( cliente.getAtivo().equals("true" ) ) {
                                    
                                    alarme = new Alarme();
                                    alarme.setDataRecebimento(new Date());
                                    alarme.setReceiver(Integer.parseInt(receiver));
                                    alarme.setLinha(Integer.parseInt(linha));
                                    alarme.setIdcliente(jpaCliente.findCliente(conta));
                                    alarme.setIdprotocolo(protocolo);
                                    alarme.setParticao(Integer.parseInt(particao));
                                    alarme.setStatus("NÃO ATENDIDO");
                                    alarme.setZona(Integer.parseInt(zona));
                                    alarme.setLog("");

                                    jpaAlarme.create(alarme);
                                    txtLog.append("Alarme registrado com sucesso. \n\n");
                                    txtLog.append("==================================================================\n\n");                                    
                                }
                            }                                                        
                            
                            if( alarme != null ) {
                            
                                DateTime dtime = new DateTime();
                                String diaSemana = "";
                                if (dtime.getDayOfWeek() == 1) 
                                    diaSemana = "Segunda";

                                if (dtime.getDayOfWeek() == 2) 
                                    diaSemana = "Terça";

                                if (dtime.getDayOfWeek() == 3) 
                                    diaSemana = "Quarta";

                                if (dtime.getDayOfWeek() == 4) 
                                    diaSemana = "Quinta";

                                if (dtime.getDayOfWeek() == 5) 
                                    diaSemana = "Sexta";

                                if (dtime.getDayOfWeek() == 6) 
                                    diaSemana = "Sábado";

                                if (dtime.getDayOfWeek() == 7) 
                                    diaSemana = "Domingo";                                                       

                                List<Horario> listHorario = cliente.getHorarioList();

                                for (Horario horario : listHorario) {
                                    if (horario.getDiaSemana().equals(diaSemana)) {

                                        // DESARMADOS
                                        if( alarme.getIdprotocolo().getIdprotocolo().equals(66) ||
                                            alarme.getIdprotocolo().getIdprotocolo().equals(70) ||    
                                            alarme.getIdprotocolo().getIdprotocolo().equals(71) ||
                                            alarme.getIdprotocolo().getIdprotocolo().equals(146) || 
                                            alarme.getIdprotocolo().getIdprotocolo().equals(148) ||   
                                            alarme.getIdprotocolo().getIdprotocolo().equals(149) ||  
                                            alarme.getIdprotocolo().getIdprotocolo().equals(152) ||    
                                            alarme.getIdprotocolo().getIdprotocolo().equals(153) ||   
                                            alarme.getIdprotocolo().getIdprotocolo().equals(154) ||  
                                            alarme.getIdprotocolo().getIdprotocolo().equals(158)) {


                                            long abre = obterSegundos(new SimpleDateFormat("HH:mm").format(horario.getHoraAbre()));
                                            long horaSistema = obterSegundos(new SimpleDateFormat("HH:mm").format(new Date()));
                                            long diferenca = horaSistema - abre;

                                            // folga de meia hora
                                            if (diferenca > 1800 || diferenca < -1800) {
                                                Alarme warningAlarme = new Alarme();
                                                warningAlarme.setDataRecebimento(new Date());
                                                warningAlarme.setReceiver(0);
                                                warningAlarme.setLinha(0);
                                                warningAlarme.setIdcliente(cliente);                                            
                                                warningAlarme.setIdprotocolo(jpaProtocol.findProtocolo(240)); // DESARMARDO FORA DE HORARIO
                                                warningAlarme.setParticao(0);
                                                warningAlarme.setStatus("NÃO ATENDIDO");
                                                warningAlarme.setZona(0);                                                                                   

                                                jpaAlarme.create(warningAlarme);

                                                break;
                                            }                                                                                                                                                                
                                        }

                                        // ARMADOS
                                        if( alarme.getIdprotocolo().getIdprotocolo().equals(2) ||
                                            alarme.getIdprotocolo().getIdprotocolo().equals(75) ||    
                                            alarme.getIdprotocolo().getIdprotocolo().equals(97) ||
                                            alarme.getIdprotocolo().getIdprotocolo().equals(98) || 
                                            alarme.getIdprotocolo().getIdprotocolo().equals(99) ||   
                                            alarme.getIdprotocolo().getIdprotocolo().equals(101) ||  
                                            alarme.getIdprotocolo().getIdprotocolo().equals(102) ||    
                                            alarme.getIdprotocolo().getIdprotocolo().equals(103) ||   
                                            alarme.getIdprotocolo().getIdprotocolo().equals(161) ||  
                                            alarme.getIdprotocolo().getIdprotocolo().equals(215) ||
                                            alarme.getIdprotocolo().getIdprotocolo().equals(216) ||
                                            alarme.getIdprotocolo().getIdprotocolo().equals(217) ||
                                            alarme.getIdprotocolo().getIdprotocolo().equals(219) ||
                                            alarme.getIdprotocolo().getIdprotocolo().equals(220) ||
                                            alarme.getIdprotocolo().getIdprotocolo().equals(221) ||
                                            alarme.getIdprotocolo().getIdprotocolo().equals(222)) {


                                            long fecha = obterSegundos(new SimpleDateFormat("HH:mm").format(horario.getHoraFecha()));
                                            long horaSistema = obterSegundos(new SimpleDateFormat("HH:mm").format(new Date()));
                                            long diferenca = horaSistema - fecha;

                                            //folga de meia hora
                                            if (diferenca > 1800 || diferenca < -1800) {
                                                Alarme warningAlarme = new Alarme();
                                                warningAlarme.setDataRecebimento(new Date());
                                                warningAlarme.setReceiver(0);
                                                warningAlarme.setLinha(0);
                                                warningAlarme.setIdcliente(cliente);                                            
                                                warningAlarme.setIdprotocolo(jpaProtocol.findProtocolo(239)); // ARMADO FORA DE HORARIO
                                                warningAlarme.setParticao(0);
                                                warningAlarme.setStatus("NÃO ATENDIDO");
                                                warningAlarme.setZona(0);
                                                //warningAlarme.setCheckHorario("false");  

                                                jpaAlarme.create(warningAlarme);
                                            }                                                                                                                                                       
                                        }                                                                                                                                                                                    
                                    }
                                }                                                                                                                                                                                                    
                            }                                                                                                                                                                         
                        }
                                                
                        //Mensagem de retorno
                        saida = new BufferedOutputStream(serialPort.getOutputStream());
                        saida.write(6);
                        saida.flush();
                        
                    } catch (Exception ex) {
                        Logger.getLogger(PortaSerialView.class.getName()).log(Level.SEVERE, null, ex);
                        txtLog.append("ERRO SERVIDOR SURGARD: " + ex.getMessage() + "\n\n");
                        txtLog.append("==================================================================\n\n");
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
