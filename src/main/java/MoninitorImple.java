/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noel
 */
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.net.InetAddress;

public class MoninitorImple extends UnicastRemoteObject implements InterfaceMonitor
{
    public MoninitorImple()throws RemoteException{super();}
    @Override
     public void PingMonitor()
     {
         System.out.println("Servidor funcionando");
     }
}
