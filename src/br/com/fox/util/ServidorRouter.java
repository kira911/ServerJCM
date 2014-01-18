package br.com.fox.util;

import br.com.fox.controller.ClienteJpaController;
import br.com.fox.controller.SinalRouterJpaController;
import br.com.fox.controller.exceptions.NonexistentEntityException;
import br.com.fox.db.Cliente;
import br.com.fox.db.SinalRouter;
import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import uk.co.caprica.vlcj.component.EmbeddedMediaListPlayerComponent;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

/**
 *
 * @author Dvr
 */
public class ServidorRouter implements Runnable {

    private SinalRouterJpaController jpaRouter = new SinalRouterJpaController();
    private ClienteJpaController jpaCliente = new ClienteJpaController();
    private BufferedReader read;
    private ServerSocket server;
    private Socket socket;
    private int portaRouter;
    private JTextArea txtLog;

    public ServidorRouter(int portaRouter, JTextArea txtLog) {
        this.portaRouter = portaRouter;
        this.txtLog = txtLog;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(portaRouter);
            txtLog.append("Abrindo porta: " + portaRouter + "\n\n");
        } catch (IOException ex) {
            txtLog.append("ERRO AO ABRIR PORTA: " + portaRouter + "\n");
            txtLog.append("ERRO: " + ex.getMessage() + "\n\n");
        }

        while (true) {
            try {
                socket = server.accept();
                InputStream entrada = socket.getInputStream();
                read = new BufferedReader(new InputStreamReader(entrada));

                // aguarda o recebimento de algum sinal
                String sinal = read.readLine();
                                
                txtLog.append("===========================Sinal do Router===========================\n\n");
                txtLog.append("Data: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "\n");
                txtLog.append("mensagem recebida: " + sinal + "\n\n");

                String ori = sinal.substring(10, 11).trim();
                String idr = sinal.substring(11, 17).trim();
                String nor = "";
                
                if (ori.equals("R")) {
                    nor = sinal.substring(17, 67).trim();
                }else{
                    if (ori.equals("O")) {
                        nor = "ROUTER MASTER";
                        StringBuilder sb = new StringBuilder(sinal);

                        sb.insert(17, String.format("%-50s", "ROUTER MASTER"));
                        sb.insert(144, String.format("%-62s", ""));
                        sinal = sb.toString();
                    }
                }
                
                String cod = sinal.substring(67, 77).trim();
                String dat = sinal.substring(77, 97).trim();
                String nuc = sinal.substring(97, 100).trim();
                String apl = sinal.substring(100, 110).trim();
                String ins = sinal.substring(110, 112).trim();
                String org = sinal.substring(112, 117).trim();
                String sub = sinal.substring(117, 127).trim();
                String nsb = sinal.substring(127, 132).trim();
                String sbn = sinal.substring(132, 142).trim();
                String cor = sinal.substring(142, 144).trim();
                String ips = sinal.substring(144, 174).trim();
                String pos = sinal.substring(174, 180).trim();
                
                /*
                System.out.println("cod = "+cod);
                System.out.println("dat = "+dat);
                System.out.println("nuc = "+nuc);
                System.out.println("apl = "+apl);
                System.out.println("ins = "+ins);
                System.out.println("org = "+org);
                System.out.println("sub = "+sub);
                System.out.println("nsb = "+nsb);
                System.out.println("sbn = "+sbn);
                System.out.println("cor = "+cor);
                System.out.println("ips = "+ips);
                System.out.println("pos = "+pos);                 
                */
                                
                final SinalRouter sr = new SinalRouter();
                sr.setOri(ori);
                    
                if (sr.getOri().equals("R")) {                   
                    sr.setCodCli(jpaCliente.findCliente(idr));
                }else{
                    if (sr.getOri().equals("O")) {                                     
                        sr.setCodCli(jpaCliente.findCliente(sbn));
                    }
                }
                
                sr.setNor(nor);
                try {
                    sr.setCod(Integer.parseInt(cod));
                } catch (NumberFormatException ex) {
                    sr.setCod(0);
                }
                sr.setDat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dat));
                try {
                    sr.setNuc(Integer.parseInt(nuc));
                } catch (NumberFormatException ex) {
                    sr.setNuc(0);
                }
                sr.setApl(apl);
                try {
                    sr.setIns(Integer.parseInt(ins));
                } catch (NumberFormatException ex) {
                    sr.setIns(0);
                }
                sr.setOrg(org);
                sr.setSub(sub);
                try {
                    sr.setNsb(Integer.parseInt(nsb));
                } catch (NumberFormatException ex) {
                    sr.setNsb(0);
                }
                try {
                    sr.setSbn(Integer.parseInt(sbn));
                } catch (NumberFormatException ex) {
                    sr.setSbn(0);
                }
                sr.setCor(cor);
                sr.setIps(ips);
                try {
                    sr.setPos(Integer.parseInt(pos));
                } catch (NumberFormatException ex) {
                    sr.setPos(0);
                }
                sr.setStatus("NÃO ATENDIDO");
                sr.setLog("");

                jpaRouter.create(sr);
                txtLog.append("Alarme registrado com sucesso. \n\n");
                txtLog.append("==================================================================\n\n");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());

