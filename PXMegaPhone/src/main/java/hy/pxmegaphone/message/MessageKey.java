package hy.pxmegaphone.message;

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
    WRONG_COMMAND("error.wrong_command");

    /* --------------- MAIN ---------------*/

    private final String key;

}