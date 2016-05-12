package com.byronginvest.modulecollections.designpatterns.decorator;

/**
 * Created by Gosha on 2016-04-21.
 */
public class PacketHTMLHeaderCreator extends PacketDecorator {
    public PacketHTMLHeaderCreator(IPacketCreator component) {
        super(component);
    }

    @Override
    public String handlerContent() {//将给定的数据封装成HTML
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<body>");
        sb.append(component.handlerContent());
        sb.append("</body>");
        sb.append("</html>\n");
        return sb.toString();
    }
}
