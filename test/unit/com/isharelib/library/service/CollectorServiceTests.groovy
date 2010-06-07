package com.isharelib.library.service

import com.isharelib.library.domain.Collector
import com.isharelib.library.domain.Item
import com.isharelib.library.domain.ItemInstance
import grails.test.*

class CollectorServiceTests extends GrailsUnitTestCase {
    def collectorService  

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAddItemToCollection() {
      def user = new Collector(username: 'tester', password: 'password', enabled: true, email: 'test@test.com')
      def item = new Item(title: "Test movie")
      user.save()
      item.save()
      collectorService.addItemToCollection(user, item)
      assertEquals("Should have exactly one item in collection", user.collection.size(), 1)
      def itemInstance = user.collection().get(0)
      assertEquals("Title should be Test movie", itemInstance.item.title, "Test movie")
    }
}
