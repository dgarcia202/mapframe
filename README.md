# mapframe
Simplistic interfaces and base classes to manage manually implemented mapper classes in a more or less elegant way.
No automapping. mapframe provides a container for your mappers.
You only need to inject your mapper container and resolve the mapper for the types you need.

## Mapper
The `Mapper<TInput, TOutput>` interface and the `MapperBase<TInput, TOutput>` abstract class provide the common contract and basic behaviour for the mappers.
The recommendation is to inherit your mappers from `MapperBase`.

The Mapper interface only exposes two methods one for mapping individual objects and another for collections.

## Mapper factory
The mapper factory's duty is to store and serve your mappers. The factory provides a method for register mappers indexed by its input and output types.
