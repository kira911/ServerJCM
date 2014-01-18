package br.com.fox.view;

import br.com.fox.util.Config;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Dvr
 */
public class ConfigView extends javax.swing.JFrame {

    public ConfigView() {
        initComponents();
        carregaConfig();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser = new javax.swing.JFileChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPathAlarmeRouter = new javax.swing.JTextField();
        btnSearchPathAlarmeRouter = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtPathDestRouter = new javax.swing.JTextField();
        btnSearchPathDestRouter = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtSmtpHostEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSmtpPortaEmail = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        txtUserEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPassEmail = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtHostFTP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPortaFTP = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        txtUserFTP = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPassFTP = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        txtPathDestFTP = new javax.swing.JTextField();
        btnSearchPathDestFTP = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        jFileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurações Gerais");
        setIconImage(new ImageIcon(getClass().getResource("/icones/Utilities-icon.png")).getImage());
        setResizable(false);

        jLabel1.setText("Pasta alarme do Router:");

        txtPathAlarmeRouter.setEditable(false);

        btnSearchPathAlarmeRouter.setText("Procurar...");
        btnSearchPathAlarmeRouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPathAlarmeRouterActionPerformed(evt);
            }
        });

        jLabel2.setText("Pasta destino:");

        txtPathDestRouter.setEditable(false);

        btnSearchPathDestRouter.setText("Procurar...");
        btnSearchPathDestRouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPathDestRouterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPathDestRouter, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(txtPathAlarmeRouter, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearchPathAlarmeRouter)
                    .addComponent(btnSearchPathDestRouter))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSearchPathAlarmeRouter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchPathDestRouter))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtPathAlarmeRouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPathDestRouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Snapshot", jPanel1);

        jLabel3.setText("Smtp Host:");

        jLabel4.setText("Smtp Porta:");

        txtSmtpPortaEmail.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        jLabel5.setText("Username:");

        jLabel6.setText("Senha:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSmtpHostEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSmtpPortaEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSmtpHostEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSmtpPortaEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPassEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Email", jPanel2);

        jLabel7.setText("Host:");

        jLabel8.setText("Porta:");

        jLabel9.setText("Username:");

        jLabel10.setText("Senha:");

        jLabel11.setText("Pasta destino:");

        txtPathDestFTP.setEditable(false);

        btnSearchPathDestFTP.setText("Procurar...");
        btnSearchPathDestFTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPathDestFTPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPortaFTP, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtPathDestFTP, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchPathDestFTP))
                    .addComponent(txtHostFTP, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassFTP, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserFTP, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtHostFTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPortaFTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtUserFTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPassFTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtPathDestFTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchPathDestFTP))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("FTP", jPanel3);

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/save-icon.png"))); // NOI18N
        btnSave.setText("Salvar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/Log-Out-icon.png"))); // NOI18N
        btnExit.setText("Sair");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                .addComponent(btnExit)
                .addContainerGap())
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnExit, btnSave});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExit)
                    .addComponent(btnSave))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
    dispose();
}//GEN-LAST:event_btnExitActionPerformed

private void btnSearchPathAlarmeRouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPathAlarmeRouterActionPerformed
    if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        txtPathAlarmeRouter.setText(jFileChooser.getSelectedFile().getAbsolutePath());
    }
}//GEN-LAST:event_btnSearchPathAlarmeRouterActionPerformed

private void btnSearchPathDestRouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPathDestRouterActionPerformed
    if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        txtPathDestRouter.setText(jFileChooser.getSelectedFile().getAbsolutePath());
    }
}//GEN-LAST:event_btnSearchPathDestRouterActionPerformed

