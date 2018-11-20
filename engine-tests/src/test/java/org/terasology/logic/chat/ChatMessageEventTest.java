package org.terasology.logic.chat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.logic.console.Message;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ChatMessageEventTest {
    private String message;
    @Mock
    EntityRef er;

    @Mock
    ChatMessageEvent cme;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        message = "Hello, World!";
    }

    @Test
    public void ifPassedMockSenderAndString_whenGetMessage_thenReturnMessage() {
        ChatMessageEvent actual = new ChatMessageEvent(message, er);
        assertEquals("Hello, World!", actual.getMessage());
    }

    @Test
    public void ifPassedMockSenderAndString_whenGetFormattedMessage_thenReturnFormattedMessage() {
        Message expected = new Message("Elliott: Hello, World!");
        when(cme.getFormattedMessage()).thenReturn(expected);
        assertEquals(expected, cme.getFormattedMessage());
    }
}
