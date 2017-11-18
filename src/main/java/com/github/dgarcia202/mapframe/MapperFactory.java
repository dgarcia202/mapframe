package com.github.dgarcia202.mapframe;

public interface MapperFactory
{
    <TInput, TOutput> void register(Class<TInput> inputClass, Class<TOutput> ouputClass, Mapper<TInput, TOutput> mapper);

    <TInput, TOutput> Mapper<TInput, TOutput> get(Class<TInput> inputClass, Class<TOutput> ouputClass);
}
