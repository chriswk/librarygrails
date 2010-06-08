package com.isharelib.library.service

import com.isharelib.library.domain.Collector
import com.isharelib.library.domain.Item
import com.isharelib.library.domain.ItemInstance
import grails.test.*

class CollectorServiceTests extends GrailsUnitTestCase {
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAddItemToCollection() {
    	    mockDomain(Collector)
    	    mockDomain(Item)
    	    mockDomain(ItemInstance)
    	    def collectorService = new CollectorService()
    	    def user = new Collector(username: 'tester', password: 'password', enabled: true, email: 'test@test.com', collection: [])
    	    def item = new Item(title: "Test movie")
    	    collectorService.addItemToCollection(user, item)
    	    def itemInstance = ItemInstance.findById(1)
    	    assertTrue("User's collection should contain the itemInstance", user.collection.contains(itemInstance))
    	    
    }
}
