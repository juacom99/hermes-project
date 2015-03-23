/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.events;

import com.hermes.common.HUser;



/**
 *
 * @author joaquin
 */
public class HClientUserListevent extends HClientEvent
{
    private HUser user;

    public HClientUserListevent(HUser user)
    {
        super();
        this.user = user;
    }

    public HUser getUser()
    {
        return user;
    }
}
