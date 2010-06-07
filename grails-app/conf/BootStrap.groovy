import com.isharelib.library.domain.ItemType
import com.isharelib.library.domain.PersonType
import com.isharelib.library.domain.Collector
import com.isharelib.library.domain.AuthRole
import com.isharelib.library.domain.CollectorAuthRole

class BootStrap {
    
     def springSecurityService
     def init = { servletContext ->
         new ItemType(itemType: "Movie").save(flush: true)
         new ItemType(itemType: "Album").save(flush: true)
         new ItemType(itemType: "Book").save(flush: true)
         new PersonType(personType: "Actor").save(flush: true)
         new PersonType(personType: "Actress").save(flush: true)
         new PersonType(personType: "Artist").save(flush:true)
         new PersonType(personType: "Author").save(flush:true)
         
         def adminRole = new AuthRole(authority: 'ROLE_ADMIN').save(flush:true)
         def collectorRole = new AuthRole(authority: 'ROLE_COLLECTOR').save(flush: true)
         
         String myPass = springSecurityService.encodePassword('ry6gnay')
         def chriswk = new Collector(username: 'chriswk', password: myPass, enabled: true, email: 'chriswk@ovitas.no')
         chriswk.save(flush: true)
         
         CollectorAuthRole.create chriswk, adminRole, true
     }
     def destroy = {
     }
} 