/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.packages.tcp;

import com.hermes.common.constants.HBrowsable;
import com.hermes.common.constants.HGender;
import com.hermes.common.constants.HLocation;
import com.hermes.common.packages.tcp.HPackage;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author jomartinez
 */
public class P4 extends HPackage
{
    private short filecount;
    private HBrowsable browsable;
    private InetAddress nodeIP;
    private short nodePort;
    private InetAddress privateIp;
    private byte age;
    private HGender gender;
    private HLocation country;
    private String region;

    public P4(short filecount, HBrowsable browsable, InetAddress nodeIP, short nodePort, InetAddress privateIp, byte age, HGender gender, HLocation country, String region)
    {
        super(4);
        this.filecount = filecount;
        this.browsable = browsable;
        this.nodeIP = nodeIP;
        this.nodePort = nodePort;
        this.privateIp = privateIp;
        this.age = age;
        this.gender = gender;
        this.country = country;
        this.region = region;
    }
    
    
    
    @Override
    public ByteBuffer getPayload()
    {
         ByteBuffer msg;
          byte[] bRegion=null;
        if(region!=null && !region.equals(""))
        {
            bRegion=region.getBytes(charset);
            msg = ByteBuffer.allocate(bRegion.length+17);
        }
        else
        {
            msg = ByteBuffer.allocate(16);
        }
        
        msg.order(ByteOrder.LITTLE_ENDIAN);
        
        msg.putShort(filecount);
        msg.put(browsable.getValue());
        msg.put(nodeIP.getAddress());
        msg.putShort(nodePort);
        msg.put(privateIp.getAddress());
        msg.put(age);
        msg.put(gender.getValue());
        msg.put(country.getValue());
        if(bRegion!=null)
        {
            msg.put(bRegion);
            msg.put((byte)0);
        }
        return msg;
    }
    
    
}
