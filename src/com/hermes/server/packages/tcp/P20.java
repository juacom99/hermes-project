/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.server.packages.tcp;

import com.hermes.common.constants.HAdminLevel;
import com.hermes.common.constants.HBrowsable;
import com.hermes.common.constants.HGender;
import com.hermes.common.constants.HLineType;
import com.hermes.common.constants.HLocation;
import com.hermes.common.packages.tcp.HPackage;
import static com.hermes.common.packages.tcp.HPackage.charset;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author joaquin
 */
public class P20 extends HPackage
{
    private int sharecount;
    private HLineType linetype;
    private InetAddress publicIp;
    private int publicPort;
    private InetAddress nodeIp;
    private int nodePort;
    private String username;
    private InetAddress privateIp;
    private HBrowsable browsable;
    private HAdminLevel adminLevel;
    private byte age;
    private HGender gender;
    private HLocation country;
    private String region;

    public P20(int sharecount, HLineType linetype, InetAddress publicIp, int publicPort, InetAddress nodeIp, int nodePort, String username, InetAddress privateIp, HBrowsable browsable, HAdminLevel adminLevel, byte age, HGender gender, HLocation country, String region)
    {
        super(20);
        this.sharecount = sharecount;
        this.linetype = linetype;
        this.publicIp = publicIp;
        this.publicPort = publicPort;
        this.nodeIp = nodeIp;
        this.nodePort = nodePort;
        this.username = username;
        this.privateIp = privateIp;
        this.browsable = browsable;
        this.adminLevel = adminLevel;
        this.age = age;
        this.gender = gender;
        this.country = country;
        this.region = region;
    }

    public P20(ByteBuffer bb) throws UnknownHostException
    {
        super(20);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        this.sharecount = bb.getShort() & 0x00ff;
        this.linetype = HLineType.getValue(bb.getInt());
        byte[] bIP = new byte[4];
        bb.get(bIP);
        this.publicIp = InetAddress.getByAddress(bIP);
        this.publicPort = bb.getShort() & 0x00ff;
        bb.get(bIP);
        this.nodeIp = InetAddress.getByAddress(bIP) ;
        this.nodePort = bb.getShort() & 0x00ff;
        bb.get();
        this.username = readNullTerminatedString(bb);
        bb.get(bIP);
        this.privateIp = InetAddress.getByAddress(bIP);
        this.browsable = HBrowsable.get(bb.get());
        this.adminLevel = HAdminLevel.get(bb.get());
        this.age = bb.get();
        this.gender = HGender.get(bb.get());
        this.country = HLocation.get(bb.get());
        if (bb.hasRemaining())
        {
            byte[] bRegion = new byte[bb.remaining()- 1];
            bb.get(bRegion);
            this.region = new String(bRegion, charset);
            bb.get();
        }
    }

    @Override
    public ByteBuffer getPayload()
    {
       return null;
    }

    @Override
    public String toString()
    {
        return this.username+" has join the channel"; 
    }

    public int getSharecount()
    {
        return sharecount;
    }

    public HLineType getLinetype()
    {
        return linetype;
    }

    public InetAddress getPublicIp()
    {
        return publicIp;
    }

    public int getPublicPort()
    {
        return publicPort;
    }

    public InetAddress getNodeIp()
    {
        return nodeIp;
    }

    public int getNodePort()
    {
        return nodePort;
    }

    public String getUsername()
    {
        return username;
    }

    public InetAddress getPrivateIp()
    {
        return privateIp;
    }

    public HBrowsable getBrowsable()
    {
        return browsable;
    }

    public HAdminLevel getAdminLevel()
    {
        return adminLevel;
    }

    public byte getAge()
    {
        return age;
    }

    public HGender getGender()
    {
        return gender;
    }

    public HLocation getCountry()
    {
        return country;
    }

    public String getRegion()
    {
        return region;
    }
    
    
    
    
}
