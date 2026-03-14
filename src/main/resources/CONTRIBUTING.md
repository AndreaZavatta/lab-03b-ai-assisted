# CONTRIBUTING.md

## Coding Standards

All Java code in this repository must follow these rules:

- fields must be `private`
- fields should be `final` where possible
- method parameters should be `final`
- avoid mutable state when possible
- use meaningful and descriptive names

Example:

Bad:
int x;
Good:
private final int boardWidth;

---

## Clean Code Rules

Code must follow clean code principles:

- methods should be small and focused
- each method should do only one thing
- avoid deep nesting
- avoid magic numbers
- prefer constants or enums

---

## Error Handling

Invalid inputs must be handled properly.

Examples:

- invalid column index
- full column
- illegal game state

Error messages must clearly explain the problem.

---

## Testing

All game logic must be covered by unit tests.

Tests should be:

- small
- deterministic
- easy to understand

Use **JUnit** for unit testing.

Tests should verify:

- move validation
- win detection
- board state updates
