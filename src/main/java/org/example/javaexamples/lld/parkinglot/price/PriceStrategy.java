package org.example.javaexamples.lld.parkinglot.price;

import org.example.javaexamples.lld.parkinglot.ticket.Ticket;

public interface PriceStrategy {
    Double calculatePrice(Ticket ticket);
}
