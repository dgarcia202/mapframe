package com.github.dgarcia202.mapframe;

import com.github.dgarcia202.mapframe.dtos.PersonOneDto;
import com.github.dgarcia202.mapframe.dtos.PersonTwoDto;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MapperTest
{
    @Test
    @Ignore
    public void MapsDtos() {

        // arrange
        PersonOneDto person =  new PersonOneDto();
        Mapper<PersonOneDto, PersonTwoDto> mapper = new PersonOneToPersonTwoMapperDto();

        // act
        PersonTwoDto mapped = mapper.Map(person);

        // assert
        Assert.assertNotNull("test mapper should not return null value.", mapped);
    }

    @Test
    @Ignore
    public void MapsDtoCollections() {
        // arrange
        List<PersonOneDto> persons =  new ArrayList<>();
        persons.add(new PersonOneDto());
        persons.add(new PersonOneDto());
        persons.add(new PersonOneDto());

        Mapper<PersonOneDto, PersonTwoDto> mapper = new PersonOneToPersonTwoMapperDto();

        // act
        List<PersonTwoDto> results = mapper.Map(persons);

        // assert
        Assert.assertNotNull("test mapper should not return null when mapping collections.", results);
        Assert.assertEquals("mapper should return collection with same number of elements.", persons.size(), results.size());
    }
}
