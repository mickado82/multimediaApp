$(document).ready(function() {
	$('#va-accordion').vaccordion({
		accordionH      : 800,
		accordionW      : 750,
	    expandedHeight  : 500,
	    animSpeed       : 400,
	    animOpacity     : 0.7,
	    visibleSlices   : 10
	});
	
	$("#dialogDiv").dialog({ autoOpen: false, modal: true });
	$(".dlButton").button();
});


function downloadAlbum(id){
	
	$("#dialogDiv").html("Application is preparing your download.<br/>Please wait...");
	$("#dialogDiv").dialog( "option", "title", "Preparing download" );
	
	$("#dialogDiv").dialog("open");
	
	$("#dialogDiv").load("/multimediaApp/dirBean/download?id="+id, function(response, status, xhr) {
		var msg = "";
		if (status == "success") {
		    var msg = "Download ready !!";
		  }else{
			  var msg = "Download failed !!";
		  }
		$("#dialogDiv").dialog( "option", "title", msg );
	});
	
	/*
		var url = "/filesList-0.1/dirBean/download?id="+id; //g.createLink({controller: 'dirBean', action: 'download', params: {id: id}});
		
		$.fileDownload(url, {
            preparingMessageHtml: "Generating the archive, please wait.<br/>(This may take a few minutes)",
            failMessageHtml: "There was a problem generating your report, please try again."
        });
        return false; //this is critical to stop the click event which will trigger a normal file download!
        */
}
