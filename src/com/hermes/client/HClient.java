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
package com.hermes.client;

import com.hermes.client.events.HClientAvatarEvent;
import com.hermes.client.events.HClientEmoteEvent;
import com.hermes.client.events.HClientEvent;
import com.hermes.client.events.HClientJoinEvent;
import com.hermes.client.events.HClientMessageEvent;
import com.hermes.client.events.HClientNoSuchEvent;
import com.hermes.client.events.HClientPartEvent;
import com.hermes.client.events.HClientPersonalMessageEvent;
import com.hermes.client.events.HClientUrlEvent;
import com.hermes.client.events.HIClientEvents;
import com.hermes.common.packages.tcp.HPackage;
import com.hermes.client.packages.tcp.P10;
import com.hermes.client.packages.tcp.P11;
import com.hermes.client.packages.tcp.P2;
import com.hermes.client.packages.tcp.P25;
import com.hermes.client.packages.tcp.P4;
import com.hermes.client.packages.tcp.P74;
import com.hermes.client.packages.tcp.P9;
import com.hermes.common.HUser;
import com.hermes.common.serealization.HPackageSerializer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author joaquin
 */
public class HClient implements Runnable, ActionListener
{

    private HCUser user;
    private HCChannel channel;
    private Thread reader;
    private ByteBuffer readBuffer;
    private SocketChannel socket;
    private static final int BUFFER_SIZE = 3072;
    private List<HIClientEvents> events;
    private Timer updateTimer;

    public static final String CLIENT_VERSION = "нεямεѕ сℓιεит 0.1";

    public HClient(HCUser user) throws IOException
    {
        this.user = user;
        this.reader = new Thread(this);
        this.events = new ArrayList<>();
        this.updateTimer = new Timer(80000, this);
    }

    public void connect(InetAddress ip, int port) throws IOException, Exception
    {
        InetSocketAddress addr = new InetSocketAddress(ip, port);
        connect(addr);
    }

