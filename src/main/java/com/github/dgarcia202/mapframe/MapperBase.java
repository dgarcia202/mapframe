package com.github.dgarcia202.mapframe;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MapperBase<TInput, TOutput> implements Mapper<TInput, TOutput>
{
    public abstract TOutput map(TInput input);

    public List<TOutput> map(List<TInput> input)
    {
        return input.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
