$(document).ready(function() {
	$('#va-accordion').vaccordion({
		accordionH      : 800,
		accordionW      : 750,
	    expandedHeight  : 500,
	    animSpeed       : 400,
	    animOpacity     : 0.7,
	    visibleSlices   : 10
	});
});

function downloadAlbum(name){
	alert("Downloading album # " + name);
}