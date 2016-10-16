package com.caronic.jwisdom.core.exercise.base.java8.lambda;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by caronic on 2016/10/16.
 */
public class MethodReferenceTest {

    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST transferElements(
            SOURCE sourceCollection,
            Supplier<DEST> collectionFactory
    ) {
        DEST result = collectionFactory.get();
        result.addAll(sourceCollection.stream().collect(Collectors.toList()));
        return result;
    }

    public static void main(String[] args) {
        List<Person> roster = Person.createRoster();
        roster.forEach(p -> p.printPerson());

        Person[] rosterArray = roster.toArray(new Person[roster.size()]);

        class PersonAgeComparator implements Comparator<Person> {
            @Override
            public int compare(Person a, Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }

        //Without method reference
        Collections.sort(roster, new PersonAgeComparator());

        // With lambda expression
        Arrays.sort(rosterArray, (Person a, Person b) -> a.getBirthday().compareTo(b.getBirthday()));

        // With method reference
        Arrays.sort(rosterArray, Person::compareByAge);

        // Reference to an instance method of a particular object
        class ComparisonProvider {
            public int compareByName(Person a, Person b) {
                return a.getName().compareTo(b.getName());
            }

            public int compareByAge(Person a, Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }
        ComparisonProvider myComparisonProvider = new ComparisonProvider();
        Arrays.sort(rosterArray, myComparisonProvider::compareByName);

        // Reference to an instance method of a arbitrary object of a particular type
        String[] stringArray = new String[]{"Barbara", "James", "Mary", "John",
                "Patricia", "Robert", "Michael", "Linda"};
        Arrays.sort(stringArray, String::compareTo);

        Set<Person> rosterSetLambda = transferElements(roster, ()->new HashSet<>());
        Set<Person> rosterSet = transferElements(roster, HashSet::new);
        System.out.println("Print rosterSetLambda");
        rosterSetLambda.forEach(p->p.printPerson());

        System.out.println("Print rosterSet");
        rosterSet.stream().filter(p->p.getAge()>25).forEach(p->p.printPerson());
    }

}
