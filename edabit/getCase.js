str = "varun";

function getCase(str) {

    for(var i =0;i<str.length;i++)
    {
        if(str[i] === str[i].toUpperCase()
        && str[i] !== str[i].toLowerCase()){

        }
        else {

        }
    }

    return str[1];
	
}

console.log(getCase(str));