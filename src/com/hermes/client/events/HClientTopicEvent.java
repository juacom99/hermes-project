/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.client.events;

/**
 *
 * @author joaquin
 */
public class HClientTopicEvent extends HClientEvent
{
    private String topic;

    public HClientTopicEvent(String topic)
    {
        super();
        this.topic = topic;
    }

    public String getTopic()
    {
        return topic;
    }
}
