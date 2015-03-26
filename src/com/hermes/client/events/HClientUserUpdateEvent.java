/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.events;

import com.hermes.common.constants.HAdminLevel;
import com.hermes.common.constants.HBrowsable;
import com.hermes.common.constants.HGender;
import com.hermes.common.constants.HLocation;
import java.net.InetAddress;

/**
 *
 * @author jomartinez
 */
public class HClientUserUpdateEvent extends HClientEvent
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

    public HClientUserUpdateEvent(String username, int filescount, HBrowsable browsable, InetAddress nodeIp, int nodePort, InetAddress publicIp, HAdminLevel level, byte age, HGender gender, HLocation country, String region)
    {
        this.username = username;
        this.filescount = filescount;
        this.browsable = browsable;
        this.nodeIp = nodeIp;
        this.nodePort = nodePort;
        this.publicIp = publicIp;
        this.level = level;
        this.age = age;
        this.gender = gender;
        this.country = country;
        this.region = region;
    }

    public String getUsername()
    {
        return username;
    }

    public int getFilescount()
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
