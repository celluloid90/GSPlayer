package com.gsplayer.presentation;

import com.gsplayer.model.InstanceVisitor;

public abstract class InstanceFormatter implements InstanceVisitor<String>
{
    final static String DELIMITER = " - ";

    public final static InstanceFormatter SHORT = new ShortInstanceFormatter();
    public final static InstanceFormatter SHORT_WITH_NUMBER = new ShortWithNumberInstanceFormatter();
    public final static InstanceFormatter LIST = new OverAllFormatter();
}
