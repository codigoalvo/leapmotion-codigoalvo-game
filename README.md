leapmotion-codigoalvo-game (Java)
==========================

My first LeapMotion Java Game.
It's about grabing a object with a specific color and drag
into objects from same color avoiding colide with diferent ones.
It's on initial stage and I'm doing it from scratch using Java2D and Leap SDK only.
At some point I will put gravitational forces on game to
change direction from objects and some inteligence on object creation.

Cassio Reinaldo Amaral
www.codigoalvo.com.br
codigoalvo@gmail.com


Updates:

28/02/2013
 - implements mouse usage instead from LEAP when device isn't connected;
 - fix a bug where collisions was verified even when cursor wasn't visible;
 - implements a debug msg visible on screen on bottom left corner;
 - implements distance between 2 objects check for future magnectic field phisics;
 - change some hardcoded data to variables in main board;

13/03/2013
 - code for magnetic field objects. not in action yet.


Future features

 - randon magnetic fields to change object direction/speed;
 - better new object creation positioning system instead from random;
 - objects visual effects (shadow, trail,,, ???);
 - screen resolution selecion before game start; 
 
 Known bugs:
 
 - screen is blinking due to Graphics usage through repaint.
   sried to use BufferStrategy but didn't worked for this project.
   I will revisit this issue in the future.
 - only works with one finger and didn't works with tools for now.
