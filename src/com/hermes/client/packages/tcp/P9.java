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
package com.hermes.client.packages.tcp;

import com.hermes.common.packages.tcp.HPackage;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sun.awt.image.JPEGImageDecoder;
import sun.awt.image.codec.JPEGImageEncoderImpl;

/**
 * @author Joaquin Martinez This Class represents the MSG_CHAT_CLIENT_AVATAR
 * Package Data Format: [x Bytes - Avatar]
 */
public class P9 extends HPackage
{

    private ImageIcon avatar;

    public P9(ImageIcon avatar)
    {
        super(9);
        this.avatar = avatar;
    }

    public P9(ByteBuffer bb)
    {
        super(9);
        this.avatar = new ImageIcon(bb.array());
    }

    @Override
    public ByteBuffer getPayload()
    {
        ByteBuffer msg = null;
        try
        {
            BufferedImage bi = new BufferedImage(avatar.getIconWidth(), avatar.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
            // paint the Icon to the BufferedImage.
            avatar.paintIcon(null, g, 0, 0);
            g.dispose();

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(bi);
            
            byte[] bAvatar=os.toByteArray();
             msg=ByteBuffer.allocate(bAvatar.length);
             msg.order(ByteOrder.LITTLE_ENDIAN);
             msg.put(bAvatar);
            
        }
        catch (IOException ex)
        {
            Logger.getLogger(P9.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ImageFormatException ex)
        {
            Logger.getLogger(P9.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return msg;
    }

}
