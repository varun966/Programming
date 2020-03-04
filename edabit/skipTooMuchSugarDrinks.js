var x = ['fanta','water','lemonade','beer','varun'];
//var x = ['a','b','c','d'];

function skipTooMuchSugarDrinks(x){

    if(x.includes('cola')||x.includes('fanta'))
    {
        var a = x.indexOf('cola');
       if(a>=0) x.splice(a,1);
        var b = x.indexOf('fanta');
        if(b>=0) x.splice(b,1);
       return x;
    }

    else 

    return x;


}

console.log(skipTooMuchSugarDrinks(x));