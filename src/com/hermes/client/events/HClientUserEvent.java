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
public class HClientUserEvent extends HClientEvent
{
    private HCUser user;

    public HClientUserEvent(HCUser user)
    {
        super();
        this.user = user;
    }

    public HCUser getUser()
    {
        return user;
    }
}
