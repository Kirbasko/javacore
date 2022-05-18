package lessonOneDot2;

import java.util.Arrays;

public class Team {
    private String nameTeam = "Winners!";
    Animal[] team = {new Cat("Kotan", "red", 1), new Turtle("Lolita", "green", 90),
            new Wolf("Volchara", "grey", 3),
            new Wolf("Bezumniy", "grey", 5)};

    // Вывод информации о членах команды
    public void teamInfo() {
        System.out.println("Название: " + nameTeam);
        for (int i = 0; i < 4; i++) {
            System.out.println(team[i]);
        }

        }
    }

