package br.com.fox.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dvr
 */
public class MAC {

    public static void main(String[] args) {

        try {
            //Capturando Mac address 
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            StringBuilder sbMac = new StringBuilder();

            for (int i = 0; i < mac.length; i++) {
                sbMac.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }

            //Criptografando arquivo
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sbMac.toString().getBytes());
            byte[] bytes = md.digest();

            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
                int parteBaixa = bytes[i] & 0xf;
                if (parteAlta == 0) {
                    s.append('0');
                }
                s.append(Integer.toHexString(parteAlta | parteBaixa));
            }

            //Criando arquivo
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File("lic.bin")));
            dos.writeUTF(s.toString());
            dos.close();

            System.out.println(s.toString());

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MAC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MAC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(MAC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MAC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
