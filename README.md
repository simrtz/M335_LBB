# TriviaQuiz

## Linter

- For the linter the checkstyle plugin was used. To run it in Android Studio go to File -> Settings -> plugins -> install checkstyle -> restart IDE

- Then go to File -Settings -> tools -> checkstyle and select the lint_config.xml

- Now you can select the pencil in the left sidebar and run the linter.

## Device
For testing a Samsung S20 FE with Android Version 12 was used.

## Repo
https://github.com/simrtz/M335_LBB <p>
-> Select branch Master

## Changes to Planning
- Buttons change color completely when selected/revealed as answer instead of just adding a border
- QuestionService class was removed, since it was unneeded. The Activities communicate with the TriviaService directly now
- the onShake Method is called hearShake because of the 'seismic' library which was used to detect shakes
- A lot of private Methods were added to structure the code better and improve code style.