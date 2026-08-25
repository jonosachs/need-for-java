# Need for Java

_Originally built June 2022 as a university assignment. Currently being modernised — Maven build, standard layout, and a characterisation test suite._

A text-based highway escape game written in Java. Navigate a 3-lane highway, dodge obstacles, manage your fuel, and reach the escape point before it's too late.

## Story

You're an undercover cop whose cover has just been blown at a drug deal gone wrong. Now you need to escape across the highway before anyone catches up. Choose your vehicle wisely — your life depends on it.

## Gameplay

The highway is displayed as a scrolling 10-cell viewport across 3 lanes. Each turn you choose an action:

| Action       | Fuel Cost   | Description                               |
| ------------ | ----------- | ----------------------------------------- |
| Move Forward | -1          | Advance one cell                          |
| Swerve Up    | -2          | Move one cell forward + shift up a lane   |
| Swerve Down  | -2          | Move one cell forward + shift down a lane |
| Boost        | -3 per cell | Advance 2–4 cells (depends on vehicle)    |

### Obstacles

| Symbol | Effect                         |
| ------ | ------------------------------ |
| `F`    | Fuel can — restores 10 fuel    |
| `B`    | Roadblock — deals 20 damage    |
| `S`    | Tyre spike — deals 45 damage   |
| `O`    | Open manhole — deals 45 damage |

The first three cells of each lane are always clear, giving you a short runway from the start.

**Win:** Reach the end of the highway.
**Lose:** Run out of fuel or take too much damage.

## Vehicles

Loaded from `vehicles.txt` (format: `Type,BoostSpeed,MaxFuel,MaxDamage`):

| Vehicle       | Boost   | Max Fuel | Max Damage |
| ------------- | ------- | -------- | ---------- |
| Motorcycle    | 4 cells | 100      | 30         |
| Car           | 3 cells | 120      | 50         |
| Garbage Truck | 2 cells | 150      | 100        |

## Difficulty

| Level    | Highway Length | Max Obstacles | Fuel Handicap |
| -------- | -------------- | ------------- | ------------- |
| Easy     | 10–14 km       | 12            | 100%          |
| Moderate | 15–29 km       | 24            | 80%           |
| Hard     | 30–49 km       | 45            | 50%           |

## Known behaviour

These are documented by tests rather than silently fixed, so the suite records what the
game actually does before the design changes:

- **Manholes never spawn.** `Lane.randomObstacle()` draws `nextInt(1, 10)`, whose upper bound is exclusive, so the value mapped to `O` is unreachable.
- **Obstacles appear twice as often as documented.** `Lane.tryObstacle()` draws `nextInt(1, 3)`, giving a 1/2 rate rather than the 1/3 its JavaDoc claims.
- The **Max Obstacles** cap above never binds in practice — every difficulty runs out of highway before reaching it.

## Project Structure

```
NeedForJava/
├── pom.xml                                  # Maven build
├── vehicles.txt                             # Vehicle configuration data
└── src/
    ├── main/java/com/jonosachs/needforjava/
    │   ├── Game.java                        # Entry point, game loop, composition root
    │   ├── Highway.java                     # Highway structure (3 lanes)
    │   ├── Lane.java                        # Single lane with random obstacle placement
    │   ├── Player.java                      # Player data and vehicle loading
    │   ├── Vehicle.java                     # Vehicle stats and state
    │   ├── PlayerPosition.java              # 2D position (lane, cell)
    │   ├── Message.java                     # Narrative text and dynamic NPC dialogue
    │   └── UserInput.java                   # Validated console input utilities
    └── test/java/com/jonosachs/needforjava/
        ├── GameTest.java                    # Boot smoke test
        └── LaneTest.java                    # Characterisation tests
```

`Game` is the composition root — the only class that constructs a `Random`. Every other
class receives one, which keeps obstacle placement reproducible under test.

## Requirements

- JDK 17 or later
- Maven 3.8+
- `vehicles.txt` must be present in the working directory at runtime

## Build & Run

```bash
mvn compile
java -cp target/classes com.jonosachs.needforjava.Game
```

`mvn package` builds a jar, but it has no main-class manifest yet — so run from
`target/classes` rather than with `java -jar`.

Game results are written to `output.txt` after each session.

## Tests

```bash
mvn test
```

These are **characterisation tests**: they record what the code currently does, bugs
included, so the upcoming design changes can be made safely. Randomness is seeded
explicitly so failures are reproducible.

## Documentation

Generate JavaDoc with `mvn javadoc:javadoc`; output lands in `docs/`.
