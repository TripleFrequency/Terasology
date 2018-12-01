package org.terasology.logic.chat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.terasology.entitySystem.entity.EntityRef;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ChatSystemTest {
    @Mock
    EntityRef er;

    @Mock
    ChatSystem cs;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ifPassedMockSenderAndString_whenSay_returnSent() {
        String[] message = {"Hello, World!"};
        when(cs.say(er, message)).thenReturn("Message sent.");

        String actual = cs.say(er, message);
        assertEquals("Message sent.", actual);
    }
}
