/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.server.packages.tcp;

import com.hermes.common.packages.tcp.HPackage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * @author Joaquin Martinez
 * This Class represents the MSG_CHAT_SERVER_AVATAR Package
 * Data Format:
 * [x Bytes - Sender username]
 * [1 Byte  - null]
 * [x Byte  - Avatar bytes]
 */
public class P9 extends HPackage
{
        private String username;
        private ImageIcon avatar;

    public P9(String username, ImageIcon avatar) {
        super(9);
        this.username = username;
        this.avatar = avatar;
    }
    
    public P9(ByteBuffer bb)
    {
        super(9);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        this.username=readNullTerminatedString(bb);
        byte[] bAvatar=new byte[bb.remaining()];
        bb.get(bAvatar);
        this.avatar=new ImageIcon(bAvatar);
    }

    @Override
    public ByteBuffer getPayload()
    {
      return null;  
    }

    @Override
    public String toString()
    {
        //JOptionPane.showMessageDialog(null,avatar.getImage().getWidth(null)+"X"+avatar.getImage().getHeight(null),username,JOptionPane.INFORMATION_MESSAGE,avatar);
        return "Image from "+username;
    }

    public String getUsername()
    {
        return username;
    }
    public ImageIcon getAvatar()
    {
        return avatar;
    }
        
    
    
    
    
}
