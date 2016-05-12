package com.byronginvest.modulecollections.designpatterns.decorator;

/**
 * Created by Gosha on 2016-04-21.
 */
public abstract  class PacketDecorator implements IPacketCreator{
    IPacketCreator component;
    public PacketDecorator(IPacketCreator component) {
        this.component = component;
    }
}
