package com.byronginvest.modulecollections.designpatterns.decorator;

/**
 * Created by Gosha on 2016-04-21.
 */
public class PacketHTTPHederCreator extends PacketDecorator {
    public PacketHTTPHederCreator(IPacketCreator component) {
        super(component);
    }

    @Override
    public String handlerContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("Cache-Conttrol:no-cache\n");
        sb.append("Date:Mon,31Dec 2012 04:25:57GMT\n");
        sb.append(component.handlerContent());
        return sb.toString();
    }
}
