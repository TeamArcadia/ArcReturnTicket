package hy.pxreturnticket.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageType {

    NORMAL("normalMessage"),
    ERROR("errorMessage"),
    RETURNTICKET("returnTicketMessage");


    private final String key;

}