arr =[1, 2, 3, 4, 5];

function transform(arr) {

    for(var i =0;i<arr.length;i++)
    {
        if(arr[i]%2==0)
        {
           arr[i]=arr[i]-1;
        }
        else 
        {
          arr[i]=arr[i]+1;
        }

    }

    return arr;
	
}

console.log(transform(arr));