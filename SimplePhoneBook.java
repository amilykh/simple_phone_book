import java.util.*;

/**
 * Реализуйте структуру телефонной книги с помощью HashMap.
 * Программа также должна учитывать, что во входной структуре будут
 * повторяющиеся имена с разными телефонами, их необходимо считать,
 * как одного человека с разными телефонами. Вывод должен быть
 * отсортирован по убыванию числа телефонов.
 */
public class SimplePhoneBook {
    private final HashMap<String, List<String>> phoneBookMap;

    public SimplePhoneBook() {
        this.phoneBookMap = new HashMap<>();
    }

    /**
     * Adding a subscriber's phone number.
     *
     * @param name the name of the subscriber
     * @param phoneNumber subscriber's phone number
     */
    public void addPhone(String name, String phoneNumber) {
        phoneBookMap.computeIfAbsent(name,
                k -> new ArrayList<>()).add(phoneNumber);
    }

    /**
     * Output of all phone numbers of a specific subscriber
     *
     * @param name the name of the subscriber
     */
    public void getUserPhones(String name) {
        System.out.printf("\nPhone numbers of the subscriber %s:\n", name);
        List<String> listPoneNumbers = phoneBookMap.getOrDefault(name, new ArrayList<>());
        System.out.println(name + "\t" + listPoneNumbers);
    }

    /**
     * Output of all subscribers, sorted in descending order of the number of phone numbers.
     */
    public void showAllPhones() {
        System.out.println("\nOutput of all subscribers, sorted in descending order of the number of phone numbers:");

        List<Map.Entry<String, List<String>>> list = new ArrayList<>(phoneBookMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, List<String>>>() {
            @Override
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                return o1.getValue().size() - o2.getValue().size();
            }
        });

        Collections.reverse(list);

        for (Map.Entry<String, List<String>> e: list) {
            System.out.println(e.getKey() + "\t" + e.getValue());
        }
    }

    public static void main(String[] args) {
        SimplePhoneBook phoneBook = new SimplePhoneBook();

        phoneBook.addPhone("User1", "+79051111111");
        phoneBook.addPhone("User1", "+79772222222");
        phoneBook.addPhone("User2", "+79163333333");
        phoneBook.addPhone("User3", "+79054444444");
        phoneBook.addPhone("User3", "+79775555555");
        phoneBook.addPhone("User3", "+79776666666");
        phoneBook.addPhone("User4", "+79057777777");
        phoneBook.addPhone("User5", "+79058888888");
        phoneBook.addPhone("User5", "+79779999999");

        phoneBook.getUserPhones("User1");
        phoneBook.getUserPhones("User2");
        phoneBook.getUserPhones("User3");

        phoneBook.showAllPhones();
    }
}