private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    String pathAlarmeRouter = txtPathAlarmeRouter.getText();
    String pathDestRouter = txtPathDestRouter.getText();
    String smtpHostEmail = txtSmtpHostEmail.getText().trim();
    Integer smtpPortaEmail = (Integer) txtSmtpPortaEmail.getValue();
    String userEmail = txtUserEmail.getText().trim();
    String passEmail = String.valueOf(txtPassEmail.getPassword());
    String hostFTP = txtHostFTP.getText().trim();
    Integer portaFTP = (Integer) txtPortaFTP.getValue();
    String userFTP = txtUserFTP.getText().trim();
    String passFTP = String.valueOf(txtPassFTP.getPassword());
    String pathDestFTP = txtPathDestFTP.getText();

    Config c = new Config();
    c.setPathRouterSnapshot(pathAlarmeRouter);
    c.setPathDestSnapshot(pathDestRouter);
    c.setSmtpHostEmail(smtpHostEmail);
    c.setSmtpPortEmail(smtpPortaEmail);
    c.setUserEmail(userEmail);
    c.setPassEmail(passEmail);
    c.setHostFTP(hostFTP);
    c.setPortaFTP(portaFTP);
    c.setUserFTP(userFTP);
    c.setPassFTP(passFTP);
    c.setPathDestFTP(pathDestFTP);

    List<Config> list = new ArrayList<Config>();
    list.add(c);

    XStream xs = new XStream(new DomDriver());
    String toXML = xs.toXML(list);

    File file = new File("config.xml");
    PrintWriter pw = null;
    try {
        pw = new PrintWriter(file);
        pw.append(toXML);

        JOptionPane.showMessageDialog(null, "Dados salvos com sucesso");

    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        Logger.getLogger(ConfigView.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        pw.close();
    }

}//GEN-LAST:event_btnSaveActionPerformed

private void btnSearchPathDestFTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPathDestFTPActionPerformed
    if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        txtPathDestFTP.setText(jFileChooser.getSelectedFile().getAbsolutePath());
    }
}//GEN-LAST:event_btnSearchPathDestFTPActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfigView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ConfigView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearchPathAlarmeRouter;
    private javax.swing.JButton btnSearchPathDestFTP;
    private javax.swing.JButton btnSearchPathDestRouter;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtHostFTP;
    private javax.swing.JPasswordField txtPassEmail;
    private javax.swing.JPasswordField txtPassFTP;
    private javax.swing.JTextField txtPathAlarmeRouter;
    private javax.swing.JTextField txtPathDestFTP;
    private javax.swing.JTextField txtPathDestRouter;
    private javax.swing.JSpinner txtPortaFTP;
    private javax.swing.JTextField txtSmtpHostEmail;
    private javax.swing.JSpinner txtSmtpPortaEmail;
    private javax.swing.JTextField txtUserEmail;
    private javax.swing.JTextField txtUserFTP;
    // End of variables declaration//GEN-END:variables

    private void carregaConfig() {
        try {
            File file = new File("config.xml");
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(file);
            Element root = doc.getRootElement();
            List list = root.getChildren();
            Iterator i = list.iterator();

            while (i.hasNext()) {
                Element xml = (Element) i.next();

                Config c = new Config();
                c.setPathRouterSnapshot(xml.getChildText("pathRouterSnapshot"));
                c.setPathDestSnapshot(xml.getChildText("pathDestSnapshot"));
                c.setSmtpHostEmail(xml.getChildText("smtpHostEmail"));
                c.setSmtpPortEmail(Integer.parseInt(xml.getChildText("smtpPortEmail")));
                c.setUserEmail(xml.getChildText("userEmail"));
                c.setPassEmail(xml.getChildText("passEmail"));
                c.setHostFTP(xml.getChildText("hostFTP"));
                c.setPortaFTP(Integer.parseInt(xml.getChildText("portaFTP")));
                c.setUserFTP(xml.getChildText("userFTP"));
                c.setPassFTP(xml.getChildText("passFTP"));
                c.setPathDestFTP(xml.getChildText("pathDestFTP"));

                txtPathAlarmeRouter.setText(c.getPathRouterSnapshot());
                txtPathDestRouter.setText(c.getPathDestSnapshot());
                txtSmtpHostEmail.setText(c.getSmtpHostEmail());
                txtSmtpPortaEmail.setValue(c.getSmtpPortEmail());
                txtUserEmail.setText(c.getUserEmail());
                txtPassEmail.setText(c.getPassEmail());
                txtHostFTP.setText(c.getHostFTP());
                txtPortaFTP.setValue(c.getPortaFTP());
                txtUserFTP.setText(c.getUserFTP());
                txtPassFTP.setText(c.getPassFTP());
                txtPathDestFTP.setText(c.getPathDestFTP());

            }
        } catch (JDOMException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
