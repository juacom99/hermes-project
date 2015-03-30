/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.common;

import com.hermes.client.HCChannel;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author joaquin
 */
public class HHash
{

    private static final int MAGIC_NUMBER = 28435;
    private static final int DEFAULT_COMPRESSION_LEVEL = 6;

    private static HHash instance;

    private HHash()
    {

    }

    public HChannel decode(String hashlink) throws IOException, DataFormatException
    {

        HChannel ch = null;
        if (hashlink.startsWith("arlnk://"))
        {
            hashlink = hashlink.substring(8);
        }

        if (hashlink.toUpperCase().startsWith("CHATROOM:"))
        {
            hashlink = hashlink.substring(9);
            int cloneIndex = hashlink.indexOf(":");
            int barIndex = hashlink.indexOf("|");

            try
            {
                InetAddress ip = InetAddress.getByName(hashlink.substring(0, cloneIndex));
                int port = Integer.parseInt(hashlink.substring(cloneIndex + 1, barIndex));
                String name = hashlink.substring(barIndex + 1, hashlink.length());

                System.out.println(name + " " + ip.getHostAddress() + ":" + port);

                ch = new HCChannel(name, ip, port, InetAddress.getLocalHost(), "");
            }
            catch (UnknownHostException ex)
            {
                Logger.getLogger(HHash.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else
        {

            byte[] b =DatatypeConverter.parseBase64Binary(hashlink); //Base64.decode(hashlink);
            b = d67(b, MAGIC_NUMBER);
            b = gzUncompress(b);

            ByteBuffer bb = ByteBuffer.wrap(b);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            bb.position(32);

            byte[] bIp = new byte[4];

            bb.get(bIp);
            InetAddress privateIp = InetAddress.getByAddress(bIp);
            byte hi=bb.get();
            byte low=bb.get();
            
            int port = ( ((low&0xFF)<<8) | (hi&0xFF) ); 

            bb.get(bIp);
            InetAddress publicIp = InetAddress.getByAddress(bIp);

            int sPos = bb.position();
            int fPos = sPos;
            while (bb.hasRemaining())
            {

                if (bb.get() == 0)
                {
                    break;
                }
                fPos = bb.position();
            }
            bb.position(sPos);

            byte[] bName = new byte[fPos - bb.position()];

            bb.get(bName);

            if (bb.remaining() >= 1)
            {
                //skip 
                bb.get();
            }

            String name = new String(bName);

            sPos = bb.position();

            while (bb.hasRemaining())
            {

                if (bb.get() == 0)
                {
                    break;
                }
                fPos = bb.position();
            }
            bb.position(sPos);

            String topic = "";
            if (bb.remaining() > 1)
            {
                byte[] bTopic = new byte[fPos - bb.position()];

                bb.get(bTopic);

                if (bb.remaining() >= 1)
                {
                    //skip 
                    bb.get();
                }
                topic = new String(bTopic, StandardCharsets.UTF_8);
            }

            ch = new HCChannel(name, publicIp, port, privateIp, topic);

        }
        return ch;
    }

    public String encode(HChannel ch) throws UnsupportedEncodingException, UnknownHostException, IOException
    {
        String hash = "";
        Charset charset = Charset.forName("UTF-8");

        byte[] bRoomname = ch.getName().getBytes(charset);
        byte[] bTopic = ch.getTopic().getBytes(charset);

        ByteBuffer bb = ByteBuffer.allocate(44 + bRoomname.length + bTopic.length);
        bb.order(ByteOrder.LITTLE_ENDIAN);

        bb.put(new byte[20]);

        bb.put("CHATCHANNEL".getBytes(charset));
        bb.put((byte) 0);
        bb.put(ch.getPublicIP().getAddress());
        bb.putShort((short) ch.getPort());
        bb.put(ch.getPrivateIP().getAddress());
        bb.put(bRoomname);
        bb.put((byte) 0);
        bb.put(bTopic);
        bb.put((byte) 0);

        byte[] b = bb.array();
        b = gzCompress(b);
        b = e67(b, MAGIC_NUMBER);
        return "arlnk://" + DatatypeConverter.printBase64Binary(b); //Base64.encode(b);

    }

    public static HHash getInstance()
    {
        if (instance == null)
        {
            instance = new HHash();
        }
        return instance;
    }

    private static byte[] d67(byte[] data, int b)
    {
        byte[] buffer = data.clone();

        for (int i = 0; i < data.length; i++)
        {
            buffer[i] = (byte) (data[i] ^ b >> 8 & 255);
            b = (b + (data[i] & 0xff)) * 23219 + 36126 & 65535;
        }
        return buffer;
    }

    private static byte[] e67(byte[] data, int b)
    {
        byte[] buffer = new byte[data.length];
        int w = b;
        for (int i = 0; i < data.length; i++)
        {
            buffer[i] = (byte) ((data[i] ^ (w >> 8)) & 255);
            w = (((buffer[i] & 0xff) + w) * 23219 + 36126) & 0xffff;
        }

        return buffer;
    }

    private byte[] gzCompress(byte[] data) throws IOException
    {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        byte[] bytesCompressed = new byte[Short.MAX_VALUE];

        int numberOfBytesAfterCompression = deflater.deflate(bytesCompressed);

        byte[] returnValues = new byte[numberOfBytesAfterCompression];

        System.arraycopy(
                bytesCompressed,
                0,
                returnValues,
                0,
                numberOfBytesAfterCompression
        );

        return returnValues;

    }

    private byte[] gzUncompress(byte[] data) throws IOException, DataFormatException
    {
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        byte[] bytesCompressed = new byte[Short.MAX_VALUE];

        int numberOfBytesAfterUncompression = inflater.inflate(bytesCompressed);

        byte[] returnValues = new byte[numberOfBytesAfterUncompression];

        System.arraycopy(bytesCompressed, 0, returnValues, 0, numberOfBytesAfterUncompression
        );

        return returnValues;
    }
}
