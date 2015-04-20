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
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author Joaquin Martinez
 * This Class represents the MSG_CHAT_CLIENT_AVATAR
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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage bi = new BufferedImage(avatar.getIconWidth(), avatar.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(this.avatar.getImage(), 0, 0, null);

        try
        {
            ImageIO.write(bi, "JPEG", baos);
            byte[] bAvatar = baos.toByteArray();
            msg = ByteBuffer.allocate(bAvatar.length);
            msg.order(ByteOrder.LITTLE_ENDIAN);
            msg.put(bAvatar);
        }
        catch (IOException ex)
        {

            return msg;
        }
        return msg;
    }
}
