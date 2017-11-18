package com.github.dgarcia202.mapframe;

public class MapperAlreadyRegisteredException extends Exception
{
    public MapperAlreadyRegisteredException(Class<?> inputClass, Class<?> outputClass)
    {
        super(String.format("A mapper from %s to %s is already registered in this factory.", inputClass.getName(), outputClass.getName()));
    }
}
