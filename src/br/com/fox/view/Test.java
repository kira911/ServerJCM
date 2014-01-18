package br.com.fox.view;

/**
 *
 * @author Dvr
 */
public class Test {
    public static void main(String[] args) {
        String msg = "5011s181234E12101001";
        
        System.out.println("Receiver: "+msg.substring(1, 3));
        System.out.println("Line number: "+msg.substring(3, 4));    
        System.out.println("Contact-ID format identifier: "+msg.substring(5, 7));
        System.out.println("Four digit account codes: "+msg.substring(7, 11));
        System.out.println("Class code and event code: "+msg.substring(11, 15));
        System.out.println("Group number: "+msg.substring(15, 17));
        System.out.println("Zone codes or user ID: "+msg.substring(17, 20));
    }
}
