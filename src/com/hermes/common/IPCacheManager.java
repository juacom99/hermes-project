/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 *
 * @author joaquin
 */
public class IPCacheManager
{

    private File file;
    private FileOutputStream outputStream;

    private static byte[] garbage =
    {
        0, 0, 0, 0, 0, 0
    };

    public IPCacheManager(String path) throws IOException
    {
        this(new File(path));
    }

    public IPCacheManager(File file)
    {
        this.file = file;
        try
        {
            this.file.createNewFile();
            this.outputStream = new FileOutputStream(file, true);
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public void write(InetAddress ip, short port) throws FileNotFoundException, IOException
    {
        HChannel tempChannel = new HChannel(ip, port);

        byte[] bPort = new byte[2];

        bPort[1] = (byte) (port & 0xff);
        bPort[0] = (byte) ((port >> 8) & 0xff);

        file.toPath();

        if (file.length() == 0)
        {
            outputStream.write(garbage);
        }
        outputStream.write(ip.getAddress());
        outputStream.write(bPort);
        outputStream.write(garbage);
        outputStream.flush();
    }

    public ArrayList<HChannel> read() throws FileNotFoundException, IOException
    {
        ArrayList<HChannel> channels = new ArrayList<HChannel>();

        InputStream is = new FileInputStream(file);
        is.skip(6);

        byte[] bFile = new byte[is.available()];
        is.read(bFile);
        ByteBuffer bb = ByteBuffer.wrap(bFile);

        byte b;

        byte[] bIp = new byte[4];
        InetAddress ip;
        int port;
        while (bb.remaining() > 0)
        {
            bb.get(bIp);
            ip = InetAddress.getByAddress(bIp);
            port = bb.getShort() & 0xFFFF;
            bb.position(bb.position() + 6);//skip 6
            channels.add(new HChannel(ip, port));
        }
        is.close();
        return channels;
    }

    public void clear() throws IOException
    {
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
    }

    public void close() throws IOException
    {
        outputStream.close();
    }
}
