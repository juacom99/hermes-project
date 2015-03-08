/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.common;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author joaquin
 */
public class HChannel
{
    private String name;
    private InetAddress publicIP;
    private int port;
    private InetAddress privateIP;
    private String topic;
    private List<HUser> users;

 
    
    
    public HChannel(String name, InetAddress publicIP, int port, InetAddress privateIP, String topic)
    {
        this.name = name;
        this.publicIP = publicIP;
        this.port = port;
        this.privateIP = privateIP;
        this.topic = topic;
        this.users = new ArrayList<HUser>();
    }

    public String getName()
    {
        return name;
    }

    public String getTopic()
    {
        return topic;
    }

    public InetAddress getPublicIP()
    {
        return publicIP;
    }

    public InetAddress getPrivateIP()
    {
        return privateIP;
    }

    public int getPort()
    {
        return port;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }
    
    public void addUser(HUser usr)
    { 
            users.add(usr);
    }
    
     public HUser find(String username)
    {
        Iterator<HUser> i=users.iterator();
        HUser ret=null;
        HUser temp;
        boolean found=false;
        while(i.hasNext() && !found)
        {
            temp=i.next();
            if(temp.getUsername().equals(username))
            {
                ret=temp;
                found=true;
            }
        }
        return ret;
    }
     
     public void removeUser(HUser user)
     {
         if(users.contains(user))
         {
             users.remove(user);
         }
     }
}
