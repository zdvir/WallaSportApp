package com.dvirz.rssfeedapp.Retofit.Response;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Root(name = "channel", strict = false) // If true - All variables must appear (image, title, pubDate....)
public class Channel implements Serializable
{
    @ElementList(inline = true, required = false)
    private ArrayList<Item> items;

    public Channel() {
    }

    public Channel(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
