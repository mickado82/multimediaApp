import security.Role
import security.User
import security.UserRole

class BootStrap {

    def init = { servletContext ->

      def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

      def testUser = new User(username: 'guest', enabled: true, password: 'toutnu')
      
	  testUser.save(flush: true)

      UserRole.create testUser, userRole, true

      assert User.count() == 1
      assert Role.count() == 1
      assert UserRole.count() == 1
     
   }
	
    def destroy = {
    }
}
