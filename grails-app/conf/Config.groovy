// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']


// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
		
		//dir where temp zip are created
		temp.dir = "/home/mickado/workspace/multimediaApp/web-app/resources/"
		
		//Dir where audio files are stored
		my.files.dir = "/home/mickado/Musique"
		
		//Dir where video files are stored
		my.files.videos = "/home/mickado/Vidéos"
		
		//min free space to create the archives (in GigaBytes)
		min.free.space = 0.5
		// root folder to check avail disk space
		home.folder="/"
		
		// log4j configuration
		log4j = {
			
			root {
				warn console
				additivity = false
			}
			
			info console: ['grails.app.controllers','grails.app.services']
			
			error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
				   'org.codehaus.groovy.grails.web.pages', //  GSP
				   'org.codehaus.groovy.grails.web.sitemesh', //  layouts
				   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
				   'org.codehaus.groovy.grails.web.mapping', // URL mapping
				   'org.codehaus.groovy.grails.commons', // core / classloading
				   'org.codehaus.groovy.grails.plugins', // plugins
				   'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
				   'org.springframework',
				   'org.hibernate',
				   'net.sf.ehcache.hibernate'
		}
		
    }
	test {
		grails.logging.jul.usebridge = false
		// grails.serverURL = "http://www.changeme.com"
		
		//dir where temp zip are created
		//TODO: Add the Tomcat apps dir for test ENV
		temp.dir = "/home/mickado/tomcat/myApps/multimediaApp/multimediaApp-2.0/resources/"
		
		//Files rep dir
		//TODO: Add the dir wher the files are located (Must be a tomcat context resource to access images)
		my.files.dir = "/home/mickado/Musique"
		
		//Dir where video files are stored
		my.files.videos = "/home/mickado/Vidéos"
		
		//min free space to create the archives (in GigaBytes)
		min.free.space = 0.5
		// root folder to check avail disk space
		home.folder="/"
		
		// log4j configuration
		log4j = {
			
			appenders {
				//TODO: Add the Tomcat apps dir for test ENV
				file name:'testFile',  maxFileSize: 1024, file:"/home/mickado/tomcat/myApps/multimediaApp/multimediaApp.log"
			}
			
			info additivity: false, testFile: ['grails.app.controllers','grails.app.services']

			root {
				error 'testFile'
				additivity = false
			}
			
			error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
				   'org.codehaus.groovy.grails.web.pages', //  GSP
				   'org.codehaus.groovy.grails.web.sitemesh', //  layouts
				   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
				   'org.codehaus.groovy.grails.web.mapping', // URL mapping
				   'org.codehaus.groovy.grails.commons', // core / classloading
				   'org.codehaus.groovy.grails.plugins', // plugins
				   'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
				   'org.springframework',
				   'org.hibernate',
				   'net.sf.ehcache.hibernate'
		}
	}
    production {
        grails.logging.jul.usebridge = false
        // grails.serverURL = "http://www.changeme.com"
		
		//dir where temp zip are created
		//TODO: Add the Tomcat apps dir for test ENV
		temp.dir = "~/CHANGE_ME/multimediaApp/multimediaApp-2.0/resources/"
		
		//Files rep dir
		//TODO: Add the dir wher the files are located (Must be a tomcat context resource to access images)
		my.files.dir = "~/CHANGE_ME"
		
		//min free space to create the archives (in GigaBytes)
		min.free.space = 10
		// root folder to check avail disk space
		home.folder="/home/"
		
		// log4j configuration
		log4j = {
			
			appenders {
				//TODO: Add the Tomcat apps dir for test ENV
				file name:'unixFile',  maxFileSize: 1024, file:"~/CHANGE_ME/multimediaApp/multimediaApp.log"
			}
			
			info additivity: false, unixFile: ['grails.app.controllers','grails.app.services']

			root {
				error 'unixFile'
				additivity = false
			}
			
			error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
				   'org.codehaus.groovy.grails.web.pages', //  GSP
				   'org.codehaus.groovy.grails.web.sitemesh', //  layouts
				   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
				   'org.codehaus.groovy.grails.web.mapping', // URL mapping
				   'org.codehaus.groovy.grails.commons', // core / classloading
				   'org.codehaus.groovy.grails.plugins', // plugins
				   'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
				   'org.springframework',
				   'org.hibernate',
				   'net.sf.ehcache.hibernate'
		}
    }
}

grails.plugins.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugins.springsecurity.interceptUrlMap = [
    '/login/**':         ['IS_AUTHENTICATED_ANONYMOUSLY'],
    '/logout/**':         ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/dirBean/**':         ['ROLE_USER', 'ROLE_ADMIN'],
	'/videoBean/browse':   ['ROLE_USER', 'ROLE_ADMIN'],
	'/audio/**':         ['ROLE_USER', 'ROLE_ADMIN'],
	'/video/**':         ['ROLE_USER', 'ROLE_ADMIN'],
	'/resources/**':         ['ROLE_USER', 'ROLE_ADMIN'],
	'/user/**':         ['ROLE_ADMIN'],
	'/videoBean/**':         ['ROLE_ADMIN'],
]

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'filesList.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'filesList.UserRole'
grails.plugins.springsecurity.authority.className = 'filesList.Role'

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'security.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'security.UserRole'
grails.plugins.springsecurity.authority.className = 'security.Role'

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'security.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'security.UserRole'
grails.plugins.springsecurity.authority.className = 'security.Role'
