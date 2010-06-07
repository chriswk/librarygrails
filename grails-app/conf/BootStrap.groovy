import com.isharelib.library.domain.*
import java.text.SimpleDateFormat

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
         def movie = new Item(title: "Street Fighter : The Legend of Chun-Li", releaseYear : new SimpleDateFormat("yyyy").parse("2009"), itemType: ItemType.findByItemType("Movie")).save(flush: true)

         def adminRole = new AuthRole(authority: 'ROLE_ADMIN').save(flush:true)
         def collectorRole = new AuthRole(authority: 'ROLE_COLLECTOR').save(flush: true)
         
         String myPass = springSecurityService.encodePassword('ry6gnay')
         def chriswk = new Collector(username: 'chriswk', password: myPass, enabled: true, email: 'chriswk@ovitas.no')
         chriswk.save(flush: true)
         new ItemInstance(item: movie, owner: chriswk).save()
         
         CollectorAuthRole.create chriswk, adminRole, true
         new RequestMap(url:"/collection/**", configAttribute: 'IS_AUTHENTICATED_FULLY').save(flush: true)
     }
     def destroy = {
     }
} 