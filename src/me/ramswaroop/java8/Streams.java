package me.ramswaroop.java8;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @author ramswaroop
 * @version 02/02/2017
 */
class Trader {

    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}

class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}

public class Streams {

    static final Trader raoul = new Trader("Raoul", "Cambridge");
    static final Trader mario = new Trader("Mario", "Milan");
    static final Trader alan = new Trader("Alan", "Cambridge");
    static final Trader brian = new Trader("Brian", "Cambridge");

    static final List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static List<Transaction> getTransactionsIn2011SortedByValue() {
        return transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
    }

    public static List<String> findUniqueCities() {
        return transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());
    }

    public static List<Trader> getAllTradersFromCambridgeAndSortByName() {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(traders -> traders.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
    }

    public static List<String> getAllTraderNamesAndSortByName() {
        return transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(toList());
    }
    
    public static boolean areAnyTradersFromMilan() {
        return transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
    }

    public static void main(String[] a) {
        System.out.println(getTransactionsIn2011SortedByValue());
        System.out.println(findUniqueCities());
        System.out.println(getAllTradersFromCambridgeAndSortByName());
        System.out.println(getAllTraderNamesAndSortByName());
        System.out.println(areAnyTradersFromMilan());
    }
}