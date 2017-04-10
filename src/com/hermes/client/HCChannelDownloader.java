/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client;


import com.hermes.client.events.HChannelListEvents;
import com.hermes.client.events.HClientEvent;
import com.hermes.common.HChannel;
import com.hermes.common.IPCacheManager;
import com.hermes.common.packages.tcp.HPackage;
import com.hermes.server.packages.udp.D3;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaquin
 */
public class HCChannelDownloader implements Runnable
{

    private Queue<HChannel> toProcess;
    private ArrayList<HChannel> channels;

    private IPCacheManager manager;
    private Thread downloadThread;

    private ArrayList<HChannelListEvents> events;

    public HCChannelDownloader(File cacheFile) throws IOException
    {

        events = new ArrayList<HChannelListEvents>();

        manager = new IPCacheManager(cacheFile);
        toProcess = manager.read();
        channels = new ArrayList<HChannel>();
        downloadThread = new Thread(this);
    }

    public void addEventListener(HChannelListEvents e)
    {
        if (!events.contains(e))
        {
            events.add(e);
        }
    }

    public void removeEventListener(HChannelListEvents e)
    {
        if (events.contains(e))
        {
            events.remove(e);
        }
    }

    public void start()
    {
        if (!downloadThread.isAlive())
        {
            downloadThread=new Thread(this);
            downloadThread.start();

            HClientEvent evt = new HClientEvent();
            for (int i = 0; i < events.size(); i++)
            {
                events.get(i).onDownloadStart(evt);
            }
        }
    }

    @Override
    public void run()
    {

        HChannel channel, temp;

        byte[] b =
        {
            2
        };
        DatagramPacket pack;
        DatagramSocket sock;

        byte[] bRes = new byte[1024];
        DatagramPacket res = new DatagramPacket(bRes, bRes.length);

        int id;
        HPackage p;
        Iterator<HChannel> i;

        while (!toProcess.isEmpty())
        {
            try
            {
                pack = new DatagramPacket(b, b.length);
                sock = new DatagramSocket();
                sock.setSoTimeout(800);

                channel = toProcess.poll();

                sock.connect(new InetSocketAddress(channel.getPublicIP(), channel.getPort()));
                sock.send(pack);
                sock.receive(res);
                sock.disconnect();
                ByteBuffer bb = ByteBuffer.wrap(res.getData());
                bb.order(ByteOrder.LITTLE_ENDIAN);
                id = (bb.get() & 0xFF);
                p = deserialize(id, bb);

                if (p != null)
                {
                    //TODO: change to dispacher
                    switch (p.getId())
                    {
                        case 3:
                            D3 d = ((D3) p);

                            toProcess.remove(channel);
                            channel.setName(d.getName());
                            channel.setTopic(d.getTopic());
                            channel.setPort(d.getPort());
                            channel.setLanguage(d.getLanguage());
                            channel.setServerVersion(d.getServerVersion());
                            channel.setUserCount(d.getUserCount());

                            channels.add(channel);
                            notifyAll(channel, channels.indexOf(channel));
                            i = d.getKnownChannels().iterator();

                            while (i.hasNext())
                            {
                                temp = i.next();

                                if (!(toProcess.contains(temp) || channels.contains(temp)))
                                {
                                    toProcess.add(temp);
                                }
                            }
                            break;
                    }
                }
                sock.close();
            } catch (SocketTimeoutException ex)
            {

            } catch (PortUnreachableException ex)
            {

            } catch (IOException ex)
            {

            }

        }
        System.out.println(channels.size() + " Downloaded");
        HClientEvent evt = new HClientEvent();
        for (int j = 0; j < events.size(); j++)
        {
            events.get(j).onDownloadFinish(evt);
        }
    }

    private HPackage deserialize(int id, ByteBuffer payload)
    {
        Class<?> clazz = null;
        HPackage p = null;
        try
        {
            clazz = Class.forName("com.hermes.server.packages.udp.D" + id);

        } catch (ClassNotFoundException ex)
        {
            try
            {
                clazz = Class.forName("com.hermes.server.packages.udp.PDefault");

            } catch (ClassNotFoundException ex1)
            {
                System.err.println("*******************ID: "+id+" not found");
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
            } catch (NoSuchMethodException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, id + " " + ex);
            } catch (SecurityException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex)
            {
                Logger.getLogger(HClient.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

        return p;

    }

    public HChannel get(int index)
    {
        HChannel ret = null;
        if (0 <= index && index < channels.size())
        {
            ret = channels.get(index);
        }

        return ret;
    }

    public void restart()
    {
        if (!downloadThread.isAlive())
        {
            toProcess.clear();
            toProcess.addAll(channels);
            channels.clear();
        }
    }

    private void notifyAll(HChannel channel, int index)
    {
        for (int i = 0; i < events.size(); i++)
        {
            events.get(i).onNewChannel(channel, index,channels.size());
        }
    }
}
