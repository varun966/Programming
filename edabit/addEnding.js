
var arr=["varun","sai","tangalla"];

var ending="ly";

function addEnding(arr, ending) {


    for(var i = 0;i<arr.length;i++)
    {

        arr[i]= arr[i] + ending;



    }

    return arr;

	
}

console.log(addEnding(arr,ending));