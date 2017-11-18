package com.github.dgarcia202.mapframe;

public class DefaultMapperFactory implements MapperFactory
{
    @Override
    public <TInput, TOutput> void register(Class<TInput> inputClass, Class<TOutput> ouputClass, Mapper<TInput, TOutput> mapper)
    {

    }

    @Override
    public <TInput, TOutput> Mapper<TInput, TOutput> get(Class<TInput> inputClass, Class<TOutput> ouputClass)
    {
        return null;
    }
}