                if (sr.getOrg().equals("VIPER")) {
                    //final EmbeddedMediaPlayerComponent component = new EmbeddedMediaListPlayerComponent();
                    //component.setVisible(true);
                    final EmbeddedMediaPlayerComponent componentRecord = new EmbeddedMediaListPlayerComponent();
                    componentRecord.setVisible(false);
                    
                    JPanel panel = new JPanel();
                    //panel.add(component);
                    panel.add(componentRecord);       
                    
                    

                    final JFrame frame = new JFrame("Gravando...");
                    frame.setSize(640, 480);
                    frame.setIconImage(new ImageIcon(getClass().getResource("/icones/Actions-media-record-icon.png")).getImage());
                    //frame.setLocationRelativeTo(null);
                    //frame.setContentPane(component);                    
                    frame.setContentPane(panel);
                    frame.setVisible(true);
                    frame.toBack(); 
                    //frame.revalidate();
                    //frame.repaint();
                    
                    //panel.revalidate();
                    //panel.repaint();
                    
                    frame.addWindowListener(new WindowAdapter() {

                        @Override
                        public void windowClosing(WindowEvent e) {
                            //component.getMediaPlayer().stop();
                            //component.getMediaPlayer().release();
                            //component.release();
                            
                            componentRecord.getMediaPlayer().stop();
                            componentRecord.getMediaPlayer().release();
                            componentRecord.release();                            
                                                        
                            frame.dispose();
                        }
                    });

                    StringBuilder fileName = new StringBuilder("");
                    fileName.append(sr.getNor().replace(" ", "_")).append("_").append(new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(sr.getDat())).append("_").append(sr.getNuc()).append(".flv");

                    //component.getMediaPlayer().playMedia("http://" + sr.getIps() + ":" + sr.getPos() + "/IPLLREDIR:" + sr.getNuc(), getMediaOptions("c:\\" + fileName.toString()));
                    //component.getMediaPlayer().playMedia("http://" + sr.getIps() + ":" + sr.getPos() + "/IPLLREDIR:" + sr.getNuc() );
                    
                    componentRecord.getMediaPlayer().playMedia("http://" + sr.getIps() + ":" + sr.getPos() + "/IPLLREDIR:" + sr.getNuc(), getMediaOptions("c:\\" + fileName.toString()) );
                                        
                    Thread t = new Thread(new CloseRecord(sr, componentRecord, frame, fileName.toString()));
                    t.start();

                    //frame.paintComponents(frame.getGraphics());
                    
                }

            } catch (Exception ex) {
                txtLog.append("ERRO SERVIDOR ROUTER: " + ex.getMessage() + "\n\n");
                txtLog.append("==================================================================\n\n");
            }
        }

    }

    private String[] getMediaOptions(String destination) {
        String SOUT = ":sout=#transcode{vcodec=FLV1,vb=%d,scale=%f}:duplicate{dst=file{dst=%s}}";
        String FPS = ":screen-fps=%d";
        String CACHING = ":screen-caching=%d";
        //int fps = 20;
        int fps = 10;
        int caching = 500;
        int bits = 4096;
        float scale = 0.5f;

        return new String[] {
                    String.format(SOUT, bits, scale, destination),
                    String.format(FPS, fps),
                    String.format(CACHING, caching)
                };
    }
}
