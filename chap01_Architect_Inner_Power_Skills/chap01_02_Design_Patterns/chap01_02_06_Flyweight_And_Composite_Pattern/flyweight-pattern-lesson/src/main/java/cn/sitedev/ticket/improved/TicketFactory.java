package cn.sitedev.ticket.improved;

import cn.sitedev.ticket.ITicket;
import cn.sitedev.ticket.TrainTicket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketFactory {
    private static Map<String, ITicket> ticketPool = new ConcurrentHashMap<>();

    public static ITicket queryTicket(String from, String to) {
        String key = from + "->" + to;
        if (ticketPool.containsKey(key)) {
            System.out.println("\n使用缓存, key:" + key);
            return ticketPool.get(key);
        }
        System.out.println("\n首次查询, 创建对象, key:" + key);
        ITicket ticket = new TrainTicket(from, to);
        ticketPool.put(key, ticket);
        return ticket;
    }

}
