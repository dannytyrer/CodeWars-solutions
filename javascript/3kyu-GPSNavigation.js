/*Navigate the fastest path between points and average speeds on a map
 Link: http://www.codewars.com/kata/52605419be184942d400003d
*/
// complete the function so that it returns the fastest route 
function navigate(numberOfIntersections, roads, start, finish) {

    if(start==finish) {
    
        return [start];
    } else if (finish>=numberOfIntersections) {
    
        return null;
    }
    var graph = initGraph(numberOfIntersections, start, roads);
    var roads = ConvertRoadsToObject(roads);
    return FindShortestPath(start, graph, roads, finish);
}

function FindShortestPath(start, graph, roads, finish) {
    
    //Initialise Variables
    var currentNode       = start;
    var foundShortestPath = 0;
    var currentValue      = 0;
    var testValue         = 0;
    var newValue          = 0;
    var newPath           = [];
    var testPath          = [];
    var currentPath       = [];
    var visitedNodes      = [];
    var returnVal         = [];
    
    //While the shortest path hasnt been determined
    while(!foundShortestPath) {
    
        var currentNeighbours = graph[currentNode].neighbours;
        var currentCost = graph[currentNode].cost;
        var unvisitedNodes = [];
  
        for(var i=0; i<currentNeighbours.length; i++) {
            //if we've visited this node skip it
            if(visitedNodes.indexOf(currentNeighbours[i])<0) {
                //path and distance to test if shortest
                testPath = graph[currentNode].shortestPath.concat(currentNeighbours[i]);
                testValue = roads[""+currentNode+","+currentNeighbours[i]]+currentCost;
                //current shortest path and distance
                currentPath = graph[currentNeighbours[i]].shortestPath;
                currentValue = graph[currentNeighbours[i]].cost;
                //If we haven't seen this node before this must be the shortest path 
                if(currentValue==undefined) {
            
                    newValue = testValue;
                    newPath = testPath;
                } else {     
                    //test if test value and path are the shortest path, if they are replace the old values
                    //if not don't
                    if(testValue<currentValue) {
                    
                        newValue = testValue;
                        newPath = testPath;
                    } else {
                    
                        newValue = currentValue;
                        newPath = currentPath;
                    }
                }
              graph[currentNeighbours[i]].shortestPath = newPath;  
              graph[currentNeighbours[i]].cost = newValue;
              unvisitedNodes.push([currentNeighbours[i], newValue]);
            }
        }
        //Sort unvisited nodes in terms of shortest distance from start
        unvisitedNodes.sort(function(a,b) { return a[1]-b[1];})
        //We've visited the current node
        visitedNodes.push(currentNode);
        //If we've reached the finish point we've found the path
        if (currentNode==finish) {
            
            foundShortestPath = 1;
            returnVal = graph[currentNode].shortestPath;
        //If we havent found the finish point and there are no unvisited nodes
        //in sight, there isn't a route to it so exit
       } else if (unvisitedNodes[0]==undefined) {       
	   
            foundShortestPath = 1;
            returnVal = null;
        } else {
        //Traverse to the unvisited node witht he shortest distance to the start
           currentNode = unvisitedNodes [0][0];
        }
  }
  return returnVal;
}
/*
Make roads more convienient to access
*/
function ConvertRoadsToObject(roads) {
    var newRoads = {};
    for (var i=0; i<roads.length; i++) {
    
      newRoads[""+roads[i].from+","+roads[i].to] = roads[i].drivingTime;
    }
    return newRoads;
}
/*
Get neighbours from roads
*/
function getNeighbours(node, roads) {

    var returnArr = [];
    for(var i=0; i<roads.length; i++) {
      if(roads[i].from==node) {
          returnArr.push(roads[i].to);
      }
    }
    return returnArr;
}
/*
Inititalises the graph structure
*/
function initGraph(numberOfIntersections, start, roads) {
    
    var graph = {};
    var cost;
    var shortestPath;
    for(var i = 0; i<numberOfIntersections; i++) {    
       if(i==start) {
          
          shortestPath = [start];
          cost = 0;
      } else {
         
          shortestPath = [];
          cost = undefined;
      }
      graph[i] = { 'cost' : cost, 'neighbours' : getNeighbours(i,roads), 'shortestPath':shortestPath};
    }
    return graph;
}