# CLAUDE.md - Project Guidelines for AI Assistants

## Coding Standard

This project follows the **Free & Fair Coding Standard**.

- Repository: https://github.com/FreeAndFair/CodingStandards
- PDF: https://github.com/FreeAndFair/CodingStandards/releases/download/latest/coding_standards.pdf

## Java Style Summary

### Naming Conventions
- Methods: `inCamelCase`
- Static final constants: `IN_ALL_CAPS_WITH_UNDERSCORES`
- Instance fields: `my_field_name` (snake_case with `my_` prefix)
- Method parameters: `a_name` or `the_name` (snake_case with article prefix)
- Local variables: `variable_name` (snake_case, no prefix)
- Never use uppercase letters in identifiers except for constants and method names

### Formatting
- 2-space indentation (no tabs)
- 100 character line limit
- Spaces around operators and after commas
- Always use braces for control statements

### `final` Usage
- Apply `final` to every method parameter unconditionally
- Mark fields and local variables as `final` when they remain unchanged

### Import Rules
- Never use wildcard imports (`import foo.*`)
- Order: `java.*`, blank line, `javax.*`, blank line, others
- Alphabetical within each group

### Code Organization (in order)
1. Static fields
2. Instance fields
3. Constructors
4. Static methods
5. Instance methods

Within each category, order by visibility: public, protected, package, private

### Documentation
- Complete Javadoc for every class, method, and field
- Non-Javadoc header comments for project ownership and copyright

### Design Principles
- Never declare instance fields as public
- Prefer `protected` over `private` to enable subclassing
- Prefer interfaces over abstract classes
- Methods should do "one thing"
- Separate state-changing from state-reading operations
- If overriding `equals()`, also override `hashCode()`
- Prefer `long` over `int` and `double` over `float`
- Declare local variables only when initial values are known
- Minimize nested conditionals and method complexity
- Replace magic numbers with named constants

### Concurrency
- Declare concurrency properties for all public methods
- Prefer synchronized methods to synchronized blocks
- Use `notifyAll()` instead of `notify()`
- Embed `wait()` calls in while loops

## Project Owner

Joe Kiniry - Free & Fair
