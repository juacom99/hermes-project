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
package com.hermes.server.packages.tcp;

import com.hermes.common.packages.tcp.HPackage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/*
 * @author Joaquin Martinez
 * This Class represents the MSG_CHAT_SERVER_TOPIC_FIRST Package
 * Data Format:
 * [x Bytes - Topic]
 */
public class P32 extends HPackage
{

    private String topic;

    public P32(String topic)
    {
        super(32);
        this.topic = topic;
    }

    public P32(ByteBuffer bb)
    {
        super(32);
        this.topic=new String(bb.array(),charset);
    }

    @Override
    public ByteBuffer getPayload()
    {
        byte[] bTopic = this.topic.getBytes(charset);

        ByteBuffer msg = ByteBuffer.allocate(1 + bTopic.length);
        msg.order(ByteOrder.LITTLE_ENDIAN);

        msg.put(bTopic);
        msg.put((byte) 0);
        return msg;
    }

    @Override
    public String toString()
    {
        return "TOPIC: "+this.topic;
    }

    public String getTopic()
    {
        return topic;
    }
}
