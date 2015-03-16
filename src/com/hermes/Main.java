/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes;

import com.hermes.client.HCChannelDownloader;
import com.hermes.client.HCUser;
import com.hermes.client.HClient;
import com.hermes.client.events.HClientAvatarEvent;
import com.hermes.client.events.HClientEmoteEvent;
import com.hermes.client.events.HClientJoinEvent;
import com.hermes.client.events.HClientMessageEvent;
import com.hermes.client.events.HClientNoSuchEvent;
import com.hermes.client.events.HClientPartEvent;
import com.hermes.client.events.HClientPersonalMessageEvent;
import com.hermes.client.events.HClientUrlEvent;
import com.hermes.client.events.HIClientEvents;
import com.hermes.common.HChannel;
import com.hermes.common.HHash;
import com.hermes.common.constants.HBrowsable;
import com.hermes.common.constants.HGender;
import com.hermes.common.constants.HLineType;
import com.hermes.common.constants.HLocation;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import javax.swing.ImageIcon;


/**
 *
 * @author jomartinez
 */
public class Main
{

    public static void main(String[] args) throws UnknownHostException, IOException, Exception
    {

        HChannel ch = HHash.getInstance().decode("arlnk://CHATROOM:127.0.0.1:5000|Test");

        System.out.println("Connecting to: " + ch.getName() + " " + ch.getPublicIP().getHostAddress() + ":" + ((int) ch.getPort()) + "  " + ch.getTopic()+" on "+new Date());

        HCUser user = new HCUser("Ħεямεѕ", "ABCDEFGHIJKLMNOP", (short) 155, HLineType.HLNone, HBrowsable.Browsable, (byte) 30, HGender.Male, HLocation.Uruguay, "Montevideo", InetAddress.getByName("190.20.65.98"), (short) 14884, InetAddress.getByName("10.1.20.56"), InetAddress.getByName("0.0.0.0"), (short) 12345, (byte) 12, (byte) 34, (byte) 0);

        
        user.setAvatar(new ImageIcon("./avatar.png"));

        final HClient c = new HClient(user);

        HIClientEvents e = new HIClientEvents()
        {

            @Override
            public void onPublicMessage(HClientMessageEvent evt)
            {
                System.out.println(evt.getSender()+" > "+evt.getText());
            }

            @Override
            public void onPrivateMessage(HClientMessageEvent evt)
            {
                System.out.println("PM FROM: "+evt.getSender()+" > "+evt.getText());
            }

            @Override
            public void onNoSuch(HClientNoSuchEvent evt)
            {
                 System.out.println(evt.getNoSuch());
            }

            @Override
            public void onJoin(HClientJoinEvent evt)
            {
                System.out.println(evt.getUser()+" has join the channel");
            }

            @Override
            public void onPart(HClientPartEvent evt)
            {
                System.out.println(evt.getUser()+" has part the channel");
            }

            @Override
            public void onPersonalMessage(HClientPersonalMessageEvent evt)
            {
               
            }

            @Override
            public void onAvatar(HClientAvatarEvent evt)
            {
                
            }

            @Override
            public void onEmote(HClientEmoteEvent evt)
            {
                System.out.println("* "+evt.getUsername()+" "+evt.getEmote() );
            }

            @Override
            public void onURL(HClientUrlEvent evt)
            {
                //System.out.println("URL: "+evt.getUrlCaption()+"["+evt.getUrl()+"]");
            }
        };
        c.addClientEventListener(e);

        c.connect(ch.getPublicIP(), ch.getPort());
        Scanner in = new Scanner(System.in);
        String line;
        boolean conected=true;
        while (conected)
        {
            line = in.nextLine();
            if (line != null && !line.isEmpty())
            {

                if (line.startsWith("/me"))
                {
                    c.sendEmote(line.substring(3));
                }
                else if (line.startsWith("/pm"))
                {
                    if (line.length() > 4)
                    {
                        String params = line.substring(4);
                        int index = params.indexOf(" ");

                        if (index != -1)
                        {
                            String to = params.substring(0, index);

                            if (params.length() > to.length() + 1)
                            {
                                String msg = params.substring(index + 1, params.length());

                                if (!msg.trim().equals(""))
                                {
                                    c.sendPM(to, msg);
                                }
                            }
                        }

                    }

                }
                else if (line.startsWith("/") || line.startsWith("#"))
                {
                    c.sendCommand(line.substring(1));
                }
                else
                {
                    c.sendMessage(line);
                }
            }
        }
        
       HCChannelDownloader cd=new HCChannelDownloader(new File("./ChatroomIPs.dat"));
        cd.start();
        
    }
    
   
}
