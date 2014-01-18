package br.com.fox.view;

import br.com.fox.util.CheckAbertura;
import br.com.fox.util.CheckFechamento;
import br.com.fox.util.ServidorSafeLink;
import br.com.fox.util.ServidorRouter;
import br.com.fox.util.PortaSerial;
import br.com.fox.util.ServidorSurGard;
import br.com.fox.util.TesteLinha;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author Dvr
 */
public class ServidorView extends javax.swing.JFrame {

    private int portaRouter = 9545;

    public ServidorView() {
        initComponents();
        setLocationRelativeTo(null);
        startService();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor");
        setIconImage(new ImageIcon(getClass().getResource("/icones/server-carbon-icon.png")).getImage());
        setResizable(false);

        txtLog.setEditable(false);
        txtLog.setColumns(20);
        txtLog.setLineWrap(true);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/Step-Forward-Normal-icon.png"))); // NOI18N
        btnStart.setText("Iniciar Serviços");
        btnStart.setEnabled(false);
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/Stop-Normal-Red-icon.png"))); // NOI18N
        btnStop.setText("Parar Serviços");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        jMenu1.setText("Configurações");

        jMenuItem2.setText("Porta Router");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Porta Serial");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Gerais");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Sistema");

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStart)
                        .addGap(18, 18, 18)
                        .addComponent(btnStop)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnStart, btnStop});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnStop))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    System.exit(0);
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    PortaSerialView aps = new PortaSerialView();
    aps.pack();
    aps.setLocationRelativeTo(null);
    aps.setVisible(true);
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    try {
        portaRouter = Integer.parseInt(JOptionPane.showInputDialog("Digite a porta do Router", portaRouter));
    } catch (NumberFormatException numberFormatException) {
        JOptionPane.showMessageDialog(null, "Porta Inválida", "ERRO", JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_jMenuItem2ActionPerformed

//Old btnStartActionPerformed..
private void startService() {
    btnStop.setEnabled(true);
    Thread t1 = new Thread(new ServidorRouter(portaRouter, txtLog));
    t1.start();

    try {
        File file = new File("serial.xml");

        SAXBuilder builder = new SAXBuilder();

        Document doc = builder.build(file);

        Element root = (Element) doc.getRootElement();

        List list = root.getChildren();

        Iterator i = list.iterator();

        while (i.hasNext()) {
            Element serial = (Element) i.next();

            PortaSerial ps = new PortaSerial();
            ps.setPorta(serial.getChildText("porta"));
            ps.setBaudRate(Integer.parseInt(serial.getChildText("baudRate")));
            ps.setNumBits(Integer.parseInt(serial.getChildText("numBits")));
            ps.setParidade(serial.getChildText("paridade"));
            ps.setStopBits(Integer.parseInt(serial.getChildText("stopBits")));
            ps.setStatus(Boolean.parseBoolean(serial.getChildText("status")));
            ps.setReceptora(serial.getChildText("receptora"));

            if (ps.getReceptora().equals("SafeLink")) {
                ServidorSafeLink ssl = new ServidorSafeLink(ps, txtLog);
                ssl.run();
            }

            if (ps.getReceptora().equals("Sur-Gard")) {
                ServidorSurGard ssg = new ServidorSurGard(ps, txtLog);
                ssg.run();
            }

            Thread t2 = new Thread(new TesteLinha());
            t2.start();

            Thread t3 = new Thread(new CheckAbertura());
            t3.start();

            Thread t4 = new Thread(new CheckFechamento());
            t4.start();
        }
    } catch (JDOMException ex) {
        Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
        txtLog.append("Nenhuma porta serial configurada.\n\n");
    }

//    btnStart.setEnabled(false);
    //btnStop.setEnabled(true);
}

private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
    System.exit(EXIT_ON_CLOSE);
}//GEN-LAST:event_btnStopActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    ConfigView c = new ConfigView();
    c.pack();
    c.setLocationRelativeTo(null);
    c.setVisible(true);
}//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        Thread t1 = new Thread(new ServidorRouter(portaRouter, txtLog));
        t1.start();

        try {
            File file = new File("serial.xml");

            SAXBuilder builder = new SAXBuilder();

            Document doc = builder.build(file);

            Element root = (Element) doc.getRootElement();

            List list = root.getChildren();

            Iterator i = list.iterator();

            while (i.hasNext()) {
                Element serial = (Element) i.next();

                PortaSerial ps = new PortaSerial();
                ps.setPorta(serial.getChildText("porta"));
                ps.setBaudRate(Integer.parseInt(serial.getChildText("baudRate")));
                ps.setNumBits(Integer.parseInt(serial.getChildText("numBits")));
                ps.setParidade(serial.getChildText("paridade"));
                ps.setStopBits(Integer.parseInt(serial.getChildText("stopBits")));
                ps.setStatus(Boolean.parseBoolean(serial.getChildText("status")));
                ps.setReceptora(serial.getChildText("receptora"));

                if (ps.getReceptora().equals("SafeLink")) {
                    ServidorSafeLink ssl = new ServidorSafeLink(ps, txtLog);
                    ssl.run();
                }

                if (ps.getReceptora().equals("Sur-Gard")) {
                    ServidorSurGard ssg = new ServidorSurGard(ps, txtLog);
                    ssg.run();
                }

                Thread t2 = new Thread(new TesteLinha());
                t2.start();

                Thread t3 = new Thread(new CheckAbertura());
                t3.start();

                Thread t4 = new Thread(new CheckFechamento());
                t4.start();
            }
        } catch (JDOMException ex) {
            Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
            txtLog.append("Nenhuma porta serial configurada.\n\n");
        }

    //    btnStart.setEnabled(false);
        btnStop.setEnabled(true);
    }//GEN-LAST:event_btnStartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        NativeLibrary.addSearchPath(                
                RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files (x86)\\VideoLAN\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

        System.setProperty(
                "jna.dump_memory", "false");

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader("lic.bin"));
                    String macLic = "";
                    while (in.ready()) {
                        macLic = in.readLine();
                    }

                    //Capturando Mac address
                    HashSet<String> macAddresses = new HashSet<String>();
                    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                    while (interfaces.hasMoreElements()) {
                        NetworkInterface ni = interfaces.nextElement();
                        byte[] mac = ni.getHardwareAddress();
                        if (mac != null) {
                            StringBuilder sbMac = new StringBuilder();
                            for (int i = 0; i < mac.length; i++) {
                                sbMac.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                            }
                            MessageDigest md = MessageDigest.getInstance("MD5");
                            md.update(sbMac.toString().getBytes());
                            byte[] bytes = md.digest();
                            StringBuilder macSys = new StringBuilder();
                            for (int i = 0; i < bytes.length; i++) {
                                int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
                                int parteBaixa = bytes[i] & 0xf;
                                if (parteAlta == 0) {
                                    macSys.append('0');
                                }
                                macSys.append(Integer.toHexString(parteAlta | parteBaixa));
                            }

                            macAddresses.add(macSys.toString());
                        }
                    }

                    //Criptografando arquivo
                    boolean valid = false;
                    for (String mac : macAddresses) {
                        if (macLic.trim().equals(mac.toString())) {
                            valid = true;
                        }
                    }
                    
                    //if (valid) {
                        new ServidorView().setVisible(true);
                    //} else {
                      //  JOptionPane.showMessageDialog(null, "Licença inválida");
                    //}

                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ServidorView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables
}
