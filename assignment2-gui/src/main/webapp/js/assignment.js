function getStudentData() {
	$.getJSON("/api/student.json", function (response ) {
	console.log(response)
	populateStudentTable(response);
	populateStudentLocationForm(response);
	});
}

function populateStudentTable(json) {
	var formString ='<tr>';
	for (var s = 0; s < json.length; s++){
		var student = json[s];
		student = explodeJSON(student);
		formString += '<td>' + student.name+ '</td><td>';
			for (var c = 0; c < student.courses.length; c++){
				var course = student.courses[c];
				course = explodeJSON(course);
				
				formString +=  course.courseCode + ', ' ;
			}
			formString += '</td>';
			if(student.longitude != null && student.latitude != null){
				formString += '<td>' + student.longitude + ', ' + student.latitude + '</td></tr>'
			} else{
				formString += '<td> No location </td></tr>';
			}
		//formString += '<td>' + student.name+ '</td>' + '<td>' + student.courses + '</td> <td>' + student.longitude + ', ' + student.latitude + '</td></tr>';
		//formString += '</td><td>' + student.longitude + ', ' + student.latitude + '</td></tr>';
			/*if(student.Location != null){
			formString += '<td>' + student.name+ '</td>' + '<td>' + student.courses + '</td> <td>' + student.longitude + ', ' + student.latitude + '</td></tr>';
		} else {
			formString += '<td>' + student.name+ '</td>' + '<td>' + student.courses + '</td> <td> No location </td></tr>';
		}*/
	}
	formString += '</tr>';
	$('#studentTable').append(formString);
}

function populateStudentLocationForm(json) {
	var formString = '<tr><td><select id="selectedStudent" name="students">';
	for (var s = 0; s < json.length; s++) {
		var student = json[s];
		student = explodeJSON(student);
		formString += '<option value="' + student.id + '">' + student.name
				+ '</option>';
	}
	formString += '</select></td></tr>';
	
	$('#studentLocationTable').append(formString);
	
}

$('#locationbtn').on('click', function(e) {
	e.preventDefault();
	get_location();
});

// This function gets called when you press the Set Location button
function get_location() {
	navigator.geolocation.getCurrentPosition(location_found);
	
}

// Call this function when you've succesfully obtained the location. 
function location_found(position) {
	var selector = document.getElementById('selectedStudent');
	var student = selector[selector.selectedIndex].value;
	var long = position.coords.longitude;
	var lat = position.coords.latitude;
	$.getJSON("/api/student/" + student + "/location?longitude=" + long + "&latitude=" + lat, function(response){ 
		populateStudentTable(response);

	});
	location.reload();
}

var objectStorage = new Object();

function explodeJSON(object) {
	if (object instanceof Object == true) {
		objectStorage[object['@id']] = object;
		console.log('Object is object');
	} else {
		console.log('Object is not object');
		object = objectStorage[object];
		console.log(object);
	}
	console.log(object);
	return object;
}

var map;
function initialize_map() {
       var mapOptions = {
               zoom : 10,
               mapTypeId : google.maps.MapTypeId.ROADMAP
       };
       map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
       // Try HTML5 geolocation
       if (navigator.geolocation) {
               navigator.geolocation.getCurrentPosition(function(position) {
                       var pos = new google.maps.LatLng(position.coords.latitude,
                                       position.coords.longitude);
                       map.setCenter(pos);
               }, function() {
                       handleNoGeolocation(true);
               });
       } else {
               // Browser doesn't support Geolocation
               // Should really tell the user…
       }
}
