console.log('client library called');

//to test whether client libs was getting loaded.
$(document).on("foundation-contentloaded", function(e) {
	var container = e.target;
    console.log('foundation-contentloaded was fired.');
	console.log(container);
     console.log('foundation-contentloaded end.');
});


/*
$(window).adaptTo("foundation-registry").register("foundation.validation.validator",{
    selector: "[]"
});
*/

/*below code is to check user input should match value 
at "/apps/Moderna/components/content/custom-components/testing-validation/cq:dialog/content/items/column/items/title/granite:data"
*/
$(window).adaptTo("foundation-registry").register("foundation.validation.validator", {
  selector: "[data-pattern]",
  validate: function(el) {
    var shouldContain = el.getAttribute("data-pattern");  //aem

    console.log('validating text contains aem');
    console.log('input should contain ' + shouldContain);

    var input = el.value;  //input added by author

    if (input.indexOf(shouldContain) === -1 ) {
      return "The field should contain " + shouldContain + ". It's current value is " + el.value + ".";
    }
  }
});

