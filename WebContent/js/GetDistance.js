/**
 * 
 */

	
	$("#submit").click(function() {
		var airport1 = $("#airport1").val();
		var airport2 = $("#airport2").val();
		console.log(airport1);
		var key = '64e01cdc6efeadac186210bfd4e694ff';
		$('#distance').html("Airport1 = " + airport1 + " and Airport2 = " + airport2);
		/*
			 $.ajax({
			 type: 'GET',
		     url : "https://airport.api.aero/airport/distance/mci/bji?user_key=64e01cdc6efeadac186210bfd4e694ff",
			 dataType : "jsonp",
			 success : function(parsed_json) {
			 var location = parsed_json['current_observation']['display_location']['full'];
			 var temp_f = parsed_json['current_observation']['temp_f'];
			 var feelsLike = parsed_json['current_observation']['feelslike_f'];
			 $('#weather').html("Current temperature in " + location + " is: " + temp_f + " degrees Fahrenheit."
					 + "<br> It feels like " + feelsLike + " degrees Fahrenheit.");
			 }
			 });
			 */		  
	});
	
	function userSubmit() {
		
		return false;
	}
