console.log('library called');

(function($, Coral) {
    "use strict";

    console.log('start');

    var registry = $(window).adaptTo("foundation-registry");


    registry.register("foundation.validation.validator", {
        selector: "[data-validation=lawfirm-multifield]",
        validate: function(element) {
            var el = $(element);
            let max = el.data("max-items");
            let min = el.data("min-items");
            let items = el.children("coral-multifield-item").length;
            let domitems = el.children("coral-multifield-item");
            console.log("{} : {} : {}", max, min, items);
            if (items > max) {
                //domitems.last().remove(); //this line removes item if it exceeds the max value
                return "You can add only " + max + " frames. You are trying to add " + items + " frames.";
            }
            if (items < min) {
                return "You have to add min of " + min + " frame.";
            }
        }
    });

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=desc-validation]",
        validate: function(element) {
            let el = $(element);
            //let pattern=/[0-9]/;
            let pattern = /^[A-Za-z .,']+$/;
            let value = el.val();
            if (!pattern.test(value)) {
                return "Alphabets, Whitespace, Full stop, Comma and Single Quotation marks are only allowed.";
            } else if (value.length == 0) {
                return "Can not be empty";
            }

        }
    });

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=heading-validation]",
        validate: function(element) {
            let el = $(element);
            //let pattern=/[0-9]/;
            let pattern = /^[A-Za-z ]+$/;
            let value = el.val();
            if (!pattern.test(value)) {
                return "Alphabets and Whitespacea are only allowed.";
            } else if (value.length == 0) {
                return "Can not be empty";
            }

        }
    });


    registry.register("foundation.validation.validator", {
        selector: "[data-validation=txt-validation]",
        validate: function(element) {
            let el = $(element);
            //let pattern=/[0-9]/;
            let pattern = /^[A-Za-z .]+$/;
            let value = el.val();
            if (!pattern.test(value)) {
                return "Alphabets, Whitespace and Full stop are only allowed.";
            } else if (value.length == 0) {
                return "Can not be empty";
            }

        }
    });

    
    console.log('end');

})(jQuery, Coral);