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
    public void MapsDtos() {

        // arrange
        PersonOneDto person =  new PersonOneDto();
        Mapper<PersonOneDto, PersonTwoDto> mapper = new PersonOneToPersonTwoMapperDto();

        // act
        PersonTwoDto mapped = mapper.map(person);

        // assert
        Assert.assertNotNull("test mapper should not return null value.", mapped);
    }

    @Test
    public void MapsDtoCollections() {
        // arrange
        List<PersonOneDto> persons =  new ArrayList<>();
        persons.add(new PersonOneDto());
        persons.add(new PersonOneDto());
        persons.add(new PersonOneDto());

        Mapper<PersonOneDto, PersonTwoDto> mapper = new PersonOneToPersonTwoMapperDto();

        // act
        List<PersonTwoDto> results = mapper.map(persons);

        // assert
        Assert.assertNotNull("test mapper should not return null when mapping collections.", results);
        Assert.assertEquals("mapper should return collection with same number of elements.", persons.size(), results.size());
    }

    public class PersonOneToPersonTwoMapperDto extends MapperBase<PersonOneDto, PersonTwoDto>
    {

        @Override
        public PersonTwoDto map(PersonOneDto input)
        {
            if (input == null)
            {
                return null;
            }

            PersonTwoDto out = new PersonTwoDto();
            out.setName(input.getName());
            out.setAge(input.getAge());
            out.setBirthDate(input.getBirthDate());
            out.setActive(input.isActive());

            return out;
        }
    }
}
