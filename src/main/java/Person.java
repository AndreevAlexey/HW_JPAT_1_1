import java.util.Objects;
import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String lastName;
    protected OptionalInt age;
    protected String address;
    // конструктор
    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
    // конструктор
    public Person(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = OptionalInt.of(age);
    }
    // геттеры
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public OptionalInt  getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
    // увеличение возраста на 1
    public void happyBirthday() {
        age = OptionalInt.of(age.isPresent() ? age.getAsInt() + 1 : 1);
    }
    // сеттеры
    public void setAddress(String address) {
        this.address = address;
    }
    // toString
    @Override
    public String toString() {
        return "Person:" +
                "\nname=" + name +
                "\nlastName=" + lastName +
                "\nage=" + age +
                "\naddress='" + address;
    }
    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, getAge(), getAddress());
    }
    // реализация билдера через статический внутренний класс
    public static class PersonBuilder {
        public String name;
        public String lastName;
        public OptionalInt age;
        public String address;
        // конструктор
        public PersonBuilder() {}
        // конструктор
        public PersonBuilder(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
        }
        // сеттеры
        public PersonBuilder setName(String name) {
            if(name.isEmpty()) {
                throw new IllegalArgumentException("Указано пустое значение обязательного поля name");
            }
            this.name = name;
            return this;
        }

        public PersonBuilder setLastName(String lastName) {
            if(lastName.isEmpty()) {
                throw new IllegalArgumentException("Указано пустое значение обязательного поля lastName");
            }
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder setAge(int age) {
            if(age < 0) {
                throw new IllegalArgumentException("Возраст не может быть меньше 0");
            }
            this.age = OptionalInt.of(age);
            return this;
        }

        public PersonBuilder setAddress(String address) {
            if(address.isEmpty()) {
                throw new IllegalArgumentException("Указан пустой адрес для установки");
            }
            this.address = address;
            return this;
        }
        //Метод с возвращающим типом Person для генерации объекта
        public Person build() {
            if(lastName == null) {
                throw new IllegalArgumentException("Не задано значение обязательного поля lastName");
            }
            if(name == null) {
                throw new IllegalArgumentException("Не задано значение обязательного поля name");
            }
            return new Person(this);
        }
    }
    // конструктор по билдеру
    private Person(PersonBuilder builder) {
        name = builder.name;
        lastName = builder.lastName;
        age = builder.age;
        address = builder.address;
    }
    // добавление ребенка
    public PersonBuilder newChildBuilder() {
        PersonBuilder child = new PersonBuilder();
        child.setLastName(this.lastName);
        child.setAddress(this.address);
        return child;
    }
}
