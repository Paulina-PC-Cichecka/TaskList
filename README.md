# TaskList

**TaskList** is a small Java graphical application created for educational purposes - to show a way of using Future and **practice multithreading**. It displays a static background and a simple frame-based animation (e.g., an animated cat using multiple images). The project is built with standard Java libraries (AWT/Swing), using a simple game loop structure.

## Important

The original commit history is unavailable because this project was extracted from a larger academic repository that included multiple unrelated assignments.

## Features

- Displays a background image using a custom panel
- Plays a simple animation by cycling through multiple images
- Uses a custom font for rendering text
- Basic application structure with separation into game loop and rendering components
- Use of Java Future for tracking game logic (to manage the state of falling cats in the application. Each falling cat can be associated with a Future, which provides information about its status — for example, whether it has landed or is still falling. Also, every cat (thread) can be cancelled by corresponding Future)

Gameplay:

![gameplay](https://github.com/user-attachments/assets/9d4bb4a5-d498-4a76-85eb-3de560cbf975)

Multithreaded task interactions:

![taksList](https://github.com/user-attachments/assets/e696b900-8a0f-44fa-a7ab-ec384f91db6c)


## Requirements

- Java 8 or newer
