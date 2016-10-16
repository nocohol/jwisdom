package com.caronic.jwisdom.core.exercise.base.java8.lambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by caronic on 2016/10/16.
 */
public class RosterTest {
    interface CheckPerson {
        boolean test(Person person);
    }

    public static void printPersons(
            List<Person> roster,CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void printPersonWithPredicate(
            List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void processPersons(List<Person> roster,
                                      Predicate<Person> tester,
                                      Consumer<Person> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }

    public static void processPersonsWithFunction(List<Person> roster,
                                                  Predicate<Person> tester,
                                                  Function<Person, String> mapper,
                                                  Consumer<String> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }

    }

    public static <X, Y> void processElements(
            Iterable<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> block
    ) {
        for (X x : source) {
            if (tester.test(x)) {
                Y y = mapper.apply(x);
                block.accept(y);
            }
        }
    }

    public static void main(String[] args) {

        class CheckPersonImpl implements CheckPerson {
            @Override
            public boolean test(Person person) {
                return person.getGender() == Person.Sex.MALE &&
                        person.getAge() >= 18 &&
                        person.getAge() <= 25;
            }
        }

        List<Person> roster = Person.createRoster();

        // Specify search criteria code in a Local class
        RosterTest.printPersons(roster, new CheckPersonImpl());
        // Specify search criteria code in a anonymous class
        RosterTest.printPersons(roster, new CheckPerson() {
            @Override
            public boolean test(Person person) {
                return person.getGender() == Person.Sex.MALE &&
                        person.getAge() >= 18 &&
                        person.getAge() <= 25;
            }
        });
        // Specify search criteria code in a Statement Lambda expression
        RosterTest.printPersons(roster, (Person person)->{
            return person.getGender() == Person.Sex.MALE &&
                    person.getAge() >= 18 &&
                    person.getAge() <= 25;
        });

        // Specify search criteria code in a expression Lambda
        RosterTest.printPersons(roster, (Person person) -> person.getGender() == Person.Sex.MALE &&
                person.getAge() > 18 && person.getAge() <=25);

        System.out.println("Persons who are eligible for Selective Service " +
                "(with Predicate parameter):");
        printPersonWithPredicate(roster, person -> person.getGender() == Person.Sex.MALE &&
                person.getAge() > 18 && person.getAge() <=25);

        System.out.println("Persons who are eligible for Selective Service " +
                "(with Predicate and Consumer parameters):");
        processPersons(roster, person -> person.getGender() == Person.Sex.MALE &&
                person.getAge() > 18 && person.getAge() <=25,
                person -> person.printPerson()
                );
        System.out.println("Persons who are eligible for Selective Service " +
                "(with Predicate, Consumer and Function parameters):");
        processPersonsWithFunction(roster,
                person -> person.getGender() == Person.Sex.MALE &&
                        person.getAge() > 18 && person.getAge() <=25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
                );

        System.out.println("Persons who are eligible for Selective Service " +
                "(generic version):");
        processElements(roster,
                p -> p.getGender() == Person.Sex.MALE &&
                        p.getAge() >= 18 && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email));

        System.out.println("Persons who are eligible for Selective Service " +
                "(with bulk data operations):");
        roster.stream()
                .filter(p -> p.getGender() == Person.Sex.MALE &&
                        p.getAge() >= 18 && p.getAge() <= 25)
                .map(p -> p.getEmailAddress())
                .forEach(email -> System.out.println(email));
    }
}
