## Project Overview

This project implements a Connect Four game in Java.

The goal of the project is to provide a clean and maintainable implementation
of the Connect Four game logic, with a console-based interface that allows two
players to play against each other.

The project must emphasize:

- clean architecture
- readable code
- well-tested logic

---

## Game Rules

Connect Four is played on a vertical board with 7 columns and 6 rows.

Players take turns dropping discs into one of the columns.

A disc always occupies the lowest available position in the column.

Players alternate turns.

The goal is to connect four discs of the same color in a row.

Winning conditions:

- four discs horizontally
- four discs vertically
- four discs diagonally

If the board becomes full and no player has won, the game ends in a draw.

---

## Core Features

The system must support:

- dropping a disc in a column
- alternating player turns
- detecting a win
- detecting a draw
- validating moves (invalid column / full column)
- displaying the board in the console

---

## Architecture Goals

The project should follow a clean separation of concerns:

- **Model** → game logic
- **Controller** → game flow
- **View** → console interface

Game logic must be independent from the console UI.

---

## Testing

Game logic must be fully tested with unit tests.

Tests should verify:

- valid moves
- invalid moves
- win detection
- draw detection
