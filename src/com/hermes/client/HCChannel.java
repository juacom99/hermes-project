/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client;

import com.hermes.common.HChannel;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;
import java.util.Collection;

/**
 *
 * @author joaquin
 */
public class HCChannel extends HChannel
{

    public HCChannel()
    {
        super(null, null, 0, null, null);
    }

    
    public HCChannel(String name, InetAddress publicIP, int port, InetAddress privateIP, String topic)
    {
        super(name, publicIP, port, privateIP, topic);
    }

    


    

    

  
    
    
}
