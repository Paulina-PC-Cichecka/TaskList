# TaskList

**TaskList** is a small Java graphical application created for educational purposes - to show a way of using Future and **practice multithreading**. It displays a static background and a simple frame-based animation (e.g., an animated cat using multiple images). The project is built with standard Java libraries (AWT/Swing), using a simple game loop structure.

## Features

- Displays a background image using a custom panel
- Plays a simple animation by cycling through multiple images
- Uses a custom font for rendering text
- Basic application structure with separation into game loop and rendering components
- Use of Java Future for tracking game logic (to manage the state of falling cats in the application. Each falling cat can be associated with a Future, which provides information about its status — for example, whether it has landed or is still falling. Also, every cat (thread) can be cancelled by corresponding Future)

## Requirements

- Java 8 or newer
