/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.server.packages.tcp;

import com.hermes.common.constants.HAdminLevel;
import com.hermes.common.constants.HBrowsable;
import com.hermes.common.constants.HGender;
import com.hermes.common.constants.HLocation;
import com.hermes.common.packages.tcp.HPackage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/*
X Bytes - Username
1 Bytes - NULL
2 Bytes - File Count
1 Bytes - Browse
4 Bytes - Node IP
2 Bytes - Node Port
4 Bytes - Public IP
1 Bytes - Admin Level
1 Bytes - Age
1 Bytes - Sex
1 Bytes - Country
X Bytes - Region
1 Bytes - NULL

*/
public class P5 extends HPackage
{
    private String username;
    private int filescount;
    private HBrowsable browsable;
    private InetAddress nodeIp;
    private int nodePort;
    private InetAddress publicIp;
    private HAdminLevel level;
    private byte age;
    private HGender gender;
    private HLocation country;
    private String region;
    
    public P5()
    {
        super(5);
    }

    public P5(ByteBuffer bb) throws UnknownHostException
    {
        super(5);
        this.username=readNullTerminatedString(bb);
        this.filescount=bb.getShort() & 0xff;
        this.browsable=HBrowsable.get(bb.get());
        byte[] bIp=new  byte[4];        
        bb.get(bIp);        
        this.nodeIp=InetAddress.getByAddress(bIp);        
        this.nodePort=bb.getShort();        
        bb.get(bIp);        
        this.publicIp=InetAddress.getByAddress(bIp);        
        level=HAdminLevel.get(bb.get());        
        age=bb.get();        
        this.gender=HGender.get(bb.get());
        this.country=HLocation.get(bb.get());
        if(bb.remaining()>1)
        {
            this.region=readNullTerminatedString(bb);
        }
        else
        {
            this.region="";
        }
    }

    @Override
    public ByteBuffer getPayload()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString()
    {
        return "";
    }

    public String getUsername()
    {
        return username;
    }

    public int getFilesCount()
    {
        return filescount;
    }

    public HBrowsable getBrowsable()
    {
        return browsable;
    }

    public InetAddress getNodeIp()
    {
        return nodeIp;
    }

    public int getNodePort()
    {
        return nodePort;
    }

    public InetAddress getPublicIp()
    {
        return publicIp;
    }

    public HAdminLevel getLevel()
    {
        return level;
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
