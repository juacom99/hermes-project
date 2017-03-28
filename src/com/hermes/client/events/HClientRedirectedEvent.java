/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.events;

import java.net.InetAddress;

/**
 *
 * @author Joaquin Martinez <juacom04@gmail.com>
 */
public class HClientRedirectedEvent
{
    private InetAddress ip;
    private int port;
    private String readson;

    public HClientRedirectedEvent(InetAddress ip, int port, String readson)
    {
        this.ip = ip;
        this.port = port;
        this.readson = readson;
    }

    public InetAddress getIp()
    {
        return ip;
    }

    public int getPort()
    {
        return port;
    }

    public String getReadson()
    {
        return readson;
    }
}
