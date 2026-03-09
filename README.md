# Need for Java

_Built: June 2022_

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
| Easy     | 10–15 km       | 12            | 100%          |
| Moderate | 15–30 km       | 24            | 80%           |
| Hard     | 30–50 km       | 45            | 50%           |

## Project Structure

```
NeedForJava/
├── Game.java           # Main class — entry point and game loop
├── Highway.java        # Highway structure (3 lanes)
├── Lane.java           # Individual lane with random obstacle placement
├── Player.java         # Player data and vehicle loading
├── Vehicle.java        # Vehicle stats and state
├── PlayerPosition.java # 2D position (lane, cell)
├── Message.java        # Narrative text and dynamic NPC dialogue
├── UserInput.java      # Validated console input utilities
├── Random.java         # Random number utility
├── vehicles.txt        # Vehicle configuration data
├── classDiagram.txt    # Mermaid class diagram
└── docs/               # Generated JavaDoc HTML documentation
```

## Requirements

- Java Development Kit (JDK) 8 or later
- `vehicles.txt` must be present in the working directory at runtime

## Build & Run

```bash
# Compile
javac Game.java Highway.java Lane.java Player.java Vehicle.java \
      Message.java UserInput.java PlayerPosition.java Random.java

# Run
java Game
```

Game results are written to `output.txt` after each session.

## Documentation

JavaDoc HTML documentation is available in the `docs/` directory — open `docs/index.html` in a browser.

A Mermaid class diagram showing the full class structure is in `classDiagram.txt`.

\*\*README generated using Claude Code
