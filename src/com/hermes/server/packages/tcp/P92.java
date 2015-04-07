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

import com.hermes.common.constants.HLanguage;
import com.hermes.common.packages.tcp.HPackage;
import com.hermes.common.constants.HServerFeatures;
import com.hermes.common.constants.HShareTypes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/*
 * @author Joaquin Martinez
 * This Class represents the MSG_CHAT_SERVER_MYFEATURES Package
 * Data Format:
 * [x Bytes - Server Version]
 * [1 Byte  - null]
 * [1 Byte  - features]
 * [1 Byte  - Sharable Types
 * [1 Byte  - Server language]
 * [4 Bytes - Random integer for SHA1 admin login]
 * [1 Byte  - suppor avatar]
 */
public class P92 extends HPackage
{

    private String serverVersion;
    private byte features;
    private byte sherableType;
    private HLanguage language;
    private int cookie;
    private boolean supportAvatar;

    public P92(String serverVersion, byte features, byte sherableType, HLanguage language, int cookie, boolean supportAvatar)
    {
        super(92);
        this.serverVersion = serverVersion;
        this.features = features;
        this.sherableType = sherableType;
        this.language = language;
        this.cookie = cookie;
        this.supportAvatar = supportAvatar;
    }

    public P92(ByteBuffer bb)
    {
        super(92);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        this.serverVersion = readNullTerminatedString(bb);
        this.features=bb.get();
        this.sherableType=bb.get();
        this.language=HLanguage.get(bb.get());
        this.cookie=bb.getInt()  & 0xffff;
        this.supportAvatar=(bb.get()==1);
    }
    

    @Override
    public ByteBuffer getPayload()
    {

        byte[] bVersion = this.serverVersion.getBytes(charset);
        int length = 8 + bVersion.length;

        ByteBuffer msg = ByteBuffer.allocate(length);
        msg.order(ByteOrder.LITTLE_ENDIAN);

        msg.put(bVersion);
        msg.put((byte) 0);
        msg.put(sherableType);
        msg.put(language.getValue());
        msg.putInt(cookie);
        if (supportAvatar)
        {
            msg.put((byte) 1);
        }
        else
        {
            msg.put((byte) 0);
        }

        return msg;
    }

    public String getServerVersion()
    {
        return serverVersion;
    }

    public byte getSherableType()
    {
        return sherableType;
    }

    public HLanguage getLanguage()
    {
        return language;
    }

    public int getCookie()
    {
        return cookie;
    }

    public boolean isSupportAvatar()
    {
        return supportAvatar;
    }

    
    public boolean supportsCompression()
    {
        return (HServerFeatures.SUPPORTS_COMPRESSION.getValue() | this.features )==this.features;
    }
    
    public boolean supportsHTML()
    {
        return (HServerFeatures.SUPPORTS_HTML.getValue() | this.features )==this.features;
    }
    
    public boolean supportsOpusVc()
    {
        return (HServerFeatures.SUPPORTS_OPUS_VC.getValue() | this.features )==this.features;
    }
    
    public boolean supportsPrivateScribbles()
    {
        return (HServerFeatures.SUPPORTS_PM_SCRIBBLES.getValue() | this.features )==this.features;
    }
    
    public boolean supportsRoomScribbles()
    {
        return (HServerFeatures.SUPPORTS_ROOM_SCRIBBLES.getValue() | this.features )==this.features;
    }
    
    public boolean supportsSharing()
    {
        return (HServerFeatures.SUPPORTS_SHARING.getValue() | this.features )==this.features;
    }
    
    public boolean supportsVC()
    {
        return (HServerFeatures.SUPPORTS_VC.getValue() | this.features )==this.features;
    }
    
    public boolean supportsPrivateMessages()
    {
        return (HServerFeatures.SUPPORT_PVT.getValue() | this.features )==this.features;
    }
    
    
    public boolean canShareOthers()
    {
        return (HShareTypes.OTHER.getValue() | this.sherableType)==this.sherableType;
    }
    
    public boolean canShareSoftwares()
    {
        return (HShareTypes.SOFTWARE.getValue() | this.sherableType)==this.sherableType;
    }
    
     public boolean canShareDocuments()
    {
        return (HShareTypes.DOCUMENT.getValue() | this.sherableType)==this.sherableType;
    }
     
    public boolean canShareImages()
    {
        return (HShareTypes.IMAGE.getValue() | this.sherableType)==this.sherableType;
    }
    
     public boolean canShareVideos()
    {
        return (HShareTypes.VIDEO.getValue() | this.sherableType)==this.sherableType;
    }
     
      public boolean canShareMusic()
    {
        return (HShareTypes.MP3.getValue() | this.sherableType)==this.sherableType;
    }
      
    @Override
    public String toString()
    {
        return "FEATURES: Version="+this.serverVersion+" Language: "+this.language+" Compress="+this.features+" Avatar="+this.supportAvatar+" Sherable Types="+this.sherableType+" Cookie= "+this.cookie;
    }

    
    
}
