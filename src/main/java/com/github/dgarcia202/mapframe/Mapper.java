package com.github.dgarcia202.mapframe;

import java.util.List;

public interface Mapper<TInput, TOutput>
{
    TOutput map(TInput input);

    List<TOutput> map(List<TInput> input);
}
