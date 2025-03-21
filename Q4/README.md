# Monster Behavior System 

## Introduction
Congratulations on your first day as an intern at Quadrado da Fênix! Your team is responsible for implementing the monster behavior system in the upcoming game *Heroes of the Crystal 3*. Your task is to design and develop the monster attack mechanics based on their species, colors, and special attack effects.

## Requirements

### Monster Classes
There are three main monster classes:
- **Dragon**
- **Rat**
- **Wolf**

Each turn, a monster should:
1. Check if it is alive.
2. If alive, perform an attack.
3. If the attack includes poison, fear, or bleeding effects, apply the respective status before attacking.

### Monster Variants by Color
Each monster has a color variant that affects its behavior:

- **Blue**:
  - **Dragon**: Increases HP by 25%.
  - **Rat**: Poisons the team on the next attack.
  - **Wolf**: Performs a charge attack during the first two turns.

- **Green**:
  - **Dragon**: Increases attack power by 20%.
  - **Rat**: Attacks first at the beginning of combat.
  - **Wolf**: Performs a bite attack during the first two turns.

- **Yellow**:
  - **Dragon**: Attacks twice per turn.
  - **Rat**: Can terrify the team on the next attack.
  - **Wolf**: Performs a scratch attack during the first two turns.

- **Purple**:
  - **Dragon**: Increases attack power by 50% but reduces HP by 25%.
  - **Rat**: Attacks three times per turn.
  - **Wolf**: Performs a sequence of attacks (bite, charge, scratch) during the first three turns.

### Common Attack Types
Each monster class has a set of common attacks with specific effects:

- **Dragon**:
  - **Bite**: Causes bleeding.
  - **Scratch**: Deals damage equal to 50 * monster level.
  - **Charge**: Increases attack power by 10% (stacking up to 50%).

- **Rat**:
  - **Bite**: Poisons the team.
  - **Scratch**: Deals damage equal to 10 * monster level.
  - **Charge**: Increases speed, allowing two attacks per turn.

- **Wolf**:
  - **Bite**: Causes fear.
  - **Scratch**: Removes 20% of a team member's HP.
  - **Charge**: Increases attack power by 10% (stacking up to 100%).

## Class Design

### `Monster` (Base Class)
**Attributes:**
- `health: Double` - Monster's health; when it reaches zero, the monster dies.
- `status: String` - Can be "poisoned", "bleeding", "terrified", or "attacking".
- `attackPower: Double` - Damage dealt by the monster.
- `level: Integer` - Determines the scaling of the monster's power.

**Methods:**
- `isAlive(): Boolean` - Checks if the monster is still alive.
- `bite(): void` - Common attack that applies effects based on the monster's status.
- `scratch(): void` - Common attack.
- `charge(): void` - Common attack.
- `bleed(): void` - Changes the monster's status to "bleeding".
- `terrify(): void` - Changes the monster's status to "terrified".
- `poison(): void` - Changes the monster's status to "poisoned".

### `ColoredMonster` (Color Behavior)
**Methods:**
- `turnBlue(): void`
- `turnGreen(): void`
- `turnYellow(): void`
- `turnPurple(): void`

### `Dummy` (Player Team Simulation)
**Attributes:**
- `status: String` - "normal", "poisoned", "bleeding", or "terrified".
- `health: Double` - Use `java.lang.Double.MAX_VALUE`.

**Methods:**
- `reset(): void` - Resets `Dummy`'s status and health.

## Implementation Details
### Monster-Specific Parameters

- **Dragon**
  - `health = 9999 + (level / 2)`
  - `attackPower = 999 * sqrt(level)`

- **Rat**
  - `health = 1000 + level`
  - `attackPower = 100 + (level^2)`

- **Wolf**
  - `health = 5000 + (level^2)`
  - `attackPower = 500 + level`

## Game Loop Example
```java
class Game {
    private static Integer turn;

    public static void main(String[] args) {
        // Create a new instance of Dummy
        Dummy player = new Dummy();
        
        // Generate a new monster with a random color
        Monster monster = generateRandomMonster();
        
        // Simulate a battle for up to 10 turns
        for (turn = 1; turn <= 10; turn++) {
            if (!monster.isAlive()) break;
            
            // Monster attacks, respecting its restrictions
            monster.attack();
            
            // Reduce monster's health by 10%
            monster.reduceHealthByPercentage(10);
            
            // If the monster dies, exit loop
            if (!monster.isAlive()) break;
            
            // Reset Dummy's state
            player.reset();
        }
    }
}
```

### Additional Considerations
- Monsters should randomly choose an attack unless restricted by their color/class.
- Use Java's `Random` class to determine the monster's color and attacks.
- Ensure every generated monster has at least one color assigned.

## Conclusion
This system provides a dynamic and flexible monster behavior implementation, incorporating inheritance, state management, and combat mechanics. By following these guidelines, you will ensure that each monster type behaves as expected in *Heroes of the Crystal 3*.


