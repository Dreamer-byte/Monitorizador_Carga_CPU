/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noel
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IntefaceRMI extends Remote{
    
    public void iniMonitor(String Monitor)throws RemoteException;
    public void loadMonitor(String Fichero,String Ip)throws RemoteException;
    public int iniClient()throws RemoteException;
    public void getLoadAvg()throws RemoteException;
    public int ReturnTime() throws RemoteException;
}
