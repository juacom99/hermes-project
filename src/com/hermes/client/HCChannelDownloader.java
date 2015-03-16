/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client;

import com.hermes.common.HChannel;
import com.hermes.common.IPCacheManager;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
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

    public HCChannelDownloader(File cacheFile) throws IOException
    {

        manager = new IPCacheManager(cacheFile);
        toProcess = manager.read();
        downloadThread=new Thread(this);
    }

    
    public void start()
    {
        if(!downloadThread.isAlive())
        {
            downloadThread.start();
        }
    }
    @Override
    public void run()
    {
        try
        {
            HChannel channel;
            
            int i;

            byte[] b =
            {
                2
            };
            DatagramPacket pack = new DatagramPacket(b, b.length);
            DatagramSocket sock = new DatagramSocket();

            byte[] bRes = new byte[1024];
            DatagramPacket res = new DatagramPacket(bRes, bRes.length);

            sock.setSoTimeout(800);

            while (!toProcess.isEmpty())
            {
                channel = toProcess.poll();
                sock.connect(new InetSocketAddress(channel.getPublicIP(),channel.getPort()));
                sock.send(pack);
                sock.receive(res);
                ByteBuffer bb = ByteBuffer.wrap(res.getData());
                bb.order(ByteOrder.LITTLE_ENDIAN);
            }
        } catch (SocketException ex)
        {
            Logger.getLogger(HCChannelDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(HCChannelDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
