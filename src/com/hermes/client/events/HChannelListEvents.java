/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.events;

import com.hermes.common.HChannel;

/**
 *
 * @author joaquin
 */
public interface HChannelListEvents
{

    public void onNewChannel(HChannel channel, int index,int count);

    public void onDownloadStart(HClientEvent evt);

    public void onDownloadFinish(HClientEvent evt);

}
