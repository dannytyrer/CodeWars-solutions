/*Write a function defaultArguments. It takes a function as an argument, along with an object containing default values for that function's arguments, and returns another function which defaults to the right values.
  Link: http://www.codewars.com/kata/52605419be184942d400003d
*/

function defaultArguments(func, params) {
  //Convert the function to string
  var funcString = func.toString();
  //Find start and end of arg list as indexOf returns the first index found
  //we know that this is the arg list
  var argStart = funcString.indexOf("(");
  var argEnd = funcString.indexOf(")");
  //Extracts the arg list from the function string
  var args = funcString.substring(argStart+1, argEnd);
  //Removes comments for the argument input
  args=args.replace(/(\/\*([\s\S]*?)\*\/)|(\/\/(.*)$)/gm, '');
  args=args.replace(/\r?\n|\r/g,'');
  args=args.replace(/\s\s+/g, ' ' );
  args=args.trim();
  
  
  var argNames = args.split(', ');
  //For wrapper to confine funcs scope
  var wrapper = function () {
          
      return func();
  };
  //Convert to string for manipulation
  var wrapperString = wrapper.toString();
  
  //find start of function body
  var indexOpen = wrapperString.indexOf("{");
  
  //Extract relevant parts of the function
  var funcStart = wrapperString.substring(0, wrapperString.indexOf("(")+1);
  var funcEnd = wrapperString.substring(indexOpen+1,wrapperString.length-1);
  //Initialise defaults object
  var addString1 = "var defaults = "+JSON.stringify(params)+";\n";
  //Cycle through arg names add the conditions that if it is unspecified then use the one specified
  //in the details object
  for (var i =0 ;i<argNames.length;i++) {
      
      if(argNames[i]!=""){
          addString1 = addString1 + argNames[i]+"="+argNames[i]+"==undefined&&Object.keys(arguments)["+i+"]==undefined?defaults[\""+argNames[i]+"\"]:"+argNames[i]+";\n"; 
      }
  }
  //Put the now known args into func arg list
  funcEnd=funcEnd.replace("func()", "func("+args+")");
  //Put all the parts together and eva
  eval("var newFunc ="+funcStart+args+")"+"{"+addString1+funcEnd+"}"); 
  return newFunc;
}