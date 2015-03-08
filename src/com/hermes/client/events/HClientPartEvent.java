/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.events;

import com.hermes.client.HCUser;

/**
 *
 * @author jomartinez
 */
public class HClientPartEvent extends HClientEvent
{
     private HCUser user;

    public HClientPartEvent(HCUser user)
    {
        this.user = user;
    }

    public HCUser getUser()
    {
        return user;
    }
     
     
}
