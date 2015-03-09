/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.events;

/**
 *
 * @author jomartinez
 */
public class HClientUrlEvent extends HClientEvent
{
    private String url;
    private String urlCaption;

    public HClientUrlEvent(String url, String urlCaption)
    {
        super();
        this.url = url;
        this.urlCaption = urlCaption;
    }

    public String getUrl()
    {
        return url;
    }

    public String getUrlCaption()
    {
        return urlCaption;
    }
}
