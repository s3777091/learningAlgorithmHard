/*
 * This class represent a person
 */
class Person {
  String ID;
  int age;

  public Person(String ID, int age) {
    this.ID = ID;
    this.age = age;
  }
}

/*
 * This class represent a queue of people
 */
public class PeopleQueue {
  Person[] people;  // I use an array for this implementation
  int size; // real number of people in the queue
  int MAX_SIZE = 100;

  public PeopleQueue() {
    people = new Person[MAX_SIZE];
    size = 0;
  }

  public void joinQueue(Person p) {
    // if this person age >= 65
    // and the preceding person age < 65, move the preceding people to next next slot
    // until there is another person >= 65 or the first position reached
    if (p.age < 65) {
      people[size] = p;
      size++;
      return;
    }
    int pos = size;
    while (pos > 0) {
      if (people[pos - 1].age < 65) {
        people[pos] = people[pos - 1];
        pos--;
      } else {
        break;
      }
    }
    people[pos] = p;
    size++;
  }

  public Person getVaccine() {
    if (size == 0) {
      return null;
    }
    // Just return the first person and shift other people one step forward
    Person p = people[0];  // save the first person
    for (int i = 0; i < size - 1; i++) {
      people[i] = people[i + 1];
    }
    size--;
    return p;
  }

  public void giveUp(String ID) {
    if (size == 0) {
      return;
    }
    // Find the position of the person whose ID = this ID
    int pos = 0;
    while (pos < size) {
      if (people[pos].ID.equals(ID)) {
        break;
      }
      pos++;
    }
    if (pos == size) {
      // No person found
      return;
    }
    // Shift all people from (pos + 1) one step forward
    for (int i = pos; i < size; i++) {
      people[i] = people[i + 1];
    }
    size--;
  }

  public static void main(String[] args) {
    PeopleQueue queue = new PeopleQueue();
    // enqueue one person whose age = 20 and another whose age = 70
    // the person whose age = 70 should get vaccine first
    queue.joinQueue(new Person("001", 20));
    queue.joinQueue(new Person("002", 70));
    Person p = queue.getVaccine();
    System.out.printf("%s %d\n", p.ID, p.age);

    // enqueue another person whose age = 50
    // the person whose age = 20 should get vaccine now
    queue.joinQueue(new Person("003", 50));
    p = queue.getVaccine();
    System.out.printf("%s %d\n", p.ID, p.age);

    // enqueue one person whose age = 80 and another whose age = 72
    queue.joinQueue(new Person("004", 80));
    queue.joinQueue(new Person("005", 72));

    // the person whose age = 80 should get vaccine now
    p = queue.getVaccine();
    System.out.printf("%s %d\n", p.ID, p.age);

    // the person whose age = 72 giveUp
    // so, the person whose age = 50 should get vaccine now
    queue.giveUp("005");
    p = queue.getVaccine();
    System.out.printf("%s %d\n", p.ID, p.age);
  }
}