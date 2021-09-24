
console.log('author library called');

(function($, Coral) {
	"use strict";

	console.log('start');

    var registry = $(window).adaptTo("foundation-registry");


	 registry.register("foundation.validation.validator", {
        selector: "[data-validation=moderna-multifield]",
        validate: function(element) {
            var el = $(element);
			let max = el.data("max-items");
            let min = el.data("min-items");
            let items = el.children("coral-multifield-item").length;
            let domitems = el.children("coral-multifield-item");
            console.log("{} : {} : {}",max,min,items);
            if(items>max) {
                domitems.last().remove();
                return "You can add only "+max+" books. You are trying to add "+items+"th book.";
            }
            if(items<min) {
				return "You have to add min of "+min+" books.";
            }
        }
    });

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=txt-validation]",
        validate: function(element) {
            let el = $(element);
            let pattern=/[0-9]/;
            //let pattern = /[a-zA-Z]+/;
            let value=el.val();
            if(pattern.test(value)){

               return "Please add only Alphabets";
            }
            else if(value.length == 0) {
				return "Please add text";
            }

        }
    });

    registry.register("foundation.validation.validator", {
            selector: "[data-validation=email-validation]",
            validate: function(element) {
                let el = $(element);
                //let pattern=/[0-9]/;
                //let pattern = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
                let pattern = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
                let value=el.val();
              //  console.log("value: "+value+" pattern: "+pattern);
                console.log(pattern.test(value)?"Yes":"no"); 
                if(!pattern.test(value)){
                     console.log("phne"); 
                   return "Please enter valid email id";
                }
                else if(value.length == 0) {
                    return "Please add phone number";
                }
    
            }
        });

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=phn-validation]",
        validate: function(element) {
            let el = $(element);
            //let pattern=/[0-9]/;
            //let pattern = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
			let pattern = /^\+?([0-9]{2})\)?[-. ]?([0-9]{5})[-. ]?([0-9]{5})$/;
            let value=el.val();
            console.log("value: "+value+" pattern: "+pattern);
            console.log(pattern.test(value)?"Yes":"no"); 
            if(!pattern.test(value)){
                 console.log("phne"); 
               return "Please enter valid phone number like +XX-XXXXX-XXXXX";
            }
            else if(value.length == 0) {
				return "Please add phone number";
            }

        }
    });

    console.log('end');

})(jQuery, Coral);