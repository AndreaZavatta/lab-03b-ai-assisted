# Copilot Instructions

When generating code for this repository you must follow the project documentation.

Important context files:

- PRODUCT.md → describes the game rules and required features
- CONTRIBUTING.md → describes coding standards and conventions

Always read these files before generating code.

---

## Development Workflow

You must follow a strict Test Driven Development (TDD) workflow.

Rules:

1. Do NOT implement the full solution at once.
2. Work on ONE method at a time.

For each method follow this cycle:

### Step 1 – Write the test

Generate a minimal unit test describing the expected behavior.

Do NOT implement the method yet.

Stop and wait for user confirmation.

### Step 2 – Implementation

After confirmation, implement the method so that the test passes.

The implementation must be minimal and focused on passing the test.

Do not add unrelated logic.

### Step 3 – Next method

Move to the next method and repeat the process.

---

## Code Quality Requirements

Generated code must follow these rules:

- make all fields private and final where possible
- make all input parameters final
- use meaningful variable and method names
- avoid magic numbers (use constants or enums)
- handle edge cases properly
- split complex logic into small methods

---

## Application Entry Point

The project must also include a `Main` class that allows two players
to play Connect Four in the console.

The Main class must:

- read user input
- alternate players
- display the board after each move
- detect wins and draws
