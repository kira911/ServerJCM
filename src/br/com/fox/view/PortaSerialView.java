package br.com.fox.view;

import br.com.fox.util.PortaSerial;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.towel.swing.table.TableFilter;
import gnu.io.CommPortIdentifier;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Dvr
 */
public class PortaSerialView extends javax.swing.JFrame {

    public PortaSerialView() {
        initComponents();
        carregaLista();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbPortaSerial = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cboxPorta = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cboxBaudRate = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cboxNumBits = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cboxParidade = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cboxStopBits = new javax.swing.JComboBox();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSalve = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        checkAtivo = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        cboxReceptora = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Config. de Porta Serial");
        setIconImage(new ImageIcon(getClass().getResource("/icones/Utilities-icon.png")).getImage());
        setResizable(false);

        tbPortaSerial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tbPortaSerial.setModel(new ObjectTableModel(PortaSerial.class, "status,porta,baudRate,numBits,paridade,stopBits,receptora"));
        tbPortaSerial.setGridColor(new java.awt.Color(255, 255, 255));
        tbPortaSerial.getTableHeader().setReorderingAllowed(false);
        tbPortaSerial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPortaSerialMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPortaSerial);

        jLabel1.setText("Porta Serial:");

        addPorta();

        jLabel2.setText("Baud Rate:");

        cboxBaudRate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "110", "300", "600", "1200", "2400", "4800", "9600", "14400", "19200", "38400", "56000", "57600" }));
        cboxBaudRate.setSelectedIndex(3);

        jLabel3.setText("Números de bits:");

        cboxNumBits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "6", "7", "8" }));

        jLabel4.setText("Paridade:");

        cboxParidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nenhum", "Ímpar", "Par", "Marked", "Spaced" }));

        jLabel5.setText("Stop Bits:");

        cboxStopBits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "1.5", "2" }));

        btnAdd.setText("Incluir");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Excluir");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSalve.setText("Salvar");
        btnSalve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalveActionPerformed(evt);
            }
        });

        btnExit.setText("Sair");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel6.setText("Status:");

        checkAtivo.setText("Ativo");

        jLabel7.setText("Receptora:");

        cboxReceptora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SafeLink", "Sur-Gard" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalve))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboxBaudRate, 0, 1, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboxNumBits, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxParidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboxStopBits, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel7))
                            .addComponent(cboxReceptora, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkAtivo)
                            .addComponent(jLabel6))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExit)
                        .addContainerGap())))
            .addComponent(jScrollPane1)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdd, btnDelete, btnExit, btnSalve});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cboxReceptora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboxPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboxBaudRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboxNumBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboxParidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboxStopBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(checkAtivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnSalve)
                    .addComponent(btnExit))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
    PortaSerial ps = new PortaSerial();
    ps.setPorta(cboxPorta.getSelectedItem().toString());
    ps.setBaudRate(Integer.parseInt(cboxBaudRate.getSelectedItem().toString()));
    ps.setNumBits(Integer.parseInt(cboxNumBits.getSelectedItem().toString()));
    ps.setParidade(cboxParidade.getSelectedItem().toString());
    ps.setStopBits(Integer.parseInt(cboxStopBits.getSelectedItem().toString()));
    ps.setReceptora(cboxReceptora.getSelectedItem().toString());
    ps.setStatus(checkAtivo.isSelected());

    ObjectTableModel<PortaSerial> model = (ObjectTableModel<PortaSerial>) tbPortaSerial.getModel();

    List<PortaSerial> list = model.getData();

    if (!list.isEmpty()) {
        for (PortaSerial vps : list) {
            if (vps.equals(ps)) {
                JOptionPane.showMessageDialog(null, "Porta serial já cadastrada", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                model.add(ps);
            }
        }
    } else {
        model.add(ps);
    }



    tbPortaSerial.setDefaultRenderer(Object.class, new ColorStatus());
    tbPortaSerial.setDefaultRenderer(Integer.class, new ColorStatus());

    TableColumn column = tbPortaSerial.getColumn(tbPortaSerial.getColumnName(0));
    column.setCellRenderer(new CheckBoxRenderer());

    /*try {
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
    saida = new BufferedOutputStream(serialPort.getOutputStream());
    saida.write(6);
    saida.flush();
    
    if (mensagem.length() == 36) {
    String receiver = mensagem.substring(14, 15);
    String linha = mensagem.substring(15, 16);
    String conta = mensagem.substring(17, 21);
    String evento = mensagem.substring(25, 29);
    String particao = mensagem.substring(30, 32);
    String zona = mensagem.substring(33, 36);
    
    Alarme alarme = new Alarme();
    alarme.setDataHora(new Date());
    alarme.setReceiver(Integer.parseInt(receiver));
    alarme.setLinha(Integer.parseInt(linha));
    //alarme.setIdcliente(jpaCliente.findCliente(conta));
    //alarme.setIdprotocolo(jpaProtocol.findProtocolo(evento));
    alarme.setParticao(Integer.parseInt(particao));
    alarme.setStatus("NÃO ATENDIDO");
    alarme.setZona(Integer.parseInt(zona));
    
    System.out.println(alarme.toString());
    //jpaAlarme.create(alarme);
    } else {
    if (mensagem.length() == 24) {
    String receiver = mensagem.substring(14, 15);
    String linha = mensagem.substring(15, 16);
    String conta = mensagem.substring(17, 21);
    String evento = mensagem.substring(22, 24);
    
    Alarme alarme = new Alarme();
    alarme.setDataHora(new Date());
    alarme.setReceiver(Integer.parseInt(receiver));
    alarme.setLinha(Integer.parseInt(linha));
    //alarme.setIdcliente(jpaCliente.findCliente(conta));
    //alarme.setIdprotocolo(jpaProtocol.findProtocolo(evento));
    alarme.setParticao(0);
    alarme.setStatus("NÃO ATENDIDO");
    alarme.setZona(0);
    
    System.out.println(alarme.toString());
    //jpaAlarme.create(alarme);
    }
    }
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
    
    ObjectTableModel<PortaSerial> model = (ObjectTableModel<PortaSerial>) tbPortaSerial.getModel();
    model.add(ps);
    
    tbPortaSerial.setDefaultRenderer(Object.class, new ColorStatus());
    tbPortaSerial.setDefaultRenderer(Integer.class, new ColorStatus());
    
    TableColumn column = tbPortaSerial.getColumn(tbPortaSerial.getColumnName(0));
    column.setCellRenderer(new CheckBoxRenderer());
    
    } catch (UnsupportedCommOperationException ex) {
    Logger.getLogger(PortaSerialView.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TooManyListenersException ex) {
    Logger.getLogger(PortaSerialView.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
    Logger.getLogger(PortaSerialView.class.getName()).log(Level.SEVERE, null, ex);
    } catch (PortInUseException ex) {
    Logger.getLogger(PortaSerialView.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchPortException ex) {
    Logger.getLogger(PortaSerialView.class.getName()).log(Level.SEVERE, null, ex);
    }*/
}//GEN-LAST:event_btnAddActionPerformed

