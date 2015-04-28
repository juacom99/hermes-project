/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.server.packages.tcp;

import com.hermes.common.packages.tcp.HPackage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author joaquin
 */
public class P6 extends HPackage
{

    private InetAddress publicIp;
    private int port;
    private InetAddress privateIp;
    private String reason;

    public P6(InetAddress publicIp, int port,InetAddress privateIp,String reason)
    {
        super(6);
        this.publicIp = publicIp;
        this.port = port;
        this.reason=reason;
    }

    public P6(ByteBuffer bb) throws UnknownHostException
    {
        super(6);

        bb.order(ByteOrder.LITTLE_ENDIAN);
        byte[] bIp = new byte[4];

        bb.get(bIp);

        this.publicIp = InetAddress.getByAddress(bIp);

        byte hi = bb.get();
        byte low = bb.get();

        this.port = (((low & 0xFF) << 8) | (hi & 0xFF));

       
        bb.get(bIp);
        
        this.privateIp = InetAddress.getByAddress(bIp);
        
        this.reason=readNullTerminatedString(bb);
       
    }

    @Override
    public ByteBuffer getPayload()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public InetAddress getPrivateIp()
    {
        return privateIp;
    }

    public InetAddress getPublicIp()
    {
        return publicIp;
    }

    public int getPort()
    {
        return port;
    }

    public String getReason()
    {
        return reason;
    }
}
