
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noel
 */
import java.rmi.registry.Registry;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import javax.swing.JOptionPane;
public class MonitoMain {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        String ContenidoFichero = "Vacio";
        String Ip =  "";
        String IpServer = JOptionPane.showInputDialog("Ingresa La Ip del Server");
        
        //Haciendo Stup para el monitor 
        try
        {
            MoninitorImple ObjMonitor = new MoninitorImple();
            Registry MyReg = java.rmi.registry.LocateRegistry.createRegistry(1099);
            MyReg.rebind("ObjetoMonitor",ObjMonitor);
        }
        catch(Exception e)
        {
            System.out.println("Ocurrio un error al leer el fichero: "+ e.getMessage());
        }
        
        //Llamando al objeto remoto e implemantando la funcionalidad
        
        try
        {
            
            
            InetAddress IpAdd = InetAddress.getLocalHost();
            Ip = IpAdd.getHostAddress();
            
            Registry RegServer = LocateRegistry.getRegistry(IpServer, 1099);
            ImplIntefaceRMI ObjCoordinador = (ImplIntefaceRMI) Naming.lookup("//"+IpServer+"/miCoordinador");
            JOptionPane.showMessageDialog(null, "Registrando al coordinador en el server");
            ObjCoordinador.iniMonitor(Ip);
            
            while(true)
            {
                
                BufferedReader Leer = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/loadavg")));
                ContenidoFichero = Leer.readLine();
                if(ContenidoFichero == null)
                {
                    ContenidoFichero = "No se pudo leer la carga";
                }
                ObjCoordinador.loadMonitor(ContenidoFichero, Ip);                //Si queremos hacer un rango de tiempo expresado en segundos debemos de
                //Multiplica el valor Dado del server por Mill para convertirlos a milisegundo
                //Dado que sleep trabaja con milisegundos
                Thread.sleep(ObjCoordinador.Time*1000);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    
}
