# Cinema Queue

## Problem Statement
You must create a queue where older people always stand in front of younger ones. Additionally, there are two types of tickets: one for adults and one for children. Use polymorphism to differentiate the ticket type for each person, and utilize generics to ensure that this queue only accepts objects that inherit from a `Person` type that you must create.

## Instructions

### 1. Create an Enum `Ticket`
Define an enum `Ticket` with the following available ticket types:
- `ADULT`
- `CHILD`

### 2. Create an Abstract Class `Person`
The `Person` class must:
- Implement the `Comparable` interface.
- Have an abstract method `getTicketType()`.
- Contain the following attributes and methods:

#### Attributes:
- `age` (int)
- `name` (String)

*Note:* Use the `Person` constructor to initialize both attributes.

#### Methods:
- `int compareTo(Person person)`: Implement this method to compare people based on age.
- `String getName()`
- `int getAge()`
- `String toString()`: Return format: `name + ": " + age + "[" + getTicketType() + "]"`

### 3. Create Two Classes Extending `Person`
- `Adult`
- `Child`

Each class must return the appropriate ticket type when implementing `Person`.

### 4. Create the `Queue` Class
The `Queue` class must only accept objects implementing `Person` (using generics). It should have the following attributes and methods:

#### Attributes:
- `people` (ArrayList)

*Note:* Use the constructor to set the initial queue capacity. If no value is provided, default to 10.

#### Methods:
- `void push(T person)`: Ensure older individuals are always placed before younger ones.
- `T pop()`
- `boolean isEmpty()`

*Note:* The goal is not to optimize the push operation. It is recommended to insert elements into the array and then sort it in reverse order. The Java `Collections` class can simplify this process!