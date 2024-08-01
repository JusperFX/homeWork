package humanTree;
import comporators.HumanComparatorByAge;
import comporators.HumanComparatorByName;
import human.Human;
import iterator.HumanIterator;

import java.io.Serializable;
import java.util.*;

public class HumanTree<E extends ItemHumanTree> implements Serializable, Iterable<E> {
    private List<E> humans;

    public HumanTree () {
        humans = new ArrayList<>();
    }

    public void addHuman (E human) {
        humans.add(human);
    }  //добавление человека

    public String getInfoAboutParents(E human) {
        StringBuilder sb = new StringBuilder();
        if (human.getFather() == null && human.getMother() == null) {
            sb.append(human.getName() + " has no information about parents.");
            return sb.toString();
        } else if (human.getFather() == null) {
            sb.append(human.getName() + " has no information about father.");
            sb.append("Mother of " + human.getName() + " " + human.getSurname() + ":\n" + human.getMother() + "\n");
            return sb.toString();
        } else if (human.getMother() == null) {
            sb.append("Father of " + human.getName() + " " + human.getSurname() + ":\n" + human.getFather() + "\n");
            sb.append(human.getName() + " has no information about mother.");
            return sb.toString();
        }
        sb.append("Parents of " + human.getName() + ":\n");
        sb.append("\n");
        sb.append("Father:\n" + human.getFather() + "\n");
        sb.append("Mother:\n" + human.getMother() + "\n");
        return sb.toString();
    } // информация о шнурках

    public String getInfoAboutChildren(E human) {
        StringBuilder sb = new StringBuilder();
        List<? extends ItemHumanTree> children = human.getChildren();
        if (human.getChildren().isEmpty()) {
            sb.append(human.getName() + " doesn`t hava a child.");
            return sb.toString();
        }
        sb.append("Children of " + human.getName() + ": \n");
        int i = 1;
        for (ItemHumanTree child : children) {
            sb.append(i + " child: ");
            sb.append(child + "\n");
            i++;
        }
        return sb.toString();
    }  // информация о детях

    public List<E> getHumans() {
        return humans;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Полное генеалогическое дерево: \n");
        for (E human : humans) {
            sb.append(human);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new HumanIterator<>(humans);
    }  // итератор (пока не использую)

    public void sortByName () {
        System.out.println("Отсортированный по имени список: \n");
        Collections.sort(humans, new HumanComparatorByName<>());
    } // сортировка по имени

    public void sortByAge() {
        System.out.println("Отсортированный по возрасту список: \n");
        Collections.sort(humans, new HumanComparatorByAge<>());
    } // сортировка по возрасту
}
