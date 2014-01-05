/**
 * 
 */

//API key needed to access DB
var API_KEY = "*" //CHANGE ME
	
var NO_INFO = "No info available"

//Object with the movie description
var movieDesc;

$(document).ready(function() {
	
			//Underline the corresponding menu item
			$("#videoMenuItem").addClass("current-menu-item");

			$(".videoDlBtn").button();
			
			
			//Initialize accordion 
			    $( "#accordion" ).accordion({
				    collapsible: true, 
				    active: false,
				    heightStyle: "content",
				    icons: { "header": "ui-icon-video", "activeHeader": "ui-icon-info" },
				    activate: fillAccordionTab
				    });
											
			    //Remove this class Coz I want squared corner accordion
			    $("h3").removeClass("ui-corner-all");
			});


//Fill the tab with info gathered from ajax call
function fillAccordionTab(event, ui){
	
	//First insert the loading spinner
	var spinnerString = "<div class='spinner'><div class='cube1'></div><div class='cube2'></div></div>"; 
	$(ui.newPanel).find('.moviDescDiv').html(spinnerString);
	
	
	//remove info from Old panel
	$(ui.oldPanel).find('.moviDescDiv').html("");
	
	//We do not want any data query if user closes the accordion
	if(!$(ui.newHeader).is("h3"))
		return;
	
	//Try to find movie infos on theMokvieDB an fill the tab with infos
	//Ajax calls are 1s delayed to avoid overload ?
	getInfoFromMovieDb(event, ui);
	
}

function getInfoFromMovieDb( event, ui ) {

    var movieId = $(ui.newHeader).attr('id');
    
    //If there is no ID for this file
    if(movieId.indexOf('ui-accordion-accordion-header') != -1){
    	$(ui.newPanel).find('.moviDescDiv').html("No Information available on The Movie DataBase");
    	return;
    }
	
    //Ajax call to take general info from Movie DB
	var generalRequest = $.ajax({
	  type: "GET",
	  url: "https://api.themoviedb.org/3/movie/" + movieId,
	  data: {api_key: API_KEY},
	  dataType: "json"
	});
	
	//If ajax works
	generalRequest.done( function(response){
		
		//Fill object with general info
		fillGeneralItems(response);
		
		//Get cast, crew info and fill the object
		getCastoFromMovieDb(movieId, ui);
			
	});
	
	//If it fails
	generalRequest.fail( function(response){
		
		$(ui.newPanel).find('.moviDescDiv').html("No Information available on The Movie DataBase");
	
	});
	
	
  }

function getCastoFromMovieDb(movieId, ui) {
	
    //Ajax call to take general info from Movie DB
	var castRequest = $.ajax({
	  type: "GET",
	  url: "https://api.themoviedb.org/3/movie/" + movieId + "/credits",
	  data: {api_key: API_KEY},
	  dataType: "json"
	});
	
	castRequest.done( function(response){
		fillCastItems(response);
		//Fill the page
		$(ui.newPanel).find('.moviDescDiv').html(buildMovieDesc());
	});
	
	castRequest.fail( function(response){
		$(ui.newPanel).find('.moviDescDiv').html("No Information available on The Movie DataBase");
	});
	
  }

//Fill the object with general info (first ajax call) 
function fillCastItems(response){
	
	//Number of cast members we want, must not be greater than the total crew number
	var nCast = 5;
	if(nCast >  response.cast.length) nCast = response.cast.length;
	
	//Take the nCast first cast
	movieDesc.cast = "";
	for(var i = 0; i < nCast; i++){
		movieDesc.cast += response.cast[i].name
		if(i != nCast - 1) movieDesc.cast += ", ";
	}
	
	movieDesc.cast += "..."
	
	//Take the director
	for(var i = 0; i < response.crew.length; i++){
		
		var member = response.crew[i];
		
		if(member.job == "Director")
			movieDesc.director = member.name; 
	}
	
}

//Fill the object with general info (first ajax call) 
function fillGeneralItems(response){
	
	//Fill object with the params from the response
	movieDesc = new Object(); //create new to remove previous info
	movieDesc.poster_path = response.poster_path;
	movieDesc.original_title = response.original_title;
	
	var genres = "";
	for (var i=0;i<response.genres.length;i++)
	{ 
		genres += response.genres[i].name;
		//Add coma only if it's not the last item
		if(i != response.genres.length - 1) genres += ", "
	}
	movieDesc.genres = genres;
	
	movieDesc.release_date = response.release_date;
	movieDesc.runtime = response.runtime + " mins";
	if(response.runtime == '0') movieDesc.runtime = NO_INFO;
	
	if(response.runtime == null) movieDesc.runtime = NO_INFO;
	
	movieDesc.vote_average = response.vote_average;
	if(response.vote_average == '0') movieDesc.vote_average = NO_INFO;
	
	movieDesc.overview = response.overview;	
	if(response.overview == null) movieDesc.overview = NO_INFO;
	movieDesc.imdb_id = response.imdb_id;
}


//Build html fragment to display infos in the opened tab
function buildMovieDesc() {
	
	var myString = "<div style='float: left; width: 250px;'>"
		  + "<a href = 'http://www.imdb.com/title/" + movieDesc.imdb_id + "' target='_blank'>"//imdb_id 
		  + "<span class='image-wrap' style='width: auto; height: auto;''>"
		  +"<img src='"
		  + "http://image.tmdb.org/t/p/w185/" + movieDesc.poster_path
		  +"' alt='No cover available'>"
		  + "</span>"
		  + "</a>"
		  + "</div>"
		  + "<div style='float: left; width: 350px;'>"
		  + "<p class = 'descriptionItem'>Infos</p>"
		  + "<div style='margin-top: 10px;'>"
		  + "<ul style='list-style-type: none';>"
		  + "<li>"
		  + "<b>Original title: </b>" + movieDesc.original_title
		  + "</li>"
		  + "<li>"
		  + "<b>Director: </b>" + movieDesc.director
		  + "</li>"
		  + "<li>"
		  + "<b>Genres: </b>" + movieDesc.genres
		  + "</li>"
		  + "<li>"
		  + "<b>Release date: </b>" + movieDesc.release_date
		  + "</li>"
		  + "<li>"
		  + "<b>Runtime: </b>" + movieDesc.runtime
		  + "</li>"
		  + "<li>"
		  + "<b>Cast: </b>" + movieDesc.cast
		  + "</li>"
		  + "<li>"
		  + "<b>Rating: </b>" + movieDesc.vote_average
		  + "</li>"
		  + "</ul>"
		  + "</div>"
		  + "</div>"
		  + "<div style='float: right; width: 250px;'>"
		  + "<p class = 'descriptionItem'>Plot</p>"
		  + "<div id='moviePlot' style='margin-top: 10px;'>" + movieDesc.overview + "</div>"
		  + "</div>"
		  
		  return myString;
		  
	}