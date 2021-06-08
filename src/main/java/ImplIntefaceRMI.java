
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noel
 */
public class ImplIntefaceRMI extends UnicastRemoteObject implements IntefaceRMI
{
    private ArrayList<String> ListMonitor;
    private  ArrayList<String> ListCarga;
    public int Time;
    public int CountMonitors;
    public ImplIntefaceRMI()throws RemoteException
    {
        super();
        ListMonitor = new ArrayList<String>();
        ListCarga = new ArrayList<String>();
        Time = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el periodo de tiempo con el que se estara revisando"));
       
    }
    @Override
     public void iniMonitor(String Monitor)
     {
         try
         {
             if(!ListMonitor.contains(Monitor))
             {
                 ListMonitor.add(Monitor);
                 CountMonitors++;
             }
         }
         catch(NumberFormatException e)
         {
             JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
         }
         
         
     }
     @Override
      public void loadMonitor(String Fichero,String Ip)
      {
          int IndexAdd;
          if(ListMonitor.size()>0)
          {
              IndexAdd = ListMonitor.indexOf(Ip);
              if(IndexAdd >=0)
              {
                  ListCarga.add(IndexAdd, Fichero);
              }
          }
          else
          {
              JOptionPane.showMessageDialog(null,"Se agreagara el servidor a la lista");
              ListMonitor.add(Ip);
              ListCarga.add(Fichero);
          }
      }
      @Override
       public int iniClient()
       {
           int IndexFun =-1;
           String Ip = "";
           if(ListMonitor.size() >0 ){ return 0;}
           try
           {
               for (String Ip2 : ListMonitor) {
                   Ip = Ip2;
                    Registry miRegistro = LocateRegistry.getRegistry(Ip2, 1099);
                    MoninitorImple ObjMonitor = (MoninitorImple) Naming.lookup("//"+Ip2+"/ObjetoMonitor");
                    ObjMonitor.PingMonitor();
                    
               }
              
              
           }
           catch(RemoteException E )
           {
               IndexFun = ListMonitor.indexOf(Ip);
               ListMonitor.remove(Ip);
               ListCarga.remove(IndexFun);
               CountMonitors--;
               
               
          }
          catch(Exception e) 
          {
              JOptionPane.showMessageDialog(null, e.getMessage());
          }
           
           return CountMonitors;
       }
       
       @Override
        public void getLoadAvg()
        {
            for (String Ip : ListMonitor) 
            {
                JOptionPane.showMessageDialog(null,"\t\tServidor:\n Ip: "+Ip +"\n Carga: "+ ListCarga.get(ListMonitor.indexOf(Ip)));
                
                
            }
        }
        @Override
         public int ReturnTime()
         {
             return  Time;
         }
         
      
      
}
