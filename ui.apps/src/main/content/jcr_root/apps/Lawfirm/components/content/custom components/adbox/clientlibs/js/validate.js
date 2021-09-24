

console.log('adbox client library called');

(function ($, Coral) {
	"use strict";

	console.log('start');

	var registry = $(window).adaptTo("foundation-registry");

	registry.register("foundation.validation.validator", {
		selector: "[data-validation=txt-validation]",
		validate: function (element) {
			let el = $(element);
			//let pattern=/[^0-9]/;
			let pattern = /^[A-Za-z .']+$/;
			let value = el.val();
			if (!pattern.test(value)) {

				return "Alphabets, Whitespaces,[.],['] are only allowed";
			} else if (value.length == 0) {
				return "Please add text";
			}

		}
	});

	console.log('end');

})(jQuery, Coral);