private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
    dispose();
}//GEN-LAST:event_btnExitActionPerformed

private void btnSalveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalveActionPerformed
    XStream xs = new XStream(new DomDriver());

    List<PortaSerial> list = ((ObjectTableModel<PortaSerial>) tbPortaSerial.getModel()).getData();

    String toXML = xs.toXML(list);

    File file = new File("serial.xml");
    PrintWriter pw = null;
    try {
        pw = new PrintWriter(file);
        pw.append(toXML);
        
        JOptionPane.showMessageDialog(null, "Dados salvos com sucesso");
        
    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        Logger.getLogger(PortaSerialView.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        pw.close();
    }

}//GEN-LAST:event_btnSalveActionPerformed

private void tbPortaSerialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPortaSerialMouseClicked
    if (evt.getClickCount() == 2) {
        int row = tbPortaSerial.getSelectedRow();
        ObjectTableModel<PortaSerial> model = (ObjectTableModel<PortaSerial>) tbPortaSerial.getModel();

        PortaSerial ps = model.getValue(row);

        cboxPorta.setSelectedItem(ps.getPorta());
        cboxBaudRate.setSelectedItem(ps.getBaudRate().toString());
        cboxNumBits.setSelectedItem(ps.getNumBits().toString());
        cboxParidade.setSelectedItem(ps.getParidade());
        cboxStopBits.setSelectedItem(ps.getStopBits().toString());
        checkAtivo.setSelected(ps.isStatus());

        btnAdd.setEnabled(false);
        btnDelete.setEnabled(true);
    }

}//GEN-LAST:event_tbPortaSerialMouseClicked

