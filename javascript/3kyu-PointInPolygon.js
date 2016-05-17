/* Determine if a given point is inside a given polygon
   Link: http://www.codewars.com/kata/point-in-polygon-1/javascript

    Uses raytracing algorithm:
    take a horizontal ray from the point to +ve infinity
    if it intersects a polygon edge an odd number of times
    the point is inside the polygon, otherwise it is outside.
*/
function pointInPoly(poly, point) {

    var startX = point[0];
    var startY = point[1];
    var endY = startY;
    var endX = 14;
    var intersectionCount = 0;
    var nextIndex;
    var lastCoordinate;
    var start = [startX,startY]
    var end = [endX,endY]
    for(var v = 0; v<poly.length;v++) {
  
        nextIndex = v == poly.length-1 ? 0:v+1;
        if(segmentsIntersect(start,end,poly[v],poly[nextIndex])){
        
            intersectionCount++;
        }
    }
    return isOdd(intersectionCount);
}

/*
True if the three points are in counterClockwise order
*/

function counterClockwise(a,b,c) {

    return (c[1]-a[1])*(b[0]-a[0])>(b[1]-a[1])*(c[0]-a[0]);
}

/*
Two line segments ab and cd intersect iff:
   a and b are on the opposite sides of the line cd
   c and d are on opposite sides of the line ab
*/
function segmentsIntersect(a,b,c,d) {

    return (counterClockwise(a,c,d)!=counterClockwise(b,c,d)) &&
           (counterClockwise(a,b,c) != counterClockwise(a,b,d))

}

function isOdd(number) {
    return number%2;
}
