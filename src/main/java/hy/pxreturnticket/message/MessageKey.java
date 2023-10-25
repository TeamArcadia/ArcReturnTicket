package hy.pxreturnticket.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageKey {
    /* --------------- NORMAL ---------------*/
    PREFIX("normal.prefix"),
    RELOAD_CONFIG("normal.reload_config"),

    /* --------------- ERROR ---------------*/
    PLAYER_ONLY("error.player_only"),
    NO_PERMISSION("error.no_permission"),
    WRONG_COMMAND("error.wrong_command"),

    /* --------------- MAIN ---------------*/
    CREATE_TICKET("main.create_ticket"),
    FAILED_CREATE_TICKET("main.failed_create_ticket"),
    CREATE_CREATE_TICKET("main.create_create_ticket"),
    CREATE_IN_HAND_ITEM("main.create_in_hand_item"),
    SAME_WARP_NAME("main.same_warp_name"),
    SAME_ITEM_NAME("main.same_item_name"),
    WARP_DELETE("main.warp_delete"),
    WARP_CANT_FIND("main.warp_cant_find"),
    CLICK_TICKET("main.click_ticket"),
    PLAYER_MOVED("main.player_moved"),
    PLAYER_NOT_IN_HAND("main.player_not_in_hand"),
    USED_TICKET("main.used_ticket");


    private final String key;

}
