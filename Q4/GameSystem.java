
import java.util.Random;

interface Colored {

    void applyEffect();
}

interface CommonAttacks {

    void bite();

    void scratch();

    void charge();
}

class Dummy {

    private String status;
    private Double health;

    public Dummy() {
        this.status = "normal";
        this.health = java.lang.Double.MAX_VALUE;
    }

    public void bleed() {
        this.status = "bleeding";
        System.out.println("Dummy is now bleeding.");
    }

    public void terrify() {
        this.status = "terrifying";
        System.out.println("Dummy is now terrifying.");
    }

    public void poison() {
        this.status = "poisoned";
        System.out.println("Dummy is now poisoned.");
    }

    public Double getHealth() {
        return health;
    }

    public String getStatus() {
        return status;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void reset() {
        this.setStatus("normal");
        this.setHealth(java.lang.Double.MAX_VALUE);
    }

    @Override
    public String toString() {
        return String.format("Dummy [Health: %.2f, Status: %s]", health, status);
    }

    public void takeDamage(Double damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0.0;
        }
    }
}

abstract class Monster implements CommonAttacks {

    protected Double health;
    protected String status;
    protected String attackType;
    protected Double attackPower;
    protected Integer attackNumber = 1;
    protected Integer attackingTurnsLimit = 1;
    protected Integer level;
    protected Dummy target;

    public Monster(Double health, Double attackPower, Integer level, Dummy target) {
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
        this.status = "normal";
        this.target = target;
    }

    public Boolean isAlive() {
        return this.health > 0;
    }

    public void applyAttack(String newStatus) {
        if (isAlive()) {
            this.status = newStatus;
            target.takeDamage(attackPower);
            System.out.println(getClass().getSimpleName() + " attacked with " + attackType + "!" + " Damage: " + attackPower);
        }
    }

    @Override
    public String toString() {
        return String.format("%s [Health: %.2f, Status: %s, Attack Power: %.2f, Level: %d]",
                getClass().getSimpleName(), health, status, attackPower, level);
    }

    public void takeDamage(Double damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0.0;
        }
        System.out.println(getClass().getSimpleName() + " took damage! Health: " + this.health);
    }
}

class Blue implements Colored {

    private final Monster monster;

    public Blue(Monster monster) {
        this.monster = monster;
    }

    @Override
    public void applyEffect() {
        if (monster instanceof Dragon) {
            monster.health *= 1.25;
            System.out.println("- Dragon's health increased by 25%.");
        } else if (monster instanceof Rat) {
            monster.status = "poisonous";
            System.out.println("- Rat will poison the team on the next attack.");
        } else if (monster instanceof Wolf) {
            monster.attackType = "charge";
            monster.attackingTurnsLimit = 2;
            System.out.println("- Wolf will charge for the first two turns.");
        }
    }
}

class Green implements Colored {

    private final Monster monster;

    public Green(Monster monster) {
        this.monster = monster;
    }

    @Override
    public void applyEffect() {
        if (monster instanceof Dragon) {
            monster.attackPower *= 1.20;
            System.out.println("- Dragon's attack power increased by 20%.");
        } else if (monster instanceof Rat) {
            System.out.println("- Rat attacks first in battle.");
        } else if (monster instanceof Wolf) {
            monster.attackType = "bite";
            monster.attackingTurnsLimit = 2;
            System.out.println("- Wolf will bite for the first two turns.");
        }
    }
}

class Yellow implements Colored {

    private final Monster monster;

    public Yellow(Monster monster) {
        this.monster = monster;
    }

    @Override
    public void applyEffect() {
        if (monster instanceof Dragon) {
            monster.attackNumber = 2;
            System.out.println("- Dragon will attack 2 times per turn.");
        } else if (monster instanceof Rat) {
            monster.status = "terrifying";
            System.out.println("- Rat will terrify on the next attack.");
        } else if (monster instanceof Wolf) {
            monster.attackType = "scratch";
            monster.attackingTurnsLimit = 2;
            System.out.println("- Wolf will scratch for the first two turns.");
        }
    }
}

class Purple implements Colored {

    private final Monster monster;

    public Purple(Monster monster) {
        this.monster = monster;
    }

    @Override
    public void applyEffect() {
        if (monster instanceof Dragon) {
            monster.attackPower *= 1.50;
            monster.health *= 0.75;
            System.out.println("- Dragon's attack increased by 50% and health decreased by 25%.");
        } else if (monster instanceof Rat) {
            monster.attackNumber = 3;
            System.out.println("- Rat attacks 3 times per turn.");
        } else if (monster instanceof Wolf) {
            monster.attackingTurnsLimit = 3;
            monster.attackType = "bite";
            System.out.println("- Wolf performs a sequence of attacks for 3 turns: bite, charge and scratch.");
        }
    }
}

