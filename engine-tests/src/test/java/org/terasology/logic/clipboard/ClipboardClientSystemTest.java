package org.terasology.logic.clipboard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClipboardClientSystemTest {
    private ClipboardClientSystem c = new ClipboardClientSystem();

    @Test
    public void testSetClipboardContents() throws Exception {
        assert(c.setClipboardContents("foo"));
        assert(c.setClipboardContents("24"));
        assert(c.setClipboardContents("Jhkjadhkfjl02938094820980"));
    }

    @Test
    public void testGetClipboardContentAsString() throws Exception {
        c.setClipboardContents("bar");
        assertEquals("bar", c.getClipboardContentsAsString());
        c.setClipboardContents("24");
        assertEquals("24", c.getClipboardContentsAsString());
        c.setClipboardContents("Jhkjadhkfjl02938094820980");
        assertEquals("Jhkjadhkfjl02938094820980", c.getClipboardContentsAsString());
    }
}
