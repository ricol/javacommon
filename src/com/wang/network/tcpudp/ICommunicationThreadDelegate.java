/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.network.tcpudp;

/**
 * @author ricolwang
 */
public interface ICommunicationThreadDelegate
{

    public void CommunicationThreadStart(CommunicationThread thread);

    public void CommunicationThreadEnd(CommunicationThread thread);
}
