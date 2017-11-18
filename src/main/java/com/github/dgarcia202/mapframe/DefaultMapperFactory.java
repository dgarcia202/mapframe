package com.github.dgarcia202.mapframe;

import java.util.HashMap;
import java.util.Map;

public class DefaultMapperFactory implements MapperFactory
{
    private Map<Class<?>, Map<Class<?>, Mapper<?, ?>>> registry = new HashMap<>();

    @Override
    public <TInput, TOutput> void register(Class<TInput> inputClass, Class<TOutput> outputClass, Mapper<TInput, TOutput> mapper)
            throws MapperAlreadyRegisteredException
    {
        Map<Class<?>, Mapper<?, ?>> secondLevel;
        if (this.registry.containsKey(inputClass))
        {
            secondLevel = this.registry.get(inputClass);
        }
        else
        {
            secondLevel = new HashMap<>();
            registry.put(inputClass, secondLevel);
        }

        if (secondLevel.containsKey(outputClass))
        {
            throw new MapperAlreadyRegisteredException(inputClass, outputClass);
        }
        else
        {
            secondLevel.put(outputClass, mapper);
        }
    }

    @Override
    public <TInput, TOutput> Mapper<TInput, TOutput> get(Class<TInput> inputClass, Class<TOutput> outputClass)
            throws MapperNotFoundException
    {
        if (!this.registry.containsKey(inputClass))
        {
            throw new MapperNotFoundException(inputClass, outputClass);
        }

        Map<Class<?>, Mapper<?, ?>> secondLevel = this.registry.get(inputClass);
        if (!secondLevel.containsKey(outputClass))
        {
            throw new MapperNotFoundException(inputClass, outputClass);
        }

        if (secondLevel.get(outputClass) == null)
        {
            throw new MapperNotFoundException(inputClass, outputClass);
        }

        return (Mapper<TInput, TOutput>) secondLevel.get(outputClass);
    }
}
