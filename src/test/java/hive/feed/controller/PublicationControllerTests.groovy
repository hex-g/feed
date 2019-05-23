package hive.feed.controller

import hive.feed.repository.PublicationRepository
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class PublicationControllerTests extends Specification{

    PublicationRepository publicationRepository

    MockMvc mockMvc

    def urlBase = 'http://localhost:9700/publication'

    def setup(){
        publicationRepository = Stub()
        mockMvc = MockMvcBuilders.standaloneSetup(new PublicationController(publicationRepository)).build()
    }

    def "Should return NOT ACCEPTABLE when POST a Json with null values"(){

        given:"a JSON containing a publication"

        when:"perform POST"
        def response = mockMvc.perform(post("$urlBase"))

        then:"HttpResponse as NOT ACCEPTABLE"
        response.getStatus()==406
        response.getErrorMessage()=="Null value not allowed"

    }
}
