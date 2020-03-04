
/* var fruits = ["Banana", "Orange", "Apple", "Mango"];
var a = fruits.indexOf("Apple");

console.log(a); */



 const arr = [-48, -20, 41, 29, -25, -17, -13, 5, 4, -5, 3, -17, 23];

 //[-48, -20, 41, 29, -25, -17, -13, 5, 4, -5, 3, -17, 23]

 //answer->[-48, -19, 43, 32, -21, -12, -7, 12, 12, 4, 13, -6, 35]

 //[-1, -3, -20, 13, 19, -12, 15, 8, -49, 27, -21, 17, 41, 17, 5, -45, -33]

 //answer->[-1, -2, -18, 16, 23, -7, 21, 15, -41, 36, -11, 28, 53, 30, 19, -30, -17]


function addIndexes(arr) {
	
	
	const maps1 = arr.map(x => x + arr.indexOf(x));
	
	return maps1;
}

console.log(addIndexes(arr)); 

