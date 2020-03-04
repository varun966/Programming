str = "varun";

function reverseCapitalize(str) {

     str = str.split('').reverse().join('') ;
     str.toUpperCase();

     return str;
	
}

console.log(reverseCapitalize(str));

