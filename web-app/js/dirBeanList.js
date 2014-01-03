//Global var for the albums list (as json)
var albumsList;

//Global var used to store id of the selected album
var imageID = 0;

//Global var for the waterwheel
var waterwheel;

var fadeDelay = 300;


$(document).ready(function() {
	$("#dl_btn").button()
	$("#dialogDiv").dialog({ autoOpen: false, modal: true, resizable: false });
	$(".dlButton").button();
	$(".orderButton").button();
	
	$.ajax({
		  url: requestAlbumsURL,
		  cache: false
		}).done(function( json ) {
			albumsList = json
			buildAlbums();
			$(".loader").hide();
		});
	
	//Underline the corresponding menu item
	$("#audioMenuItem").addClass("current-menu-item");
	
});


function buildAlbums(){
	
			var content = '';
			for ( var i = 0; i < albumsList.length; i++) {
				var album = albumsList[i];
				if(album.cover == null){
					content += "<img class = 'cover' id = 'cover_" + i + "' src='../images/dirIcon3.png' alt='Image 1' width='128' height='128'/>";
				}
				else{
					content += "<img class = 'cover' id = 'cover_"+ i + "' src='../audio/" + album.name + "/" + album.cover +  "' alt='Image 1' width='128' height='128'/>";					
				}
			}
		
			$('#waterwheel').append(content);
			
			waterwheel = $("#waterwheel").waterwheelCarousel({
				edgeFadeEnabled : true,
				keyboardNav : true,
				movedToCenter: function($newCenterItem) {
				      imageID = $newCenterItem.attr('id').split("_")[1];
				      var album = albumsList[imageID];
				      
				      //Title and songs fade out
				      $('#headline').fadeOut(fadeDelay);
				      $('#songsdiv').fadeOut(fadeDelay);
				      
				      buildSongList(album);
				      				      	
				      $('#headline').fadeIn(fadeDelay);
				      $('#songsdiv').fadeIn(fadeDelay);
				 }
	
		});
		
		//Display first album props just after loading
		buildSongList(albumsList[0]);

}

function buildSongList(album){
	//Build the song list
    var songsList = '<ul>';
    for (var i = 0; i < album.songs.length; i++){ songsList += '<li>' +
  	  album.songs[i] + '</li>'; 
    }
    songsList += '</ul>';
    
    //Wait before new display
    setTimeout(function(){$('#headline').html(album.label);$('#songsdiv').html(songsList);},fadeDelay);
}


function downloadAlbum(url){
	
	$("#dialogDiv").html("Application is preparing your download.<br/>Please wait...");
	$("#dialogDiv").dialog( "option", "title", "Preparing download" );
	
	$("#dialogDiv").dialog("open");
	
	$("#dialogDiv").load(url + "?id=" + imageID, function(response, status, xhr) {
		var msg = "";
		if (status == "success") {
		    var msg = "Download ready !!";
		  }else{
			  var msg = "Download failed !!";
			  $("#dialogDiv").html("AARRGGHHH... Something went wrong !!");
		  }
		$("#dialogDiv").dialog( "option", "title", msg );
		if (status == "success"){
			$(".dlLinkBtn").button();
			$(".dlLinkBtn").effect('pulsate',5000);
		}
	});
	
}
