package com.byronginvest.modulecollections.designpatterns.decorator;

/**
 * Created by Gosha on 2016-04-21.
 */
public class PacketBodyCreator implements IPacketCreator {
    @Override
    public String handlerContent() {
        return "contentr of Packet";
    }
}
