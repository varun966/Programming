var arr =["Joe","Angela","Frank",""];

function greetPeople(arr) {

    for(var i = 0;i<arr.length;i++)
    {
        if(arr[i]=='')
        {
            break;
        }
        else 
        {
            arr[i]='Hello'+' '+arr[i];
        }
    }

    return arr;
	
}

console.log(greetPeople(arr));