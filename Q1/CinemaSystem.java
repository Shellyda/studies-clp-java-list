
import java.util.ArrayList;

enum Ticket {
    ADULT, CHILD
}

abstract class Person implements Comparable<Person> {

    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract Ticket getTicketType();

    public int getAge() {
        return this.age;
    }

    @Override
    public int compareTo(Person p) {
        return this.age - p.age;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.age + "[" + getTicketType() + "]";
    }
}

class Adult extends Person {

    public Adult(String name, int age) {
        super(name, age);
    }

    @Override
    public Ticket getTicketType() {
        return Ticket.ADULT;
    }
}

class Child extends Person {

    public Child(String name, int age) {
        super(name, age);
    }

    @Override
    public Ticket getTicketType() {
        return Ticket.CHILD;
    }
}

class Queue {

    public ArrayList<Person> people;
    private int capacity = 10;

    public Queue(int capacity) {
        this.people = new ArrayList<>();
        this.capacity = capacity;
    }

    public Boolean isEmpty() {
        return this.people.isEmpty();
    }

    public void push(Person person) {
        if (this.people.size() == this.capacity) {
            throw new RuntimeException("Queue is full");
        }

        int i = 0;
        while (i < this.people.size() && this.people.get(i).compareTo(person) > 0) {
            i++;
        }

        this.people.add(i, person);
    }

    public void clear() {
        this.people.clear();
    }

    public Person pop() {
        if (isEmpty()) {
            return null;
        }

        return this.people.remove(0);
    }
}

public class CinemaSystem {

    public static void main(String[] args) {
        Queue queue = new Queue(5);

        queue.push(new Adult("John", 30));
        queue.push(new Child("Alice", 10));
        queue.push(new Adult("Bob", 40));
        queue.push(new Child("Eve", 5));
        queue.push(new Adult("Charlie", 50));

        while (!queue.isEmpty()) {
            Person p = queue.pop();
            System.out.println(p);
        }
    }
}