private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    int row = tbPortaSerial.getSelectedRow();
    ObjectTableModel<PortaSerial> model = (ObjectTableModel<PortaSerial>) tbPortaSerial.getModel();
    model.remove(row);

    clear();
}//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(PortaSerialView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PortaSerialView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PortaSerialView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PortaSerialView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PortaSerialView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSalve;
    private javax.swing.JComboBox cboxBaudRate;
    private javax.swing.JComboBox cboxNumBits;
    private javax.swing.JComboBox cboxParidade;
    private javax.swing.JComboBox cboxPorta;
    private javax.swing.JComboBox cboxReceptora;
    private javax.swing.JComboBox cboxStopBits;
    private javax.swing.JCheckBox checkAtivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbPortaSerial;
    // End of variables declaration//GEN-END:variables

    private void addPorta() {
        Enumeration listaDePortas = CommPortIdentifier.getPortIdentifiers();

        cboxPorta.removeAllItems();
        while (listaDePortas.hasMoreElements()) {
            CommPortIdentifier ips =
                    (CommPortIdentifier) listaDePortas.nextElement();

            cboxPorta.addItem(ips.getName());
        }
    }

    private class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

        CheckBoxRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (((Boolean) value) == true) {
                if (isSelected) {
                    setBackground(table.getSelectionBackground());
                } else {
                    setBackground(new Color(0, 153, 51));
                }
            } else {
                if (isSelected) {
                    setBackground(table.getSelectionBackground());
                } else {
                    setBackground(new Color(204, 0, 0));
                }
            }
            setSelected((value != null && ((Boolean) value).booleanValue()));
            return this;
        }
    }

    private class ColorStatus extends DefaultTableCellRenderer {

        public ColorStatus() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (table.getValueAt(row, 0).equals(true)) {
                if (isSelected) {
                    comp.setBackground(table.getSelectionBackground());
                    comp.setForeground(table.getSelectionForeground());
                } else {
                    comp.setBackground(new Color(0, 153, 51));
                    comp.setForeground(Color.WHITE);
                }
            } else {
                if (isSelected) {
                    comp.setBackground(table.getSelectionBackground());
                    comp.setForeground(table.getSelectionForeground());
                } else {
                    comp.setBackground(new Color(204, 0, 0));
                    comp.setForeground(Color.WHITE);
                }

            }

            return comp;
        }
    }

    private void carregaLista() {
        try {
            File file = new File("serial.xml");

            SAXBuilder builder = new SAXBuilder();

            Document doc = builder.build(file);

            Element root = (Element) doc.getRootElement();

            List list = root.getChildren();

            Iterator i = list.iterator();
            
            AnnotationResolver resolver = new AnnotationResolver(PortaSerial.class); 
            ObjectTableModel<PortaSerial> model = new ObjectTableModel<>(resolver, "porta,baudRate,numBits,paridade,stopBits,status,receptora");    
            TableFilter filter = new TableFilter(tbPortaSerial.getTableHeader(), model);

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

                //ObjectTableModel<PortaSerial> model = (ObjectTableModel<PortaSerial>) tbPortaSerial.getModel();

                model.add(ps);

                tbPortaSerial.setDefaultRenderer(Object.class, new ColorStatus());
                tbPortaSerial.setDefaultRenderer(Integer.class, new ColorStatus());

                TableColumn column = tbPortaSerial.getColumn(tbPortaSerial.getColumnName(0));
                column.setCellRenderer(new CheckBoxRenderer());
            }
            
            tbPortaSerial.setModel(filter);
        } catch (JDOMException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void clear() {
        cboxBaudRate.setSelectedIndex(3);
        cboxNumBits.setSelectedIndex(0);
        cboxParidade.setSelectedIndex(0);
        cboxPorta.setSelectedIndex(0);
        cboxStopBits.setSelectedIndex(0);
        cboxReceptora.setSelectedIndex(0);
        checkAtivo.setSelected(false);

        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
    }
}
