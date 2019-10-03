/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatapp.networking;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nisarg-PC
 */
public class MessageListener extends Thread{
    ServerSocket server;
    int port;
    WritableGUI gui;
    public MessageListener(WritableGUI gui,int port){
        try {
            this.gui=gui;
            this.port=port;
            server=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run(){
        try {
            Socket clientsocket;
            while((clientsocket=server.accept())!=null){
                InputStream is=clientsocket.getInputStream(); 
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                if(line!=null){
                    gui.write(line);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
