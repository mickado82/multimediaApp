import security.Role
import security.User
import security.UserRole

class BootStrap {

    def init = { servletContext ->

      def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
	  
	  def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

	  //TODO: Change password
      def myAccount = new User(username: 'mickado', enabled: true, password: '*')
      
	  myAccount.save(flush: true)

      UserRole.create myAccount, adminRole, true

      assert User.count() >= 1
      assert Role.count() == 2
     
   }
	
    def destroy = {
    }
}
