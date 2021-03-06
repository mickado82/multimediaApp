#multimediaApp
Multimedia application dedicated to serve audio and video files from a dedicated server

##Version 3.0 
 - Grails version updated to 2.3.4
 - Rebuild of the videos views 
 - Movies infos are now fetched from 'The movie databse' thanks to their nice api
 - Readme updated with better markdown
 
##Version 2.6
 - Grails version updated to 2.2.4
 - Videos number in list increased
 - Videos size is now displayed on the list
 - 'Home' menu btn has been removed as it was useless

##Version 2.5
 - WaterWheel display now fully functionnal
 - Version is now displayed in the main view
 - Albums have now a name (filesystem) and a label (view)
 - UI improved for users, videos and audios views 
 - Privilege management added in the views
 - More html5, CSS3 and responsive
 - Design has been taken from http://luiszuno.com/blog/downloads/modus-html-template/
 - Directories are now listed using JSON format
 - User is looged in both directories
 
##Version 2.0
 - WaterWheel display branch created
 
##Version 1.0
 - Download link generation changed 
 - Logging system implemented
 - Tracks names shortened if needed for proper display
 - Files filtered by extension (mp3, flac etc ...)
 - Covers images added when available
 - Default image added if no cover was found
 - Security management switched to URL mapping for secured resources access
 - Title and icon added
 - CRUD users management allowed for admin role
 - Application server changed from jboss to tomcat
 - Firefox compatibility
 - Folders can now be sort by date added / name
 - UI improvements, css3 features added
   
##Version 0.2
 - Users management improved
 - Basic login log added
 - Grails version upgraded to 2.1.1
 - Minor UI improvement
  
##Version 0.1
- First deployment of the application
- Audio files listed and all tracks displayed in gui
- Tracks ziped in a temp dir and Download link generated 
- Spring security to manage login
- Deployed on Jboss7