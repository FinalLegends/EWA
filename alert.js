

/** alert_submit
 * DESCRIPTION: Sends email to user when current weather surpasses high/low threshold
 * INPUTS: zip code, email, high temp, low temp
 * OUTPUTS: none
 * RETURN VALUE: none
 * SIDE EFFECTS: Modifies frontend to display success message
 */
	$("#alert_submit").click(function() {
		/* Initialize text-field variables */
		var zipCode = $("#zipCode").val();
		var emailID = $("#emailID").val();
		var highTemp = $("#highTemp").val();
		var lowTemp = $("#lowTemp").val();
		
		//test print values
		//$('#weather').html(zipCode + " " + emailID + " " + highTemp + " " + lowTemp);
		
		/* Validate zip code */
		if (zipCode.length != 5)
		{
			$('#weather').html("Invalid input! Zip code must be 5 digits.");
			
		} else {
			/* Get json object, extract temp_f and feelslike_f, and display on screen */
			 $.ajax({
			 type: 'GET',
		     url : "http://api.wunderground.com/api/0ec92fe577458c9e/conditions/q/" + zipCode + ".json",
			 dataType : "jsonp",
			 success : function(parsed_json) {
			 var location = parsed_json['current_observation']['display_location']['full'];
			 var temp_f = parsed_json['current_observation']['temp_f'];
			 
			 $('#weather').html("Current temperature in " + location + " is: " + temp_f + " degrees Fahrenheit."
					 + "<br> An alert has been set to email " + emailID + " when temperature exceeds " + 
					 highTemp + " or falls below " + lowTemp + " degrees Fahrenheit.");
			 }
			 });
			 /*start 15 mins
			  * extract temp from zip code
			  * check if temp => highTemp or temp =< lowTemp
			  * 	sendemail
			  * wait for 15 mins
			  *end
			  */
		}
		  
	});
	
	function userSubmit() {
		
		return false;
	}
