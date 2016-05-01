/* There are a series of 10 bombs about to go off! Diffuse them all! Simple, right?
Link: http://www.codewars.com/kata/54d558c72a5e542c0600060f
*/

//Store the diffuse function that can be seen using console.log(Bomb.diffuse)
var diffuseTemp =Bomb.diffuse;
var i=0;
//Cycle through the bombs
while (i<10) {

  //Diffuse bomb with key that can be seen from console.log(Bomb)
  Bomb.diffuse(42);
  //Re set key to that value
  Bomb.key = 42;
  //Re set the diffuse member variable to contain the original diffuse function
  Bomb.diffuse=diffuseTemp;
  i++;
}