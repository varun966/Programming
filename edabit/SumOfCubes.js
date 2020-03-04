
var arr = [1,2,3,4,5];

function sumOfCubes(arr) {

    for(var i =0;i<arr.length;i++)
    {

      arr[i] = Math.pow(arr[i],3);
    }
        const reducer = (accumulator, currentValue) => accumulator + currentValue;

    return arr.reduce(reducer);
    

}

console.log(sumOfCubes(arr));