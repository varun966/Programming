
var arr =["goku","varun","mani"];


function wordLengths(arr) {

   for(var i=0;i<arr.length;i++)
   {
       arr[i]=arr[i].length;
   }
    return arr;

}

console.log(wordLengths(arr));



 