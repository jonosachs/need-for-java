package com.jonosachs.needforjava;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void gameDoesntThrow() {
        assertDoesNotThrow(Game::new);
    }
}
