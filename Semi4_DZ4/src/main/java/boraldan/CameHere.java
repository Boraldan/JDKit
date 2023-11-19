package boraldan;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
public class CameHere {
    private List<Worker> workerList;
    private static int counterId;

    public CameHere() {
        this.workerList = new ArrayList<>();
        workerList.add(new Worker(++counterId, 11111, "Tom", 5));
        workerList.add(new Worker(++counterId, 22222, "Паша", 1));
        workerList.add(new Worker(++counterId, 33333, "Валя", 80));
    }

    public List<Worker> findSkill(int lvl) {
        List<Worker> skill = new ArrayList<>();
        for (Worker el : workerList) {
            if (lvl == el.getSkill()) skill.add(el);
        }
        return skill;
    }

    public List<Worker> findPasha(String name) {
        List<Worker> skill = new ArrayList<>();
        for (Worker el : workerList) {
            if (el.getName().equalsIgnoreCase(name)) skill.add(el);
        }
        return skill;
    }

    public Worker findId(int id) {
        for (Worker el : workerList) {
            if (el.getId() == id) {
                return el;
            }
        }
        return null;
    }

    public void addPasha() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите телефон Паши: ");
        int tel = scanner.nextInt();
        System.out.println("Введите имя Паши: ");
        String na = scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Введите стаж: ");
        int skill = scanner.nextInt();
        workerList.add(new Worker(++counterId, tel, name, skill));

    }

}
