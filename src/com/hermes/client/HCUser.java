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

import com.hermes.common.HUser;
import com.hermes.common.constants.HBrowsable;
import com.hermes.common.constants.HGender;
import com.hermes.common.constants.HLineType;
import com.hermes.common.constants.HLocation;
import java.net.InetAddress;

/**
 *
 * @author joaquin
 */
public class HCUser extends HUser
{

    public HCUser(String username,String guid, int filecount, HLineType linetype, HBrowsable browsable,byte age, HGender gender, HLocation countryCode, String region, InetAddress publicIp, int dataport, InetAddress privateIp, InetAddress nodeIp, int nodePort, byte uploads, byte maxUploads, byte queued)
    {
        super(username, guid, filecount, linetype, browsable, age, gender, countryCode, region, publicIp, dataport, privateIp, nodeIp, nodePort, uploads, maxUploads, queued);
    }
    
    public HCUser(String username,short filecount, HLineType linetype, HBrowsable browsable, byte age, HGender gender, HLocation countryCode, String region, InetAddress publicIp, short dataport, InetAddress privateIp, InetAddress nodeIp, short nodePort, byte uploads, byte maxUploads, byte queued)
    {
        super(username,filecount, linetype, browsable,age, gender, countryCode, region, publicIp, dataport, privateIp, nodeIp, nodePort, uploads, maxUploads, queued);
    }
}
