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
package com.hermes.client.packages.tcp;

import com.hermes.common.constants.HBrowsable;
import com.hermes.common.constants.HGender;
import com.hermes.common.constants.HLineType;
import com.hermes.common.constants.HLocation;
import com.hermes.common.packages.tcp.HPackage;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidParameterException;

/**
 * @author Joaquin Martinez
 * This Class represents the MSG_CHAT_CLIENT_LOGIN Package
 * Data Format:
 * [16 Bytes - guid]
 * [2 Bytes  - ShareCount]
 * [1 Byte   - null]
 * [2 Bytes  - DataPort]
 * [4 Bytes  - Node Ip Adress]
 * [2 Bytes  - Node Port]
 * [4 Bytes  - Line Type]
 * [x Bytes  - Username]
 * [1 Byte   - null]
 * [x Bytes  - client version]
 * [1 Byte   - null]
 * [4 Bytes  - Private Ip Adress]
 * [4 Bytes  - Public Ip Adress]
 * [1 Byte   - Browsable]
 * [1 Byte   - Uploads]
 * [1 Byte   - Max Uploads]
 * [1 Byte   - Queued]
 * [1 Byte   - Age]
 * [1 Byte   - Gender]
 * [1 Byte   - Country Code]
 * [x Bytes  - Region]
 * [1 Byte   - null]
*/
public class P2 extends HPackage {

    private String guid;
    private short filecount;
    private short dataport;
    private InetAddress nodeIp;
    private short nodePort;
    private HLineType linetype;
    private String username;
    private String version;
    private InetAddress privateIp;
    private InetAddress publicIp;
    private HBrowsable browsable;
    private byte uploads;
    private byte maxUploads;
    private byte queued;
    private byte age;
    private HGender gender;
    private HLocation countryCode;
    private String region;

    public P2(String guid, short filecount, short dataport, InetAddress nodeIp, short nodePort, HLineType linetype, String username, String version, InetAddress privateIp, InetAddress publicIp, HBrowsable browsable, byte uploads, byte maxUploads, byte queued, byte age, HGender gender, HLocation countryCode, String region) {
        super(2);
        if(guid.length()!=16)
        {
            throw new InvalidParameterException("The GUID must have 16 characters");
        }
        this.guid = guid;
        this.filecount = filecount;
        this.dataport = dataport;
        this.nodeIp = nodeIp;
        this.nodePort = nodePort;
        this.linetype = linetype;
        this.username = username;
        this.version = version;
        this.privateIp = privateIp;
        this.publicIp = publicIp;
        this.browsable = browsable;
        this.uploads = uploads;
        this.maxUploads = maxUploads;
        this.queued = queued;
        this.age = age;
        this.gender = gender;
        this.countryCode = countryCode;
        this.region = region;
    }
    
    public P2(ByteBuffer bb)
    {
        super(2);
        //TODO: Read pacakge from ByteBuffer
    }
    @Override
    public ByteBuffer getPayload()
    {
        byte[] bUsername=this.username.getBytes(charset);
        byte[] bVersion=this.version.getBytes(charset);
        byte[] bRegion= this.region.getBytes(charset);

        int length = 49 + bUsername.length+bVersion.length;

        if (!this.region.isEmpty())
        {
            length += bRegion.length+1;
        }

        ByteBuffer msg = ByteBuffer.allocate(length);
        msg.order(ByteOrder.LITTLE_ENDIAN);

        msg.put(this.guid.getBytes(charset));
        msg.putShort(this.filecount);
        msg.put((byte) 0);
        msg.putShort(this.dataport);
        msg.put(this.nodeIp.getAddress());
        msg.putShort(this.nodePort);
        msg.putInt(this.linetype.getValue());
        msg.put(bUsername);
        msg.put((byte) 0);
        msg.put(bVersion);
        msg.put((byte) 0);
        msg.put(this.privateIp.getAddress());
        msg.put(this.publicIp.getAddress());
        msg.put(this.browsable.getValue());
        msg.put(this.uploads);
        msg.put(this.maxUploads);
        msg.put(this.queued);
        msg.put(this.age);
        msg.put(this.gender.getValue());
        msg.put(this.countryCode.getValue());
        
        if (!this.region.isEmpty())
        {
            msg.put(bRegion);
            msg.put((byte) 0);
        }
        //features
        msg.put((byte)0);

        return msg;
    }
}
