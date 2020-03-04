function nSidedShape(n) {
	
let arr = ["circle","semicircle","triangle","square","pentagon","hexagon","heptagon","octagon","nanogon","decagon"];

if ( n ==1 )

    return arr[0];

else if ( n == 2)

    return arr[1];

else 

    return arr[2];
}

console.log(nSidedShape(10));