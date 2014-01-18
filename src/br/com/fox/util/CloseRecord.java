package br.com.fox.util;

import br.com.fox.controller.SinalRouterJpaController;
import br.com.fox.db.SinalRouter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

/**
 *
 * @author Dvr
 */
public class CloseRecord implements Runnable {

    private Config config = new Config();
    private SinalRouterJpaController jpa = new SinalRouterJpaController();
    private SinalRouter sinalRouter;
    private EmbeddedMediaPlayerComponent component;
    private JFrame frame;
    private String nameFile;

    public CloseRecord(SinalRouter sinalRouter, EmbeddedMediaPlayerComponent component, JFrame frame, String nameFile) {
        this.sinalRouter = sinalRouter;
        this.component = component;
        this.frame = frame;
        this.nameFile = nameFile;
    }

    @Override
    public void run() {
        //carregaConfig(); // Usado no envio FTP (desativado)

        while (true) {
            SinalRouter sr = jpa.findSinalRouter(sinalRouter.getIdsinalRouter());

            if (sr.getStatus().equals("ATENDIDO")) {
                component.getMediaPlayer().stop();
                component.getMediaPlayer().release();
                component.release();
                frame.dispose();

                /*
                //enviando arquivo por FTP
                FTPClient ftp = new FTPClient();
                InputStream is = null;

                try {
                    ftp.connect(config.getHostFTP(), config.getPortaFTP());

                    if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                        ftp.login(config.getUserFTP(), config.getPassFTP());

                        is = new FileInputStream("c:\\" + nameFile);

                        ftp.setFileTransferMode(FTPClient.BLOCK_TRANSFER_MODE);
                        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

                        ftp.makeDirectory("Seegate 2.0");
                        ftp.changeWorkingDirectory("Seegate 2.0");

                        ftp.makeDirectory("videos");
                        ftp.changeWorkingDirectory("videos");

                        ftp.makeDirectory(sr.getNor());
                        ftp.changeWorkingDirectory(sr.getNor());

                        ftp.makeDirectory(new SimpleDateFormat("ddMMyyyy").format(sr.getDat()));
                        ftp.changeWorkingDirectory(new SimpleDateFormat("ddMMyyyy").format(sr.getDat()));

                        ftp.storeFile(nameFile, is);

                        ftp.disconnect();

                    } else {
                        //erro ao se conectar  
                        ftp.disconnect();
                        System.out.println("Conexão recusada");
                    }
                } catch (SocketException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    try {
                        ftp.disconnect();
                        is.close();

                        File file = new File("c:\\" + nameFile);
                        file.delete();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                //Fim do envio
                * */                

                break;
            }
            
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

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

                config.setPathRouterSnapshot(xml.getChildText("pathRouterSnapshot"));
                config.setPathDestSnapshot(xml.getChildText("pathDestSnapshot"));
                config.setSmtpHostEmail(xml.getChildText("smtpHostEmail"));
                config.setSmtpPortEmail(Integer.parseInt(xml.getChildText("smtpPortEmail")));
                config.setUserEmail(xml.getChildText("userEmail"));
                config.setPassEmail(xml.getChildText("passEmail"));
                config.setHostFTP(xml.getChildText("hostFTP"));
                config.setPortaFTP(Integer.parseInt(xml.getChildText("portaFTP")));
                config.setUserFTP(xml.getChildText("userFTP"));
                config.setPassFTP(xml.getChildText("passFTP"));
                config.setPathDestFTP(xml.getChildText("pathDestFTP"));
            }
        } catch (JDOMException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
