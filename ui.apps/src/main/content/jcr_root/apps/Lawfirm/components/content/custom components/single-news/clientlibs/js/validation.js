

console.log('library called');

(function ($, Coral) {
	"use strict";

	console.log('start');

	var registry = $(window).adaptTo("foundation-registry");

	registry.register("foundation.validation.validator", {
		selector: "[data-validation=desc-validation]",
		validate: function (element) {
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
		validate: function (element) {
			let el = $(element);
			//let pattern=/[0-9]/;
			let pattern = /^[A-Za-z &]+$/;
			let value = el.val();
			if (!pattern.test(value)) {
				return "Alphabets,& and Whitespacea are only allowed.";
			} else if (value.length == 0) {
				return "Can not be empty";
			}

		}
	});


	registry.register("foundation.validation.validator", {
		selector: "[data-validation=txt-validation]",
		validate: function (element) {
			let el = $(element);
			//let pattern=/[0-9]/;
			let pattern = /^[A-Za-z .]+$/;
			let value = el.val();
            if (!pattern.test(value)) {
				return "Alphabets, Full stop and Whitespacea are only allowed.";
            }
            else if (value.length == 0) {
				return "Can not be empty";
			}

		}
	});

	registry.register("foundation.validation.validator", {
		selector: "[data-validation=date-validation]",
		validate: function (element) {
			let el = $(element);
			//let pattern=/[0-9]/;
			let pattern = /^[A-Za-z .]+$/;
			let value = el.val();
			if (value.length == 0) {
				return "Can not be empty";
			}

		}
	});


	console.log('end');

})(jQuery, Coral);

