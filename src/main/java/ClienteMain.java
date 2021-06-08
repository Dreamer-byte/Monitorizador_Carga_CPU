
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noel
 */
public class ClienteMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String IpServer = JOptionPane.showInputDialog("Ingresa La Ip del Server");
        JOptionPane.showMessageDialog(null,"*****Iniciando el cliente*****");
        ArrayList<String>ListaCarga;
        int Opc;
        try
        {
            Registry RegServer = LocateRegistry.getRegistry(IpServer, 1099);
            IntefaceRMI ObjCoordinador = (IntefaceRMI) Naming.lookup("//"+IpServer+"/miCoordinador");
            while(true)
            {
                try
                {
                    Opc = Integer.parseInt(JOptionPane.showInputDialog("***Menu***\n 1:Cantidad de coordinadores activos.\n 2:Consultar carga.\n 3: Salir")) ;
                    switch(Opc)
                    {
                        case 1:
                            int CountActive = ObjCoordinador.iniClient();
                            JOptionPane.showMessageDialog(null,"Los monitores activos son: "+CountActive);
                            break;
                        case 2:
                            ListaCarga = new ArrayList<String>();
                            ListaCarga = ObjCoordinador.getLoadAvg();
                            for (String MonitorCarga : ListaCarga) {
                                JOptionPane.showMessageDialog(null, MonitorCarga);
                            }
                            break;
                        case 3:
                            System.out.println("Cerrando el cliente");
                            break;
                    }
                    
                    if(Opc == 3 )
                    {
                        break;
                    }
                }
                catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null,"Eror al procesar peticion: "+ e.getMessage());
                }
            }
        }
        catch(Exception e )
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    
        
    }
    
}
