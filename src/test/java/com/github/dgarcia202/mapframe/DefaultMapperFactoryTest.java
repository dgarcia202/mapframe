package com.github.dgarcia202.mapframe;

import com.github.dgarcia202.mapframe.dtos.PersonOneDto;
import com.github.dgarcia202.mapframe.dtos.PersonTwoDto;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DefaultMapperFactoryTest
{
    private Mapper<PersonOneDto, PersonTwoDto> mapper = new Mapper<PersonOneDto, PersonTwoDto>()
    {
        @Override
        public PersonTwoDto map(PersonOneDto personOneDto)
        {
            return null;
        }

        @Override
        public List<PersonTwoDto> map(List<PersonOneDto> input)
        {
            return new ArrayList<>();
        }
    };

    @Test
    public void RegistersMappers() {

        // arrange
        MapperFactory factory = new DefaultMapperFactory();

        // act
        factory.register(PersonOneDto.class, PersonTwoDto.class, this.mapper);
    }

    @Test
    public void ResolvesMappersGivenInputAndOutputTypes() {

        // arrange
        MapperFactory factory = new DefaultMapperFactory();
        factory.register(PersonOneDto.class, PersonTwoDto.class, this.mapper);

        // act
        Mapper<PersonOneDto, PersonTwoDto> mapper = factory.get(PersonOneDto.class, PersonTwoDto.class);

        // assert
        Assert.assertNotNull("Mapper should be resolved without issue.", mapper);
    }

    @Test(expected = MapperAlreadyRegisteredException.class)
    public void RegisteringDuplicateMappersThrowsException() {

        // arrange
        MapperFactory factory = new DefaultMapperFactory();

        // act
        factory.register(PersonOneDto.class, PersonTwoDto.class, this.mapper);
        factory.register(PersonOneDto.class, PersonTwoDto.class, this.mapper);
    }

    @Test(expected = MapperNotFoundException.class)
    public void ResolvingNonExistentMapperThrowsException() {

        // arrange
        MapperFactory factory = new DefaultMapperFactory();

        // act
        Mapper<PersonOneDto, PersonTwoDto> mapper = factory.get(PersonOneDto.class, PersonTwoDto.class);
    }
}
