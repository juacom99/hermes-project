/*
 Hermes Project - Chat client/server for Ares Galaxy P2P
 Copyright (C) 2011  Joaquin Martinez (juacom04@gmail.com)

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.hermes.server.packages.tcp;

import com.hermes.common.constants.HAdminLevel;
import com.hermes.common.constants.HBrowsable;
import com.hermes.common.constants.HLineType;
import com.hermes.common.constants.HGender;
import com.hermes.common.constants.HLocation;
import com.hermes.common.packages.tcp.HPackage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/*
 * @author Joaquin Martinez
 * This Class represents the MSG_CHAT_SERVER_CHANNEL_USER_LIST Package
 * Data Format:    
 * [2 Bytes - Shared Count]
 * [4 Bytes - Line Type]
 * [4 Bytes - Private IP]
 * [2 Bytes - Private Port]
 * [4 Bytes - Node IP]
 * [2 Bytes - Node Port]
 * [1 Byte  - null]
 * [x Bytes - Username]
 * [1 Byte  - null]
 * [4 Bytes - Local IP]
 * [1 Byte  - Browse]
 * [1 Byte  - Admin Level]
 * [1 Byte  - Age]
 * [1 Byte  - Gender]
 * [1 Byte  - Country]
 * [x Bytes - Region]
 * [1 Byte  - null]
 */
public class P30 extends HPackage
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

    public P30(int sharecount, HLineType linetype, InetAddress publicIp, int publicPort, InetAddress nodeIp, int nodePort, String username, InetAddress privateIp, HBrowsable browsable, HAdminLevel adminLevel, byte age, HGender gender, HLocation country, String region)
    {
        super(30);
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

    public P30(ByteBuffer bb) throws UnknownHostException
    {
        super(30);
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

        byte[] bUsername = this.username.getBytes(charset);
        byte[] bRegion = this.region.getBytes(charset);

        int length = 30 + bUsername.length + bRegion.length;

        ByteBuffer msg = ByteBuffer.allocate(length);
        msg.order(ByteOrder.LITTLE_ENDIAN);
        msg.putShort((short) sharecount);
        msg.putInt(linetype.getValue());
        msg.put(publicIp.getAddress());
        msg.putShort((short) publicPort);
        msg.put(nodeIp.getAddress());
        msg.putShort((short) nodePort);
        msg.put((byte) 0);
        msg.put(bUsername);
        msg.put((byte) 0);
        msg.put(privateIp.getAddress());
        msg.put(browsable.getValue());
        msg.put(adminLevel.getValue());
        msg.put(age);
        msg.put(gender.getValue());
        msg.put(country.getValue());
        msg.put(bRegion);
        msg.put((byte) 0);

        return msg;
    }

    
    @Override
    public String toString()
    {
        String ret = "************************************************************"
                +"\nUsername: " + this.username 
                + "\nShared:" + this.sharecount 
                + "\nAge: "+this.age
                + "\nGender:" + this.gender
                + "\nCountry: " + this.country
                + "\nRegion: "+this.region
                + "\nLineType: "+this.linetype
                + "\nBrowsable: "+this.browsable
                + "\nAdmin Level: "+this.adminLevel
                + "\nPublic Ip: "+this.publicIp
                + "\nPublic Port: "+this.publicPort
                + "\nPrivate Ip: "+this.privateIp
                + "\nNode Ip: "+this.nodeIp
                + "\nNode Port: "+this.nodePort;
        return ret; 
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
