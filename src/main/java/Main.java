public class Main {
    public static void main(String[] args) {
        // примеры использования
        Person mother = new Person.PersonBuilder()
                .setName("Анна")
                .setLastName("Вольф")
                .setAge(31)
                .setAddress("Сидней")
                .build();

        Person son = mother.newChildBuilder()
                .setName("Антошка")
                .setAge(7)
                .build();

        System.out.println("У " + mother + " есть сын, " + son);
        try {
            // Не хватает обязательных полей
            new Person.PersonBuilder().build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        try {
            // Возраст недопустимый
            new Person.PersonBuilder().setAge(-100).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