    public void connect(SocketAddress addr) throws Exception
    {
        try
        {
            channel = new HCChannel();
            socket = SocketChannel.open();
            socket.connect(addr);
            socket.configureBlocking(true);
            /*while (!socket.finishConnect())
            {

            }*/
            reader.start();

            P2 pkg = new P2(user.getGuid(), (short) user.getFilecount(), (short) user.getDataport(), user.getNodeIp(), (short) user.getNodePort(), user.getLinetype(), user.getUsername(), CLIENT_VERSION, user.getPrivateIp(), user.getPublicIp(), user.getBrowsable(), user.getUploads(), user.getMaxUploads(), user.getQueued(), user.getAge(), user.getGender(), user.getCountryCode(), user.getRegion());
            send(pkg);
            sendAvatar();
            updateTimer.start();

        }
        catch (IOException ex)
        {
            Logger.getLogger(HClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(HPackage pkg)
    {
        try
        {
            socket.write(HPackageSerializer.getInstance().getBytes(pkg));
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }

    }

    public void sendMessage(String message) throws IOException
    {
        P10 pkg = new P10(message);
        send(pkg);
    }

    public void sendEmote(String emote)
    {
        P11 pkg = new P11(emote);
        send(pkg);
    }

    public void sendCommand(String command)
    {
        P74 pkg = new P74(command);
        send(pkg);
    }

    public void sendPM(String to, String message)
    {
        P25 pkg = new P25(to, message);
        send(pkg);
    }

    public void sendAvatar()
    {
        P9 pkg = new P9(user.getAvatar());
        send(pkg);
    }

    public void addClientEventListener(HIClientEvents e)
    {
        events.add(e);
    }

    public void removeClientEventListener(HIClientEvents e)
    {
        if (events.contains(e))
        {
            events.remove(e);
        }
    }

    private void notifice(HPackage p)
    {
        for (int i = 0; i < events.size(); i++)
        {

        }
    }

    @Override
    public void run()
    {

        readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        readBuffer.order(ByteOrder.LITTLE_ENDIAN);
        int payloadLength = 0;
        short id = -1;
        int readed = 0;
        byte[] bPayload = null;
        ByteBuffer payload;
        HPackage p;
        int payloadLeft = 0;
        byte hi,low;

        try
        {

            while (socket.isConnected())
            {
                readed = socket.read(readBuffer);

                if (readed != -1)
                {

                    readBuffer.flip();

                    while (readBuffer.remaining() >= 3)
                    {

                        if (payloadLeft == 0)
                        {
                           hi=readBuffer.get();
                           low=readBuffer.get();
            
                            payloadLength = ( ((low&0xFF)<<8) | (hi&0xFF) ); 
                            payloadLeft = payloadLength;
                            id = (short) (readBuffer.get() & 0x00ff);

                            bPayload = new byte[payloadLength];
                        }

                        if (readBuffer.remaining() >= payloadLeft)
                        {
                            readBuffer.get(bPayload, payloadLength - payloadLeft, payloadLeft);

                            payload = ByteBuffer.wrap(bPayload);
                            payload.order(ByteOrder.LITTLE_ENDIAN);

                            try
                            {
                                p = deserialize(id, payload);
                                HClientEvent evt;
                                //TODO Replace with Dispacher
                                switch (p.getId())
                                {
                                    case 3:
                                        channel.setName(((com.hermes.server.packages.tcp.P3) p).getRoomname());
                                        user.setUsername(((com.hermes.server.packages.tcp.P3) p).getUsername());
                                        break;
                                    case 9:
                                        com.hermes.server.packages.tcp.P9 pg = ((com.hermes.server.packages.tcp.P9) p);
                                        HUser t = channel.find(pg.getUsername());
                                        if (t != null)
                                        {
                                            t.setAvatar(pg.getAvatar());
                                            evt=new HClientAvatarEvent(pg.getUsername(),pg.getAvatar());
                                            for (int i = 0; i < events.size(); i++)
                                            {
                                                events.get(i).onAvatar((HClientAvatarEvent)evt);
                                            }
                                        }
                                        break;
                                    case 10:

                                        evt = new HClientMessageEvent(((com.hermes.server.packages.tcp.P10) p).getSender(), ((com.hermes.server.packages.tcp.P10) p).getMessage());
                                        for (int i = 0; i < events.size(); i++)
                                        {
                                            events.get(i).onPublicMessage((HClientMessageEvent) evt);
                                        }
                                        break;
                                    case 11:
                                         evt = new HClientEmoteEvent(((com.hermes.server.packages.tcp.P11) p).getUsername(), ((com.hermes.server.packages.tcp.P11) p).getText());
                                        for (int i = 0; i < events.size(); i++)
                                        {
                                            events.get(i).onEmote((HClientEmoteEvent) evt);
                                        }
                                        break;
                                    case 13:
                                        com.hermes.server.packages.tcp.P13 pkg = ((com.hermes.server.packages.tcp.P13) p);
                                        HUser target = channel.find(pkg.getUsername());
                                        if (target != null)
                                        {
                                            target.setPersonalMessage(pkg.getPersonalMessage());
                                             evt=new HClientPersonalMessageEvent(pkg.getUsername(),pkg.getPersonalMessage());
                                            for (int i = 0; i < events.size(); i++)
                                            {
                                                events.get(i).onPersonalMessage((HClientPersonalMessageEvent)evt);
                                            }
                                        }
                                        break;
                                    case 20:
                                        com.hermes.server.packages.tcp.P20 pakg = ((com.hermes.server.packages.tcp.P20) p);
                                        HCUser us = new HCUser(pakg.getUsername(), "", pakg.getSharecount(), pakg.getLinetype(), pakg.getBrowsable(), pakg.getAge(), pakg.getGender(), pakg.getCountry(), pakg.getRegion(), pakg.getPublicIp(), pakg.getPublicPort(), pakg.getPrivateIp(), pakg.getNodeIp(), pakg.getNodePort(), (byte) 0, (byte) 0, (byte) 0);
                                        channel.addUser(us);
                                        evt = new HClientJoinEvent(us);
                                        for (int i = 0; i < events.size(); i++)
                                        {
                                            events.get(i).onJoin((HClientJoinEvent) evt);
                                        }
                                        break;
                                    case 22:
                                        String user = ((com.hermes.server.packages.tcp.P22) p).getUser();
                                        HUser usr = channel.find(user);

                                        if (user != null)
                                        {
                                            channel.removeUser(usr);
                                            evt = new HClientPartEvent(((HCUser) usr));
                                            for (int i = 0; i < events.size(); i++)
                                            {
                                                events.get(i).onPart((HClientPartEvent) evt);
                                            }
                                        }

                                        break;
                                    case 25:
                                        evt = new HClientMessageEvent(((com.hermes.server.packages.tcp.P25) p).getSender(), ((com.hermes.server.packages.tcp.P25) p).getMessage());
                                        for (int i = 0; i < events.size(); i++)
                                        {
                                            events.get(i).onPrivateMessage((HClientMessageEvent) evt);
                                        }
                                        break;
                                    case 30:
                                        com.hermes.server.packages.tcp.P30 pk = ((com.hermes.server.packages.tcp.P30) p);
                                        HCUser u = new HCUser(pk.getUsername(), "",  pk.getSharecount(), pk.getLinetype(), pk.getBrowsable(), pk.getAge(), pk.getGender(), pk.getCountry(), pk.getRegion(), pk.getPublicIp(), pk.getPublicPort(), pk.getPrivateIp(), pk.getNodeIp(), pk.getNodePort(), (byte) 0, (byte) 0, (byte) 0);
                                        channel.addUser(u);
                                        System.out.println(u);
                                        break;
                                    case 32:
                                        channel.setTopic(((com.hermes.server.packages.tcp.P32) p).getTopic());
                                        break;
                                    case 44:
                                        evt = new HClientNoSuchEvent(((com.hermes.server.packages.tcp.P44) p).getMessage());
                                        for (int i = 0; i < events.size(); i++)
                                        {
                                            events.get(i).onNoSuch((HClientNoSuchEvent) evt);
                                        }
                                        break;
                                    case 73:
                                         com.hermes.server.packages.tcp.P73 pkga = ((com.hermes.server.packages.tcp.P73) p);
                                         this.channel.setUlr(pkga.getUrl(),pkga.getCaption());
                                         evt=new HClientUrlEvent(pkga.getUrl(),pkga.getCaption());
                                         for (int i = 0; i < events.size(); i++)
                                        {
                                            events.get(i).onURL((HClientUrlEvent) evt);
                                        }
                                        break;

                                }
                            }
                            catch (Exception ex)
                            {
                                int i = 0;
                            }
                            payloadLeft = 0;

                        }
                        else
                        {

                            payloadLeft = payloadLeft - readBuffer.remaining();
                            readBuffer.get(bPayload, 0, readBuffer.remaining());
                        }
                    }
                    readBuffer.compact();
                }
                else
                {
                    updateTimer.stop();
                    socket.close();
                    //TODO clean UP;
                    payload = null;
                    payloadLeft = 0;
                    payloadLength = 0;
                    id = 0;
                    bPayload = null;
                    System.out.println(new Date() + " Disconnected");

                }
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(HClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //TODO:Change back to private
    public HPackage deserialize(int id, ByteBuffer payload)
    {
        Class<?> clazz = null;
        HPackage p = null;
        try
        {
            clazz = Class.forName("com.hermes.server.packages.tcp.P" + id);

        }
        catch (ClassNotFoundException ex)
        {
            try
            {
                clazz = Class.forName("com.hermes.server.packages.tcp.PDefault");

            }
            catch (ClassNotFoundException ex1)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }

        }

        if (clazz != null)
        {
            Constructor<?> constr;

            try
            {
                constr = clazz.getConstructor(ByteBuffer.class
                );
                p = (HPackage) constr.newInstance(payload);
            }
            catch (NoSuchMethodException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, id + " " + ex);
            }
            catch (SecurityException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            catch (InstantiationException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            catch (IllegalAccessException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            catch (IllegalArgumentException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            catch (InvocationTargetException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

        return p;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        P4 pkg = new P4((short) user.getFilecount(), user.getBrowsable(), user.getNodeIp(), (short) user.getNodePort(), user.getPrivateIp(), user.getAge(), user.getGender(), user.getCountryCode(), user.getRegion());
        send(pkg);
    }
}