class Dragon extends Monster {

    public Dragon(Integer level, Dummy target) {
        super(9999.0 + level / 2.0, 999 * Math.sqrt(level), level, target);
    }

    @Override
    public void bite() {
        this.attackType = "bite";
        applyAttack("attacking");
        target.bleed();
    }

    @Override
    public void scratch() {
        this.attackType = "scratch";
        applyAttack("attacking");
        target.takeDamage(50.0 * level);
    }

    @Override
    public void charge() {
        this.attackType = "charge";
        applyAttack("attacking");
        attackPower *= 1.10;
        if (attackPower > 1.50 * (999 * Math.sqrt(level))) {
            attackPower = 1.50 * (999 * Math.sqrt(level));
        }
    }
}

class Rat extends Monster {

    public Rat(Integer level, Dummy target) {
        super(1000.0 + level, 100.0 + Math.pow(level, 2), level, target);
    }

    @Override
    public void bite() {
        this.attackType = "bite";
        applyAttack("attacking");
        target.poison();
    }

    @Override
    public void scratch() {
        this.attackType = "scratch";
        applyAttack("attacking");
        target.takeDamage(10.0 * level);
    }

    @Override
    public void charge() {
        this.attackType = "charge";
        applyAttack("attacking");
        attackPower *= 1.10;
    }
}

class Wolf extends Monster {

    public Wolf(Integer level, Dummy target) {
        super(5000.0 + Math.pow(level, 2), 500.0 + level, level, target);
    }

    @Override
    public void bite() {
        this.attackType = "bite";
        applyAttack("attacking");
        target.terrify();
    }

    @Override
    public void scratch() {
        this.attackType = "scratch";
        applyAttack("attacking");
        target.takeDamage(target.getHealth() * 0.20);
    }

    @Override
    public void charge() {
        this.attackType = "charge";
        applyAttack("attacking");
        attackPower *= 1.10;
    }
}

public class GameSystem {

    public static void main(String[] args) {
        Random random = new Random();
        Dummy dummy = new Dummy();
        Integer level = random.nextInt(10) + 1;

        String[] colors = {"blue", "green", "yellow", "purple"};
        String color = colors[random.nextInt(colors.length)];

        String[] monsters = {"dragon", "rat", "wolf"};
        String monsterType = monsters[random.nextInt(monsters.length)];

        Monster monster = switch (monsterType) {
            case "dragon" ->
                new Dragon(level, dummy);
            case "rat" ->
                new Rat(level, dummy);
            default ->
                new Wolf(level, dummy);
        };

        Colored effect = switch (color) {
            case "blue" ->
                new Blue(monster);
            case "green" ->
                new Green(monster);
            case "yellow" ->
                new Yellow(monster);
            default ->
                new Purple(monster);
        };
        System.out.println("\n-------------------------");
        System.out.println("- Dummy initial status: " + dummy + "\n");
        System.out.println("- Monster initial status: " + monster);
        System.out.println("- Monster Color: " + color.toUpperCase());
        effect.applyEffect();

        System.out.println("\nBattle Start!");

        for (int turn = 1; turn <= 10; turn++) {
            System.out.println("\nTurn " + turn);

            if (!monster.isAlive()) {
                System.out.println("\nThe monster has been defeated!");
                return;
            }

            if (monster instanceof Wolf && color.equals("purple") && turn <= 3) {
                switch (turn) {
                    case 1 ->
                        monster.bite();
                    case 2 ->
                        monster.charge();
                    case 3 ->
                        monster.scratch();
                }
            } else if (turn == 1 && monster instanceof Rat && !(color.equals("green") || color.equals("purple"))) {
                switch (color) {
                    case "blue" ->
                        dummy.poison();
                    case "yellow" ->
                        dummy.terrify();
                }
            } else if (turn <= monster.attackingTurnsLimit) {
                for (int i = 0; i < monster.attackNumber; i++) {
                    String[] attacks = {"bite", "charge", "scratch"};
                    String attackType = monster.attackType != null ? monster.attackType : attacks[random.nextInt(attacks.length)];
                    switch (attackType) {
                        case "bite" ->
                            monster.bite();
                        case "scratch" ->
                            monster.scratch();
                        default ->
                            monster.charge();
                    }
                }
            } else {
                int randomAttack = random.nextInt(3);
                switch (randomAttack) {
                    case 0 ->
                        monster.bite();
                    case 1 ->
                        monster.scratch();
                    default ->
                        monster.charge();
                }

            }

            monster.takeDamage(monster.health * 0.10);

            dummy.reset();
        }

        System.out.println("\nMonster defeated! Battle Ended!");
        System.out.println("\n-------------------------");
    }
}
