# Buster

## What is this Project?

This is a project that help you to do commands and queries in a project with CQRS Architecture.  
    
## How can I  use?

First of all, you need have a project with Java or Kotlin that uses Frameworks Spring or Quarkus. You can add the dependency using a management dependencies like Gradle or Maven, or, you can do the download of jar from Maven Central Repository.    

### Class that you need know

The Buster Core Project, have the interfaces `Command` and `Query`. That interfaces you must use in commands and queries. For all commands or queries you need have a Handle, for command you must create a class and implement the `CommandHandle` interface, and for Queries, you must create a class and implement the `QueryHandle` interface.

After that you created the commands, queries, commands handles and queries handle, you can use the interface `CommandBus` to execute commands, and `QueryBus` to execute queries.

The interfaces must be injected by management beans of the quarkus or the spring. For Quarkus, you must use `@Inject` and for Spring you must use `@Autowired`. See the examples bellow.

### Creating a Command

    public class FooCommand implements Command {
    }

### Creating a Query

    public class FooQuery implements Query<Foo> {
    }
    
Look that in a query, you need to specify the typed return.

### Using with Quarkus

#### Command:

Create a Command Handler:

    @ApplicationScoped
    public class FooHandlerCommand implements CommandHandler<FooCommand> {
    
        @Override
        public void apply(FooCommand fooCommand) {
            // Do something
        }
    }
    
Inject the Command Bus:

     @Inject
     private CommandBus commandBus;
     
Execute the Command:

    commandBus.execute(new FooCommand());
    
#### Query

Create a Query Handler:

    @ApplicationScoped
    public class FooQueryHandler implements QueryHandler<FooQuery, String> {
        @Override
        public String apply(FooQuery fooQuery) {
            // Do a query and return something
            return null;
        }
    }

Inject the Query Bus:

     @Inject
     private QueryBus queryBus;
     
Execute the query:

    Foo resul = queryBus.execute(new FooQuery());

### Using with Spring

#### Command:

Create a Command Handler:

    @Component
    public class FooHandlerCommand implements CommandHandler<FooCommand> {
    
        @Override
        public void apply(FooCommand fooCommand) {
            // Do something
        }
    }
    
Inject the Command Bus:

     @Autowired
     private CommandBus commandBus;
     
Execute the Command:

    commandBus.execute(new FooCommand());
    
#### Query

Create a Query Handler:

    @Component
    public class FooQueryHandler implements QueryHandler<FooQuery, String> {
        @Override
        public String apply(FooQuery fooQuery) {
            // Do a query and return something
            return null;
        }
    }

Inject the Query Bus:

     @Autowired
     private QueryBus queryBus;
     
Execute the query:

    Foo resul = queryBus.execute(new FooQuery());

## How can I contribute?

First you need know about the project structure, after you need know about necessary environment, e you need also know about the branch flow, and the pull request, and finally, you can contribute with project creating issues, fix bugs and development new features. 

### Project structure

The Buster Project have a simple structure with maven modules. 

The `buster-core` project is where you find the common classes and interfaces: `Command`, `Query`, `CommandBus`, `QueryBus`, `CommandHandle`, `QueryHandle`, `CommandHandleFactory` and `QueryHandleFactory`.  

The interfaces `Command` and `Query` allows you to create Commands and Queries for you to execute through `CommandBus`, `QueryBus`.

The interfaces `CommandHandle` and `QueryHandle` allows you to create handles to Command and Queries.

The `CommandHandleFactory` and `QueryHandleFactory` are interfaces that must be implemented by a provider that allows you to use in your project through dependency injection.

The `buster-quarkus` project is the implementation for Quarkus Framework. The was development like a quarkus extension, that you can read more about [here](https://quarkus.io/guides/writing-extensions).

The `spring-bustes` project is the implementation for Spring Framework. This is a simple spring boot project, tha uses the `spring.factories` files from `spring-boot-autoconfigure` to provider spring beans.   

### Environment

You need:
    - Maven 3
    - Java 8

### Branch 