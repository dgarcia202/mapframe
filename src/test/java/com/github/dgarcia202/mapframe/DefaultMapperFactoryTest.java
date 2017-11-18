package com.github.dgarcia202.mapframe;

import com.github.dgarcia202.mapframe.dtos.PersonOneDto;
import com.github.dgarcia202.mapframe.dtos.PersonTwoDto;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class DefaultMapperFactoryTest
{
    @Rule
    public ExpectedException thrownException = ExpectedException.none();

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
    public void RegistersMappers() throws MapperAlreadyRegisteredException
    {

        // arrange
        MapperFactory factory = new DefaultMapperFactory();

        // act
        factory.register(PersonOneDto.class, PersonTwoDto.class, this.mapper);
    }

    @Test
    public void ResolvesMappersGivenInputAndOutputTypes() throws MapperAlreadyRegisteredException, MapperNotFoundException
    {

        // arrange
        MapperFactory factory = new DefaultMapperFactory();
        factory.register(PersonOneDto.class, PersonTwoDto.class, this.mapper);

        // act
        Mapper<PersonOneDto, PersonTwoDto> mapper = factory.get(PersonOneDto.class, PersonTwoDto.class);

        // assert
        Assert.assertNotNull("Mapper should be resolved without issue.", mapper);
    }

    @Test
    public void RegisteringDuplicateMappersThrowsException() throws MapperAlreadyRegisteredException
    {

        // arrange
        this.thrownException.expect(MapperAlreadyRegisteredException.class);
        MapperFactory factory = new DefaultMapperFactory();

        // act
        factory.register(PersonOneDto.class, PersonTwoDto.class, this.mapper);
        factory.register(PersonOneDto.class, PersonTwoDto.class, this.mapper);
    }

    @Test
    public void ResolvingNonExistentMapperThrowsException() throws MapperNotFoundException
    {

        // arrange
        this.thrownException.expect(MapperNotFoundException.class);
        MapperFactory factory = new DefaultMapperFactory();

        // act
        Mapper<PersonOneDto, PersonTwoDto> mapper = factory.get(PersonOneDto.class, PersonTwoDto.class);
    }

    @Test
    public void DuplicateRegistryExceptionInformsMappingParams() throws MapperAlreadyRegisteredException
    {
        // arrange
        this.thrownException.expect(MapperAlreadyRegisteredException.class);
        this.thrownException.expectMessage(containsString(PersonOneDto.class.getSimpleName()));
        this.thrownException.expectMessage(containsString(PersonTwoDto.class.getSimpleName()));

        MapperFactory factory = new DefaultMapperFactory();

        // act
        factory.register(PersonOneDto.class, PersonTwoDto.class, this.mapper);
        factory.register(PersonOneDto.class, PersonTwoDto.class, this.mapper);
    }

    @Test
    public void NonExistentMapperExceptionInformsMappingParams() throws MapperNotFoundException
    {
        // arrange
        this.thrownException.expect(MapperNotFoundException.class);
        this.thrownException.expectMessage(containsString(PersonOneDto.class.getSimpleName()));
        this.thrownException.expectMessage(containsString(PersonTwoDto.class.getSimpleName()));

        MapperFactory factory = new DefaultMapperFactory();

        // act
        Mapper<PersonOneDto, PersonTwoDto> mapper = factory.get(PersonOneDto.class, PersonTwoDto.class);
    }

}
