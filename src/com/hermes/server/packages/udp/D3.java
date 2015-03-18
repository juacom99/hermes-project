/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.server.packages.udp;

import com.hermes.common.HChannel;
import com.hermes.common.constants.HLanguage;
import com.hermes.common.packages.tcp.HPackage;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 *
 * @author joaquin
 */
public class D3 extends HPackage
{

    private String name;
    private int port;
    private String topic;
    private int userCount;
    private String serverVersion;
    private HLanguage language;
    private ArrayList<HChannel> knownChannels;

    public D3(ByteBuffer bb) throws UnsupportedEncodingException, UnknownHostException
    {
        super(3);
        this.knownChannels=new ArrayList<HChannel>();
        this.port = bb.getShort() & 0xFFFF;
        this.userCount = bb.getShort() & 0xFFFF;//user count
        int roomNameLength = bb.getShort() & 0xFFFF;//name length
        byte[] bName = new byte[roomNameLength];
        bb.get(bName);
        this.name = new String(bName, charset);//room name
        int roomTopicLength = bb.getShort() & 0xFFFF;
        byte[] bTopic = new byte[roomTopicLength];
        bb.get(bTopic);
        this.topic = new String(bTopic, charset);//room topic
        this.language = HLanguage.get(bb.get());//language
        int roomServerVersionLength = bb.getShort() & 0xFFFF;
        byte[] bServerVersion = new byte[roomServerVersionLength];
        bb.get(bServerVersion);
        this.serverVersion = new String(bServerVersion, charset);
        int nodeNumber = Integer.MAX_VALUE;
        if (!serverVersion.contains("Arca Eclipse"))
        {
            nodeNumber = bb.get();
        } else
        {
            nodeNumber = 7;
        }
        byte[] bIp = new byte[4];
        int nodePort;
        InetAddress nodeIp;
        int i = 0;

        while (bb.hasRemaining() && nodeNumber > i)
        {
            bb.get(bIp);
            nodeIp = InetAddress.getByAddress(bIp);
            nodePort = bb.getShort() & 0xFFFF;
            knownChannels.add(new HChannel(nodeIp, nodePort));
            i++;
        }
    }

    @Override
    public ByteBuffer getPayload()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName()
    {
        return name;
    }

    public int getPort()
    {
        return port;
    }

    public String getTopic()
    {
        return topic;
    }

    public int getUserCount()
    {
        return userCount;
    }

    public String getServerVersion()
    {
        return serverVersion;
    }

    public HLanguage getLanguage()
    {
        return language;
    }

    public ArrayList<HChannel> getKnownChannels()
    {
        return knownChannels;
    }

    
}
