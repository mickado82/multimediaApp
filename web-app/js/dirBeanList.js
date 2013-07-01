var albumsList;
var waterwheel;


$(document).ready(function() {
	
	$("#dialogDiv").dialog({ autoOpen: false, modal: true, resizable: false });
	$(".dlButton").button();
	$(".orderButton").button();
	
	//$(".loader").hide();
	
	/*---------- To delete !!- --------*/
	waterwheel = $("#waterwheel").waterwheelCarousel({
		edgeFadeEnabled : true,
		keyboardNav : true,

	});
	
	
//	$.ajax({
//		  url: requestAlbumsURL,
//		  cache: false
//		}).done(function( json ) {
//			albumsList = json
//			buildAlbums();
//			$(".loader").hide();
//		});
	
});


function buildAlbums(){
	
			var content = '<div id="wheel">';
			for ( var i = 0; i < albumsList.length; i++) {
				var album = albumsList[i];
				if(album.cover == null){
					content += "<img id = 'cover_" + i + "' src='../images/dirIcon.png' alt='Image 1' width='128' height='128'/>";
				}
				else{
					content += "<img id = 'cover_"+ i + "' src='../audio/" + album.name + "/" + album.cover +  "' alt='Image 1' width='128' height='128'/>";					
				}
			}
			content += '</div>'
		
			$('#borderDiv').append(content);
			
			$("#wheel").waterwheelCarousel({
				edgeFadeEnabled : true,
				keyboardNav : true,
				movedToCenter: function($newCenterItem) {
				      var imageID = $newCenterItem.attr('id').split("_")[1];
				      var album = albumsList[imageID];
				      $('#album_title').html(album.name);
				      
				      var songsList = '<ul>';
				      for (var i = 0; i < album.songs.length; i++){ songsList += '<li>' +
				 		 album.songs[i] + '</li>'; 
				      }
				 		  
				      	songsList += '</ul>';
				      	$('#album_songs').html(songsList);
				 }
	
		});

}


function downloadAlbum(url){
	
	$("#dialogDiv").html("Application is preparing your download.<br/>Please wait...");
	$("#dialogDiv").dialog( "option", "title", "Preparing download" );
	
	$("#dialogDiv").dialog("open");
	
	$("#dialogDiv").load(url, function(response, status, xhr) {
		var msg = "";
		if (status == "success") {
		    var msg = "Download ready !!";
		  }else{
			  var msg = "Download failed !!";
		  }
		$("#dialogDiv").dialog( "option", "title", msg );
		if (status == "success"){
			$(".dlLinkBtn").button();
			$(".dlLinkBtn").effect('pulsate',5000);
		}
	});
	
	/*
	 * var url = "/filesList-0.1/dirBean/download?id="+id;
	 * //g.createLink({controller: 'dirBean', action: 'download', params: {id:
	 * id}});
	 * 
	 * $.fileDownload(url, { preparingMessageHtml: "Generating the archive,
	 * please wait.<br/>(This may take a few minutes)", failMessageHtml: "There
	 * was a problem generating your report, please try again." }); return
	 * false; //this is critical to stop the click event which will trigger a
	 * normal file download!
	 */
}
