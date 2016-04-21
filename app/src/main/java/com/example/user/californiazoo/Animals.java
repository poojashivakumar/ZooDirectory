package com.example.user.californiazoo;

import java.io.Serializable;


public class Animals implements Serializable
{
    private String name;
    private int iconId;
    private String description;

    public Animals(String name, int iconId , String description)
    {
        super();
        this.name = name;
        this.iconId = iconId;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public int getIconId()
    {
        return iconId;
    }

    public String getDescription()
    {
        return description;
    }
}
