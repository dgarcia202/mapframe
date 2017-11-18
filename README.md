# mapframe
Simplistic interfaces and base classes to manage manually implemented mapper classes in a more or less elegant way.
No automapping. mapframe provides a container for your mappers.
You only need to inject your mapper container and resolve the mapper for the types you need.

## Mapper
The `Mapper<TInput, TOutput>` interface and the `MapperBase<TInput, TOutput>` abstract class provide the common contract and basic behaviour for the mappers.
The recommendation is to inherit your mappers from `MapperBase`.

```java
public class PersonEntityToPersonDtoMapper extends MapperBase<PersonEntity, PersonDto>
{

    @Override
    public PersonDto map(PersonEntity input)
    {
        if (input == null)
        {
            return null;
        }

        PersonDto out = new PersonTwoDto();
        out.setName(input.getName());
        out.setAge(input.getAge());
        out.setBirthDate(input.getBirthDate());
        out.setActive(input.isActive());

        return out;
    }
}
```
    
The Mapper interface only exposes two methods one for mapping individual objects... 

```java
PersonEntity person =  new PersonEntity();

Mapper<PersonEntity, PersonDto> mapper = new PersonEntityToPersonDtoMapper();

PersonDto dto = mapper.map(person);
```

and another for collections.

```java
List<PersonEntity> persons =  new ArrayList<>();
persons.add(new PersonEntity());
persons.add(new PersonEntity());
persons.add(new PersonEntity());

Mapper<PersonEntity, PersonDto> mapper = new PersonEntityToPersonDtoMapper();

List<PersonDto> results = mapper.map(persons);
```

## Mapper factory
The mapper factory's duty is to store and serve your mappers. The factory provides a method for register mappers indexed by its input and output types.

Register your mappers on application start up in a `MapperFactory` instance scoped as singleton.

```java
MapperFactory factory = new DefaultMapperFactory();

factory.register(PersonOneDto.class, PersonTwoDto.class, mapper);

Mapper<PersonOneDto, PersonTwoDto> mapper = factory.get(PersonOneDto.class, PersonTwoDto.class);
```
