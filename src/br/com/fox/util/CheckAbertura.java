package br.com.fox.util;

import br.com.fox.controller.AlarmeJpaController;
import br.com.fox.controller.ClienteJpaController;
import br.com.fox.controller.ProtocoloJpaController;
import br.com.fox.db.Alarme;
import br.com.fox.db.Cliente;
import br.com.fox.db.Horario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Seconds;

/**
 *
 * @author Dvr
 */
public class CheckAbertura implements Runnable {

    private ClienteJpaController jpaCliente = new ClienteJpaController();
    private ProtocoloJpaController jpaProtocol = new ProtocoloJpaController();
    private AlarmeJpaController jpaAlarme = new AlarmeJpaController();

    @Override
    public void run() {
        
        while (true) {
            try {
                List<Cliente> listClinte = jpaCliente.findClienteEntities();

                for (Cliente cliente : listClinte) {
                    try {
                        // Checar alarme de desarme
                        //Alarme a = jpaAlarme.findAlarmeArmado(cliente);
                        Alarme a = jpaAlarme.findAlarmeDesarmado(cliente);
                        List<Horario> listHorario = cliente.getHorarioList();

                        DateTime dtime = new DateTime();
                        String diaSemana = "";

                        if (dtime.getDayOfWeek() == 1) {
                            diaSemana = "Segunda";
                        }

                        if (dtime.getDayOfWeek() == 2) {
                            diaSemana = "Terça";
                        }

                        if (dtime.getDayOfWeek() == 3) {
                            diaSemana = "Quarta";
                        }

                        if (dtime.getDayOfWeek() == 4) {
                            diaSemana = "Quinta";
                        }

                        if (dtime.getDayOfWeek() == 5) {
                            diaSemana = "Sexta";
                        }

                        if (dtime.getDayOfWeek() == 6) {
                            diaSemana = "Sábado";
                        }

                        if (dtime.getDayOfWeek() == 7) {
                            diaSemana = "Domingo";
                        }

                        if( a != null ) {
                                                            
                            for (Horario horario : listHorario) {
                                if (horario.getDiaSemana().equals(diaSemana)) {

                                    long horaAbre = new DateTime(horario.getHoraAbre()).getMinuteOfDay();
                                    long horaFecha = new DateTime(horario.getHoraFecha()).getMinuteOfDay();

                                    if (horaAbre > 0 || horaFecha > 0) {
                                        String dataRecebimento = new SimpleDateFormat("dd/MM/yyyy").format(a.getDataRecebimento());
                                        String dataSistema = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

                                        long minAbertura = new DateTime(horario.getHoraAbre()).getMinuteOfDay() + 30; //folga de meia hora
                                        long minSistema = new DateTime().getMinuteOfDay();

                                        if (!dataRecebimento.equals(dataSistema)) {
                                            if (minSistema > minAbertura) {
                                                Alarme warningAlarme = new Alarme();
                                                warningAlarme.setDataRecebimento(new Date());
                                                warningAlarme.setReceiver(0);
                                                warningAlarme.setLinha(0);
                                                warningAlarme.setIdcliente(cliente);
                                                warningAlarme.setIdprotocolo(jpaProtocol.findProtocolo(238)); // NAO DESARMADO
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
                        } else {                            
                            
                            for (Horario horario : listHorario) {
                                if (horario.getDiaSemana().equals(diaSemana)) {

                                    long horaAbre = new DateTime(horario.getHoraAbre()).getMinuteOfDay();
                                    long horaFecha = new DateTime(horario.getHoraFecha()).getMinuteOfDay();

                                    if (horaAbre > 0 || horaFecha > 0) {
                                        String dataSistema = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

                                        long minAbertura = new DateTime(horario.getHoraAbre()).getMinuteOfDay() + 30; //folga de meia hora
                                        long minSistema = new DateTime().getMinuteOfDay();         
                                        
                                        if (minSistema > minAbertura) {                                            
                                            Alarme warningAlarme = new Alarme();
                                            warningAlarme.setDataRecebimento(new Date());
                                            warningAlarme.setReceiver(0);
                                            warningAlarme.setLinha(0);
                                            warningAlarme.setIdcliente(cliente);
                                            warningAlarme.setIdprotocolo(jpaProtocol.findProtocolo(238)); // NAO DESARMADO
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
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }

                //Thread.sleep(600000); // 10 min
                Thread.sleep(1800000); // 30 min
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
                System.out.println("========================================");
            }
        }
        
    }

    public static long obterSegundos(String hora1) {
        String[] time = hora1.split(":");
        try {
            return (Integer.parseInt(time[1]) + (Integer.parseInt(time[0]) * 60)) * 60;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
