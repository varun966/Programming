//[45, 3, 0]
//[-2, 84, 23]

const arr = [-2, 84, 23];

var sum = 0;

function getSumOfItems(arr) {
	
    for(var i = 0; i<arr.length ; i++)
    {
        sum = sum + arr[i];
    }

    return sum;
}

console.log(getSumOfItems(arr));