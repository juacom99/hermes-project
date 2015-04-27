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
package com.hermes.common;
 
import com.hermes.common.constants.HAdminLevel;
import com.hermes.common.constants.HBrowsable;
import com.hermes.common.constants.HGender;
import com.hermes.common.constants.HLineType;
import com.hermes.common.constants.HLocation;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.ImageIcon;

/**
 *
 * @author joaquin
 */
public abstract class HUser
{
    private String username;
    private String guid;
    private int filecount;
    private HLineType linetype;
    private HBrowsable browsable;
    
     private byte age;
    private HGender gender;
    private HLocation country;
    private String region;
    private InetAddress publicIp;   
    private int dataport;
    private InetAddress privateIp;
    private InetAddress nodeIp;
    private int nodePort;
    private byte uploads;
    private byte maxUploads;
    private byte queued;
    private HAdminLevel level;
    
    private String personalMessage;
    private ImageIcon avatar;
    
    private ArrayList<HUser> ignored;

    public HUser(String username, String guid, int filecount, HLineType linetype, HBrowsable browsable, byte age, HGender gender, HLocation countryCode, String region, InetAddress publicIp, int dataport, InetAddress privateIp, InetAddress nodeIp, int nodePort, byte uploads, byte maxUploads, byte queued)
    {
        this.username = username;
        this.guid = guid;
        this.filecount = filecount;
        this.linetype = linetype;
        this.browsable = browsable;
        this.age = age;
        this.gender = gender;
        this.country = countryCode;
        this.region = region;
        this.publicIp = publicIp;
        this.dataport = dataport;
        this.privateIp = privateIp;
        this.nodeIp = nodeIp;
        this.nodePort = nodePort;
        this.uploads = uploads;
        this.maxUploads = maxUploads;
        this.queued = queued;
        this.ignored=new ArrayList<HUser>();
    }
    
    public HUser(String username,short filecount, HLineType linetype, HBrowsable browsable, byte age, HGender gender, HLocation countryCode, String region, InetAddress publicIp, short dataport, InetAddress privateIp, InetAddress nodeIp, short nodePort, byte uploads, byte maxUploads, byte queued)
    {
        this.username = username;
        this.guid = UUID.randomUUID().toString().replaceAll("-","").substring(0,16);
        this.filecount = filecount;
        this.linetype = linetype;
        this.browsable = browsable;
        this.age = age;
        this.gender = gender;
        this.country = countryCode;
        this.region = region;
        this.publicIp = publicIp;
        this.dataport = dataport;
        this.privateIp = privateIp;
        this.nodeIp = nodeIp;
        this.nodePort = nodePort;
        this.uploads = uploads;
        this.maxUploads = maxUploads;
        this.queued = queued;
    }
    

    public String getUsername()
    {
        return username;
    }

    public String getGuid()
    {
        return guid;
    }
    
    public int getFilecount()
    {
        return filecount;
    }

    public HLineType getLinetype()
    {
        return linetype;
    }

    public HBrowsable getBrowsable()
    {
        return browsable;
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

    public InetAddress getPublicIp()
    {
        return publicIp;
    }

    public int getDataport()
    {
        return dataport;
    }

    public InetAddress getPrivateIp()
    {
        return privateIp;
    }

    public InetAddress getNodeIp()
    {
        return nodeIp;
    }

    public int getNodePort()
    {
        return nodePort;
    }

    public byte getUploads()
    {
        return uploads;
    }

    public byte getMaxUploads()
    {
        return maxUploads;
    }

    public byte getQueued()
    {
        return queued;
    }

    public HAdminLevel getLevel()
    {
        return level;
    }
    
    

    public void setAvatar(ImageIcon avatar)
    {
       this.avatar = avatar;
    }

    public ImageIcon getAvatar()
    {
        return avatar;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPersonalMessage(String personalMessage)
    {
        this.personalMessage = personalMessage;
    }

    public String getPersonalMessage()
    {
        return personalMessage;
    }

    public void setFilecount(int filecount)
    {
        this.filecount = filecount;
    }

    public void setBrowsable(HBrowsable browsable)
    {
        this.browsable = browsable;
    }

    public void setAge(byte age)
    {
        this.age = age;
    }

    public void setGender(HGender gender)
    {
        this.gender = gender;
    }    
    
    public void setCountry(HLocation countryCode)
    {
        this.country = countryCode;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public void setPublicIp(InetAddress publicIp)
    {
        this.publicIp = publicIp;
    }

    public void setDataport(int dataport)
    {
        this.dataport = dataport;
    }

    public void setNodeIp(InetAddress nodeIp)
    {
        this.nodeIp = nodeIp;
    }

    public void setNodePort(int nodePort)
    {
        this.nodePort = nodePort;
    }

    public void setLevel(HAdminLevel level)
    {
        this.level = level;
    }

    @Override
    public String toString()
    {
        return this.username;
    }
    
    public boolean ignore(HUser usr)
    {
        boolean ret;
        if(!this.ignored.contains(usr))
        {
            this.ignored.add(usr);
            ret=true;
        }
        else
        {
            this.ignored.remove(usr);
            ret=false;
        }
        
        return ret;
    }
    
     public boolean remIgnore(HUser usr)
    {
        boolean ret=false;
        if(this.ignored.contains(usr))
        {
            this.ignored.remove(usr);
            ret=true;
        }
        
        return ret;
    }
     
   
}
